//--------------------------------------------
//
//	Apprende
//  Develop by Carlos Alfredo Cervantes Bedoy
//
//	Android Developer
//
//	Independent project:	carlos.bedoy@gmail.com
//
//	Aguascalientes | Mexico
//-------------------------------------------------------
package com.uandroides.aprende.vistas;

import java.util.Timer;
import java.util.TimerTask;

import com.uandroides.aprende.R;
import com.uandroides.aprende.R.layout;
import com.uandroides.aprende.R.menu;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SplashActivity extends Activity {

	private long splashDelay = 3000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		ActionBar a = getActionBar();
		a.hide();
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		
		
		TimerTask task = new TimerTask() {
		      @Override
		      public void run() {
		        Intent mainIntent = new Intent().setClass(SplashActivity.this, MainActivity.class);
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
