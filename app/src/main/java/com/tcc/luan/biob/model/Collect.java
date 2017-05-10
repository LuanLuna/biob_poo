package com.tcc.luan.biob.model;

import android.content.Context;
import android.view.View;

import com.tcc.luan.biob.R;
import com.tcc.luan.biob.model.util.Item;
import com.tcc.luan.biob.model.util.Item_adapter;
import com.tcc.luan.biob.view.MainActivity;

import java.util.List;

/**
 * Created by luan on 11/09/16.
 */
public class Collect {
    public static void prepareCollect(MainActivity context, List<Item> close){
        Item_adapter it = new Item_adapter(close,context, Item_adapter.COLECT_MODE);
        context.getGv().setAdapter(it);
        context.getTitle_().setText("Coletar");
        context.getDescription().setText("Colete o máximo de itens.");
        context.getHeader().setBackgroundResource(R.color.header_hunter);
        context.getGv().setBackgroundResource(R.color.bg_hunter);
        context.getImage().setImageResource(R.drawable.squirrel);
        context.getBack().setVisibility(View.VISIBLE);
        context.getBlok().setVisibility(View.INVISIBLE);
    }
}
