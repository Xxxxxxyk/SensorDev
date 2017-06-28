package com.me.sensordev.view;

import android.Manifest;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.me.sensordev.R;
import com.me.sensordev.utils.FingerPrintUtils;
import com.me.sensordev.utils.FlashLampUtils;
import com.me.sensordev.utils.LightUtils;
import com.me.sensordev.utils.SpendUtils;
import com.me.sensordev.utils.VibratorUtils;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener, EasyPermissions.PermissionCallbacks {

    private static final int READ_PHONE = 1;
    private FlashLampUtils mFlashLampUtils;
    private Button mBtn_lamp;
    private boolean FLAG = false;
    private FingerPrintUtils mFingerPrintUtils;
    private Button mBtn_light;
    private LightUtils mLightUtils;
    private VibratorUtils mVibratorUtils;
    private SpendUtils mSpendUtils;
    private static final int UPTATE_INTERVAL_TIME = 50;
    //灵敏度调节
    private static final int SPEED_SHRESHOLD = 30;
    private long lastUpdateTime;
    private float lastX;
    private float lastY;
    private float lastZ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn_lamp = (Button) findViewById(R.id.btn_lamp);
        mBtn_lamp.setOnClickListener(this);
        findViewById(R.id.btn_finger).setOnClickListener(this);
        mBtn_light = (Button) findViewById(R.id.btn_light);
        findViewById(R.id.btn_shock).setOnClickListener(this);
        findViewById(R.id.btn_hard_list).setOnClickListener(this);
        findViewById(R.id.btn_shake).setOnClickListener(this);


        mFlashLampUtils = new FlashLampUtils(this);
        mFingerPrintUtils = new FingerPrintUtils(this);

        //获取亮度数值勒克斯
        mLightUtils = new LightUtils(this);
        mLightUtils.getLightNum(this);

        mVibratorUtils = new VibratorUtils(this);
        EasyPermissions.requestPermissions(this, "请允许读取手机状态", READ_PHONE, Manifest.permission.READ_PHONE_STATE);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_lamp:
                if (FLAG) {
                    mBtn_lamp.setText("开启闪光灯");
                    mFlashLampUtils.closeLamp();
                    FLAG = false;
                } else {
                    mBtn_lamp.setText("关闭闪光灯");
                    mFlashLampUtils.openLamp();
                    FLAG = true;
                }
                break;
            case R.id.btn_finger:
                mFingerPrintUtils.checkFingerPrint();
                break;
            case R.id.btn_shock:
                mVibratorUtils.shock(3000);
                break;
            case R.id.btn_hard_list:
                if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_PHONE_STATE)) {
                    startActivity(new Intent(this, HardListActivity.class));
                } else {
                    Toast.makeText(this, "未赋予读取手机状态权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_shake:
                //摇一摇
                mSpendUtils = new SpendUtils(this);
                mSpendUtils.shake(this);
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int type = sensorEvent.sensor.getType();
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_LIGHT:
                mBtn_light.setText("当前亮度" + sensorEvent.values[0]);
                break;
            case Sensor.TYPE_ACCELEROMETER:

                shake(sensorEvent);
                break;

            default:
                break;
        }
    }

    private void shake(SensorEvent sensorEvent) {
        long currentUpdateTime = System.currentTimeMillis();
        long timeInterval = currentUpdateTime - lastUpdateTime;
        if (timeInterval < UPTATE_INTERVAL_TIME) {
            return;
        }
        lastUpdateTime = currentUpdateTime;
        // 传感器信息改变时执行该方法
        float[] values = sensorEvent.values;
        float x = values[0]; // x轴方向的重力加速度，向右为正
        float y = values[1]; // y轴方向的重力加速度，向前为正
        float z = values[2]; // z轴方向的重力加速度，向上为正
        float deltaX = x - lastX;
        float deltaY = y - lastY;
        float deltaZ = z - lastZ;

        lastX = x;
        lastY = y;
        lastZ = z;
        double speed = (Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ) / timeInterval) * 100;
        if (speed >= SPEED_SHRESHOLD) {
            mVibratorUtils.shock(2000);
            Toast.makeText(this, "摇一摇震动", Toast.LENGTH_SHORT).show();
            mSpendUtils.unregister(this);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }


    @Override
    protected void onPause() {
        mLightUtils.unregister(this);
        mSpendUtils.unregister(this);
        super.onPause();
    }

}
