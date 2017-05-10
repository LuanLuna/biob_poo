package com.tcc.luan.biob.model;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import com.tcc.luan.biob.view.CollectActivity;

import java.util.Calendar;

/**
 * Created by luan on 11/09/16.
 */
public class BiobCountDownTime extends CountDownTimer {

    private CollectActivity context;
    private TextView tv;
    private long timeInFuture;


    public BiobCountDownTime(CollectActivity context, TextView tv, long timeInFuture, long interval){
        super(timeInFuture, interval);
        this.context = context;
        this.tv = tv;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        timeInFuture = millisUntilFinished;
        tv.setText(getCorretcTimer(true, millisUntilFinished)+":"+getCorretcTimer(false, millisUntilFinished));
    }

    @Override
    public void onFinish() {
        timeInFuture -= 1000;
        tv.setText(getCorretcTimer(true, timeInFuture)+":"+getCorretcTimer(false, timeInFuture));

        context.finish();

    }

    private String getCorretcTimer(boolean isMinute, long millisUntilFinished){
        String aux;
        int constCalendar = isMinute ? Calendar.MINUTE : Calendar.SECOND;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millisUntilFinished);

        aux = c.get(constCalendar) < 10 ? "0"+c.get(constCalendar) : ""+c.get(constCalendar);
        return(aux);
    }
}
