package com.tcc.luan.biob.view;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tcc.luan.biob.R;
import com.tcc.luan.biob.model.database.DataBase;
import com.tcc.luan.biob.model.util.Item;

import org.w3c.dom.Text;

public class LearnActivity extends AppCompatActivity {
    private ImageView image_item;
    private TextView item_name;
    private TextView item_weight;
    private TextView item_size;
    private TextView description;
    private TextView location;
    private TextView item_type;
    private  TextView item_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        int id = 0;
        if(intent != null){
            Bundle bundle = intent.getExtras();//11
            if (bundle != null){
                id = bundle.getInt("item_id");
            }
        }

        if (id == 0){
            finish();
        }else{
            DataBase db = new DataBase(this);
            Item item = db.get_item_by_id(id);
            if (item != null){
                //selecionando
                image_item = (ImageView) findViewById(R.id.image_item);
                item_name = (TextView)  findViewById(R.id.item_name);
                item_weight = (TextView)  findViewById(R.id.item_weight);
                item_size = (TextView) findViewById(R.id.item_size);
                item_type = (TextView) findViewById(R.id.type_content);
                item_group = (TextView) findViewById(R.id.group_content);
                description = (TextView) findViewById(R.id.description_content);
                location = (TextView) findViewById(R.id.location_content);
                //setando
                image_item.setImageResource(item.getImageResource());
                item_name.setText(item.getName());
                item_weight.setText(item.getWeight()+"");
                item_size.setText(item.getSize()+"");
                item_type.setText(item.getType_label());
                item_group.setText(item.getGroup_label());
                description.setText(item.getDesription());
                location.setText(item.getPlace());
            }else
                finish();
        }
    }
}
