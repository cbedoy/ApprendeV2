package com.cbedoy.apprende;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SplashView extends Activity {

	private long splashDelay = 3000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.splash_view);
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		TimerTask task = new TimerTask() {
		      @Override
		      public void run() {
		        Intent mainIntent = new Intent().setClass(SplashView.this, LoginView.class);
		        startActivity(mainIntent);
		        finish();
		      }
		};

		Timer timer = new Timer();
		timer.schedule(task, splashDelay);
	}

}
