package com.cbedoy.apprende.activity;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;

import com.cbedoy.apprende.service.TypefaceService;


/**
 * Created by Carlos on 18/10/2014.
 */
public class ApplicationLoader extends Application
{
    public static volatile Context mainContext = null;
    public static volatile Handler mainHandler = null;
    @Override
    public void onCreate() {
        super.onCreate();
        mainContext = getApplicationContext();
        mainHandler = new Handler(mainContext.getMainLooper());
        TypefaceService.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Regular.ttf");
    }

    public static int getAppVersion() {
        try {
            PackageInfo packageInfo = mainContext.getPackageManager().getPackageInfo(mainContext.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Could not get package name: " + e);
        }
    }
}
