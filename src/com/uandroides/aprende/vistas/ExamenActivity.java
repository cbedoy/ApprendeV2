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
import android.util.Log;
import android.view.Menu;

import com.uandroides.aprende.R;
import com.uandroides.aprende.fragments.PaginadoAdapter;
import com.uandroides.aprende.modelos.CuestionarioDemo;
import com.uandroides.aprende.modelos.Pregunta;
import com.uandroides.aprende.modelos.Tema;

public class ExamenActivity extends FragmentActivity {

	PaginadoAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	public static ExamenActivity mthis;
	
	public ArrayList<Pregunta> preguntas;
	public SharedPreferences shared;
	private Tema tema;
	public  int cantidad;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_examen);
		shared = this.getPreferences(MODE_PRIVATE);
		Bundle bundle = getIntent().getExtras();
		this.preguntas = new CuestionarioDemo().getCuestionario();
		cantidad = (bundle.getInt("dificultad"));
		Collections.shuffle(preguntas);
		if(cantidad==0)
			cantidad=5;
		else if(cantidad==1)
			cantidad=10;
		else 
			cantidad=20;
		
		Log.i("depu", ""+preguntas.size());
		Log.i("depu", "cantidad "+cantidad);
		mthis = this;
		mSectionsPagerAdapter = new PaginadoAdapter(
				getSupportFragmentManager());
		
		//MyAnsyTask tarea = new MyAnsyTask(mthis, 5, tema);
		//tarea.execute();
		mViewPager = (ViewPager) findViewById(R.id.pager);
		
		showMensajeDemo();
		

		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		ActionBar action = getActionBar();
		action.hide();
		
		
		
		new Thread(new Runnable() {
	        public void run() {
	            try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            mthis.runOnUiThread(new Runnable() {
	                public void run() {
	                	
	                	AlertDialog.Builder alerta = new AlertDialog.Builder(mthis);
	                	alerta.setMessage("Tiempo agotado, el examen ha terminado");
	                	alerta.setCancelable(false);
	                	alerta.setNeutralButton("Finalizar", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								 mthis.startActivity(new Intent(mthis, EstadisticasActivity.class));
							}
						});
	                   
	                }
	            });
	        }
	    }).start();
		
	}
	
	private void showMensajeDemo() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alerta = new AlertDialog.Builder(mthis);
		alerta.setMessage("Esta aplicacion es una version demo de la aplicacion, por lo cual el examen que realizara acontinuacion puede resultar facil o bien similar a uno que ya haya realizado");
		alerta.show();
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
