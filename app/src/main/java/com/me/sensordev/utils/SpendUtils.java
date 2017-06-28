package com.me.sensordev.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by 华农天时-Qiuzi on 2017/6/22.
 */

public class SpendUtils {

    private final SensorManager mSensorManager;
    private Sensor mMSensorManagerDefaultSensor;

    public SpendUtils(Context context){
        //SensorManager代表了各类传感器的集合
        mSensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        if (mSensorManager != null) {
            //加速传感器
            mMSensorManagerDefaultSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }

    public void shake(SensorEventListener sensorEventListener){
        if (mMSensorManagerDefaultSensor != null) {
            mSensorManager.registerListener(sensorEventListener, mMSensorManagerDefaultSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    /**
     * 解除注册
     * @param sensorEventListener
     */
    public void unregister(SensorEventListener sensorEventListener){
        mSensorManager.unregisterListener(sensorEventListener);
    }
}
