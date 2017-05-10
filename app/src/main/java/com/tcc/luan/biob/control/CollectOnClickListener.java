package com.tcc.luan.biob.control;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tcc.luan.biob.model.util.Item;
import com.tcc.luan.biob.view.CollectActivity;

/**
 * Created by luan on 09/09/16.
 */
public class CollectOnClickListener implements View.OnClickListener {
    private Item item;

    public CollectOnClickListener(Item item) {

        this.item = item;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(),CollectActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("item_id",item.getId());
        bundle.putInt("location_id",item.getLocation());
        intent.putExtras(bundle);
        v.getContext().startActivity(intent);
    }
}
