package com.me.sensordev.utils;

import android.content.Context;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.util.Log;

/**
 * Created by 惜梦哥哥_ on 2017/6/22.
 */

public class FingerPrintUtils {

    //指纹传感器
    public static final String TAG = "惜梦哥哥_";
    private final FingerprintManagerCompat mManagerCompat;

    public FingerPrintUtils(Context context) {
        mManagerCompat = FingerprintManagerCompat.from(context);
    }

    public void checkFingerPrint(){
        /**
         * 检查是否支持指纹识别
         */
        if(mManagerCompat.hasEnrolledFingerprints()){
            mManagerCompat.authenticate(null, 0, null, new FingerprintManagerCompat.AuthenticationCallback() {
                /**
                 *  出现错误回调,多次尝试失败也会调用
                 */
                @Override
                public void onAuthenticationError(int errMsgId, CharSequence errString) {
                    Log.e(TAG,errMsgId + "--------" + errString.toString());
                }

                /**
                 *  错误信息提示
                 */
                @Override
                public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                    Log.e(TAG,helpMsgId + "--------" + helpString.toString());
                }

                /**
                 *  当验证的指纹成功时会回调此函数，然后取消监听
                 */
                @Override
                public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                    Log.e(TAG,"验证成功");
                }

                /**
                 * 指纹验证失败
                 */
                @Override
                public void onAuthenticationFailed() {
                    Log.e(TAG,"验证失败");;
                }
            }, null);
        };
    }
}
