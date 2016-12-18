package com.cmfs.resgenerator;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Logger.d(TAG, "onCreate() density = %s", metrics.density);
        Logger.d(TAG, "onCreate() densityDpi = %s", metrics.densityDpi);
        Logger.d(TAG, "onCreate() scaledDensity = %s", metrics.scaledDensity);

        Logger.d(TAG, "onCreate() widthPixels = %s", metrics.widthPixels);
        Logger.d(TAG, "onCreate() heightPixels = %s", metrics.heightPixels);

        Logger.d(TAG, "onCreate() xdpi = %s", metrics.xdpi);
        Logger.d(TAG, "onCreate() ydpi = %s", metrics.ydpi);

        // 根据所输出的参数配置ResGenerator，运行ResGenerator的main方法生成相应的文件


        Resources resources = getResources();
        Logger.d(TAG, "onCreate() 1dp = %s px", resources.getDimension(R.dimen.dp1));
        Logger.d(TAG, "onCreate() 10dp = %s px", resources.getDimension(R.dimen.dp10));
        Logger.d(TAG, "onCreate() 100dp = %s px", resources.getDimension(R.dimen.dp100));
    }


}
