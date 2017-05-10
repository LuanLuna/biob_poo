package com.tcc.luan.biob.control;

import android.view.View;

import com.tcc.luan.biob.model.Learn;
import com.tcc.luan.biob.view.MainActivity;

/**
 * Created by luan on 10/09/16.
 */
public class BackOnClickListener implements View.OnClickListener {
    private MainActivity context;

    public BackOnClickListener(MainActivity context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Learn.prepareLearn(context);
        context.getBlok().setChecked(true);
    }
}