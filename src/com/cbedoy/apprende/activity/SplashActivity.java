package com.cbedoy.apprende.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cbedoy.apprende.R;

/**
 * Created by Carlos on 17/10/2014.
 */
public class SplashActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_splash_view);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, ApprendeActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}
