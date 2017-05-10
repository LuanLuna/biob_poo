package com.tcc.luan.biob.model;

import android.view.View;

import com.tcc.luan.biob.R;
import com.tcc.luan.biob.model.database.DataBase;
import com.tcc.luan.biob.model.util.Item;
import com.tcc.luan.biob.model.util.Item_adapter;
import com.tcc.luan.biob.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luan on 10/09/16.
 */
public class Learn {
    public static void prepareLearn(MainActivity context){
        List<Item> itens = new ArrayList<>();
        try{
            DataBase db = new DataBase(context);
            itens = db.get_user_item(SESSION.getInstance().getUser());
        }catch(Exception e){}
        Item_adapter it = new Item_adapter(itens,context,Item_adapter.LEARN_MODE);
        context.getGv().setAdapter(it);
        context.getTitle_().setText("Aprender");
        context.getDescription().setText("Aprenda sobre os itens que coletou.");
        context.getHeader().setBackgroundResource(R.color.header_learn);
        context.getGv().setBackgroundResource(R.color.bg_learn);
        context.getImage().setImageResource(R.drawable.owl);
        context.getBack().setVisibility(View.INVISIBLE);
        context.getBlok().setVisibility(View.VISIBLE);
    }
}
