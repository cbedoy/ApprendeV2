package com.cbedoy.apprende.activity;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Handler;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.service.TypefaceService;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


/**
 * Created by Carlos on 18/10/2014.
 */
public class ApplicationLoader extends Application
{
    public static volatile Context mainContext = null;
    public static volatile Handler mainHandler = null;
    public static volatile DisplayImageOptions options;
    public static volatile ImageLoaderConfiguration config;
    @Override
    public void onCreate() {
        super.onCreate();
        mainContext = getApplicationContext();
        mainHandler = new Handler(mainContext.getMainLooper());
        TypefaceService.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Light.ttf");

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(5 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
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
