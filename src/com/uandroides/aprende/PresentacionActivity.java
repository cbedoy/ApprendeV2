package com.uandroides.aprende;

import java.util.Timer;
import java.util.TimerTask;

import com.uandroides.aprende.vistas.SplashActivity;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class PresentacionActivity extends Activity {

	private long splashDelay = 1500;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_presentacion);
		ActionBar a = getActionBar();
		a.hide();
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		
		TimerTask task = new TimerTask() {
		      @Override
		      public void run() {
		        Intent mainIntent = new Intent().setClass(PresentacionActivity.this, SplashActivity.class);
		        startActivity(mainIntent);
		        finish();//Destruimos esta activity para prevenit que el usuario retorne aqui presionando el boton Atras.
		      }
		    };

		    Timer timer = new Timer();
		    timer.schedule(task, splashDelay);//Pasado los 6 segundos dispara la tarea
		  }



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
