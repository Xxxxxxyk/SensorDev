package com.me.sensordev.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by 惜梦哥哥_ on 2017/6/22.
 */

public class FlashLampUtils {

    //闪光灯
    private final CameraManager mSystemService;

    public FlashLampUtils(Context context) {
        //获取系统服务
        mSystemService = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void openLamp(){
        if(!isLOLLIPOP()){
            return;
        }
        try {
            mSystemService.setTorchMode("0", true);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void closeLamp(){
        if(!isLOLLIPOP()){
            return;
        }
        try {
            mSystemService.setTorchMode("0", false);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断Android系统版本
     *
     * @return boolean
     */
    private boolean isLOLLIPOP() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return true;
        } else {
            return false;
        }
    }
}
