package com.tcc.luan.biob.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.tcc.luan.biob.R;
import com.tcc.luan.biob.model.BiobCountDownTime;
import com.tcc.luan.biob.model.Collect;
import com.tcc.luan.biob.model.SESSION;
import com.tcc.luan.biob.model.database.DataBase;
import com.tcc.luan.biob.model.util.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CollectActivity extends AppCompatActivity {
    private TextView chronometer;
    private BiobCountDownTime timer;
    private TextView count;
    private int quantidade;
    private Map<Integer, Integer> grid;
    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        getSupportActionBar().hide();
        db = new DataBase(this);
        //---------------------------------criação da grade de itens
        grid = new HashMap<>();
        grid.put(1, R.id.image1);
        grid.put(2, R.id.image2);
        grid.put(3, R.id.image3);
        grid.put(4, R.id.image4);
        grid.put(5, R.id.image5);
        grid.put(6, R.id.image6);
        grid.put(7, R.id.image7);
        grid.put(8, R.id.image8);
        grid.put(9, R.id.image9);
        //---------------------------------selecionando item
        Intent intent = getIntent();
        int id = 0;
        int location = 0;
        if(intent != null){
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                id = bundle.getInt("item_id");
                location = bundle.getInt("location_id");
            }
        }

        if (id == 0){
            finish();
        }else {
            Item item = db.get_item_by_id(id);
            if (item != null) {
                item.setLocation(location);
                //----------------------------------------número de toques
                quantidade = (new Random()).nextInt(5)+7;
                count = (TextView) findViewById(R.id.count);
                count.setText("Colete "+item.getName()+" "+quantidade+" vezes");
                //----------------------------------------posicionar imagem
                int ramdom_image = (new Random()).nextInt(9)+1;

                ImageView imagem = (ImageView)findViewById(grid.get(ramdom_image));
                imagem.setImageResource(item.getImageResource());
                imagem.setOnClickListener(new ItenOnClickLinstener(item,grid,imagem,this));
                //----------------------------------------cronometro
                chronometer = (TextView) findViewById(R.id.chronometer);
                timer = new BiobCountDownTime(this, chronometer, 10000, 1000);
                timer.start();
            }
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();

        if(timer != null){
            timer.cancel();
        }
    }
    private class ItenOnClickLinstener implements View.OnClickListener{
        private Item item;
        private Map<Integer, Integer> grid;
        private ImageView imageView;
        private CollectActivity collectActivity;

        public ItenOnClickLinstener(Item item, Map<Integer, Integer> grid, ImageView imageView, CollectActivity collectActivity) {
            this.item = item;
            this.grid = grid;
            this.imageView = imageView;
            this.collectActivity = collectActivity;
        }

        @Override
        public void onClick(View v) {
            int ramdom_image = (new Random()).nextInt(9)+1;
            Log.i("LOG", ramdom_image+"");
            imageView.setImageResource(0);
            imageView.setOnClickListener(null);
            count.setText("Pegue "+item.getName()+" "+(--quantidade)+" vezes");
            if (quantidade > 0) {
                ImageView imagem = (ImageView) findViewById(grid.get(ramdom_image));
                imagem.setImageResource(item.getImageResource());
                imagem.setOnClickListener(new ItenOnClickLinstener(item, grid, imagem,collectActivity));
            }else {
                collectActivity.finish();
                boolean salvar = db.exeqSql("insert into user_item (user, item) values ("+
                        SESSION.getInstance().getUser().getId()+", "+item.getId()+");");
                if (salvar)
                    db.exeqSql("update item_location set captured = 1 where _id = " + item.getLocation() + ";");
                Log.i("update_sql_captured","update item_location set captured = 1 where _id = " + item.getLocation() + ";");
            }

        }
    }
}
