package com.tcc.luan.biob.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.tcc.luan.biob.R;
import com.tcc.luan.biob.control.BackOnClickListener;
import com.tcc.luan.biob.control.Biob_Batery;
import com.tcc.luan.biob.control.Biob_Location;
import com.tcc.luan.biob.model.Learn;

public class MainActivity extends AppCompatActivity {
    private GridView gv;
    private TextView title;
    private TextView description;
    private RelativeLayout header;
    private ImageView image;
    private Button back;
    private Switch blok;
    private Biob_Batery biob_batery = new Biob_Batery();
    private Biob_Location biob_location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        gv = (GridView) findViewById(R.id.item_gridView);
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        header = (RelativeLayout) findViewById(R.id.header);
        image = (ImageView) findViewById(R.id.image_header);
        back = (Button) findViewById(R.id.back);
        blok = (Switch) findViewById(R.id.blok);

        BackOnClickListener voltar = new BackOnClickListener(this);
        back.setOnClickListener(voltar);
        //-------------------------------------Localizador
        biob_location = new Biob_Location(this);
        biob_location.callConnection();
        //-------------------------------------Medição de bateria
        registerReceiver(biob_batery, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        Learn.prepareLearn(this);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        biob_location.stopLocationUpdate();
        unregisterReceiver(biob_batery);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Learn.prepareLearn(this);
    }

    public boolean isBlok(){
        return blok.isChecked();
    }

    public Button getBack() {
        return back;
    }

    public void setBack(Button back) {
        this.back = back;
    }

    public Switch getBlok() {
        return blok;
    }

    public void setBlok(Switch blok) {
        this.blok = blok;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public TextView getDescription() {
        return description;
    }

    public void setDescription(TextView description) {
        this.description = description;
    }

    public RelativeLayout getHeader() {
        return header;
    }

    public void setHeader(RelativeLayout header) {
        this.header = header;
    }

    public GridView getGv() {
        return gv;
    }

    public void setGv(GridView gv) {
        this.gv = gv;
    }

    public TextView getTitle_() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }
}