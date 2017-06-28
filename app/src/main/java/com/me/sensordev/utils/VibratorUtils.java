package com.me.sensordev.utils;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by 惜梦哥哥_ on 2017/6/22.
 */

public class VibratorUtils {

    private final Vibrator mVibrator;

    //震动传感器
    public VibratorUtils(Context context){
        //获取系统服务
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    //震动,time为震动时间
    public void shock(long time){
        mVibrator.vibrate(time);
    }
}
