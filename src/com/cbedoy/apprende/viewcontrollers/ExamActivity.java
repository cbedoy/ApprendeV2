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
package com.cbedoy.apprende.viewcontrollers;

import java.util.ArrayList;
import java.util.Collections;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.cbedoy.apprende.fragments.ViewPagerAdapter;
import com.cbedoy.apprende.models.CuestionarioDemo;
import com.cbedoy.apprende.models.Pregunta;
import com.cbedoy.apprende.models.Tema;
import com.uandroides.aprende.R;

public class ExamActivity extends FragmentActivity {

	ViewPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	public static ExamActivity mthis;
	
	public ArrayList<Pregunta> preguntas;
	public SharedPreferences shared;
	private Tema tema;
	public  int cantidad;
	private CuestionarioDemo demo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exam_view);
		shared = this.getPreferences(MODE_PRIVATE);
		Bundle bundle = getIntent().getExtras();
		this.preguntas = new CuestionarioDemo().getCuestionario();
		cantidad = (bundle.getInt("dificultad"));
		Collections.shuffle(preguntas);
		
		cantidad = (cantidad==0)?5:(cantidad==1)?10:20;
		

		mthis = this;
		mSectionsPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		
		showMensajeDemo();
		

		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		ActionBar action = getActionBar();
		action.hide();
		
		
		
		new Thread(new Runnable() {
	        public void run() {
	            try {
					Thread.sleep(cantidad*500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            mthis.runOnUiThread(new Runnable() {
	                public void run() {
	                	showAlerta();
	                	           
	                }
	            });
	        }
	    }).start();
		
		demo = new CuestionarioDemo();
		
		
	}
	
	private void showMensajeDemo() {
		AlertDialog.Builder alerta = new AlertDialog.Builder(mthis);
		alerta.setMessage("Esta aplicacion es una version demo de la aplicacion, por lo cual el examen que realizara acontinuacion puede resultar facil o bien similar a uno que ya haya realizado");
		alerta.show();
	}

	private void showAlerta(){
		AlertDialog.Builder alerta = new AlertDialog.Builder(mthis);
    	alerta.setMessage("Tiempo agotado, el examen ha terminado");
    	alerta.setCancelable(false);
    	alerta.setNeutralButton("Finalizar", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				 mthis.startActivity(new Intent(mthis, StatisticsActivity.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.examen, menu);
		return true;
	}

	


	

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

	}

}
