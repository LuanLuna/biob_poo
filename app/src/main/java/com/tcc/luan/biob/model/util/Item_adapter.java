package com.tcc.luan.biob.model.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tcc.luan.biob.R;
import com.tcc.luan.biob.control.CollectOnClickListener;
import com.tcc.luan.biob.control.LearnOnclickListener;

import java.util.List;

/**
 * Created by luan on 31/08/16.
 */
public class Item_adapter extends BaseAdapter {
    private List<Item> itens;
    private Context context;
    private int mode;

    public static final int LEARN_MODE = 1;
    public static final int COLECT_MODE = 2;


    public Item_adapter(List<Item> itens, Context context, int mode){
        this.context = context;
        this.itens = itens;
        this.mode = mode;
    }
    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        if (position >= 0 && position < itens.size())
        return itens.get(position);
        else return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Item item = itens.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.activity_item_adapter, null);

        ((ImageView) layout.findViewById(R.id.item_image)).setImageResource(item.getImageResource());
        ((TextView) layout.findViewById(R.id.item_name)).setText(item.getName());
        ((TextView) layout.findViewById(R.id.item_group)).setText(item.getGroup_label());
        ((TextView) layout.findViewById(R.id.item_type)).setText(item.getType_label());

        //On click
        switch (mode){
            case LEARN_MODE:
                layout.setOnClickListener(new LearnOnclickListener(item.getId()));
                break;
            case COLECT_MODE:
                layout.setOnClickListener(new CollectOnClickListener(item));
                break;
        }

        return layout;
    }
}
