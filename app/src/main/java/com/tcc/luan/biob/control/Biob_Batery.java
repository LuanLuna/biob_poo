package com.tcc.luan.biob.control;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tcc.luan.biob.model.SESSION;

/**
 * Created by luan on 11/09/16.
 */
public class Biob_Batery extends BroadcastReceiver {
    public static int BATERY_LEVEL = 100;
    @Override
    public void onReceive(Context context, Intent intent) {
        SESSION.test = true;
        try {
            BATERY_LEVEL = intent.getIntExtra("level", 0);
            if (BATERY_LEVEL <= 15)
                Biob_Location.setRefreshTime(Biob_Location.SAFE_REFRESH_TIME);
            else
                Biob_Location.setRefreshTime(Biob_Location.REAL_REFRESH_TIME);
        }catch (Exception e){}
    }
}