package com.cbedoy.apprende.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;

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
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.background_splash);
        ColorDrawable[] color = {new ColorDrawable(Color.parseColor("#012423")),
                                 new ColorDrawable(Color.parseColor("#b9d474"))};
        TransitionDrawable trans = new TransitionDrawable(color);
        relativeLayout.setBackgroundDrawable(trans);
        trans.startTransition(2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, ApprendeActivity.class);
                intent.putExtra("image_service", "login_blur");
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
