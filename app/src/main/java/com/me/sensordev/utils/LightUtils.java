package com.me.sensordev.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by 光线传感器 on 2017/6/22.
 */

public class LightUtils {
    private final Sensor mDefaultSensor;
    private final SensorManager mSystemService;

    //光线传感器

    public LightUtils(Context context) {
        mSystemService = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        mDefaultSensor = mSystemService.getDefaultSensor(Sensor.TYPE_LIGHT);

    }

    public void getLightNum(SensorEventListener sensorEventListener) {
        mSystemService.registerListener(sensorEventListener, mDefaultSensor, SensorManager.SENSOR_DELAY_UI);
    }

    /**
     * 解除注册
     * @param sensorEventListener
     */
    public void unregister(SensorEventListener sensorEventListener){
        mSystemService.unregisterListener(sensorEventListener);
    }
}
