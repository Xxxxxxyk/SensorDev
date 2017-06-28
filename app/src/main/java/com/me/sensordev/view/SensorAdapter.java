package com.me.sensordev.view;

import android.content.Context;
import android.hardware.Sensor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 惜梦哥哥_ on 2017/6/22.
 */

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.SensorViewHolder> {

    private final Context context;
    private final List<Sensor> sensors;

    public SensorAdapter(Context context, List<Sensor> sensors) {
        this.context = context;
        this.sensors = sensors;
    }

    @Override
    public SensorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SensorViewHolder viewHolder = new SensorViewHolder(LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SensorViewHolder holder, int position) {
        holder.mTv_text.setText("类型:" + getChineseName(sensors.get(position).getType()) + "\n" + "设备名称:" + sensors.get(position).getName() + "\n设备版本" + sensors.get(position).getVersion() + "\n");
    }

    @Override
    public int getItemCount() {
        return sensors == null ? 0 : sensors.size();
    }

    class SensorViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTv_text;

        public SensorViewHolder(View itemView) {
            super(itemView);
            mTv_text = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }

    public String getChineseName(int type) {
        switch (type) {
            case Sensor.TYPE_ACCELEROMETER:
                return "加速度传感器";
            case Sensor.TYPE_GYROSCOPE:
                return "陀螺仪传感器";
            case Sensor.TYPE_LIGHT:
                return "环境光线传感器";
            case Sensor.TYPE_MAGNETIC_FIELD:
                return "电磁场传感器";
            case Sensor.TYPE_ORIENTATION:
                return "方向传感器";
            case Sensor.TYPE_PRESSURE:
                return "压力传感器";
            case Sensor.TYPE_PROXIMITY:
                return "距离传感器";
            case Sensor.TYPE_TEMPERATURE:
                return "温度传感器";
            case Sensor.TYPE_GRAVITY:
                return "重场传感器";
            case Sensor.TYPE_LINEAR_ACCELERATION:
                return "线性加速度传感器";
            case Sensor.TYPE_ROTATION_VECTOR:
                return "旋转矢量传感器";
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                return "湿度传感器";
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                return "温度传感器";
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                return "游戏旋转矢量传感器";
            case Sensor.TYPE_STEP_COUNTER:
                return "计步器";
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                return "地磁旋转矢量传感器";
            case Sensor.TYPE_SIGNIFICANT_MOTION:
                return "特殊动作触发传感器";
            default:
                return "未知传感器";
        }
    }
}
