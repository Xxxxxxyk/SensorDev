package com.me.sensordev.view;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import com.me.sensordev.R;

import java.util.List;

/**
 * Created by 华农天时-Qiuzi on 2017/6/22.
 */

public class HardListActivity extends AppCompatActivity {

    private SensorManager sm;

    //获取手机基本状态及所有传感器
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardlist);
        TextView tv_brand = (TextView) findViewById(R.id.tv_brand);

        //手机号码不一定能获取到
        TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        tv_brand.setText("品牌: " + Build.BRAND + "\n" + "型号: " + Build.MODEL + "\n" + "Android版本: "
                + android.os.Build.VERSION.RELEASE + "\n" + "IMEI: " + tm.getDeviceId()
                + "\n" + "IMSI: " + tm.getSubscriberId() + "\n" + "手机号码: " + tm.getLine1Number() + "\n"
                + "运营商: " + tm.getSimOperatorName() + "\n");

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> allSensors = sm.getSensorList(Sensor.TYPE_ALL);// 获得传感器列表
        RecyclerView rl_list = (RecyclerView) findViewById(R.id.rv_list);
        rl_list.setLayoutManager(new LinearLayoutManager(this));
        rl_list.setAdapter(new SensorAdapter(this,allSensors));
    }
}
