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
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.uandroides.aprende.R;

import com.uandroides.aprende.R.id;
import com.uandroides.aprende.R.layout;
import com.uandroides.aprende.R.menu;
import com.uandroides.aprende.R.string;
import com.uandroides.aprende.controladores.MyAnsyTask;
import com.uandroides.aprende.fragments.SectionsPagerAdapter;
import com.uandroides.aprende.modelos.Tema;
import com.uandroides.aprende.modelos.Pregunta;
import com.uandroides.aprende.modelos.CuestionarioDemo;
import com.uandroides.aprende.modelos.Serializador;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class ExamenActivity extends FragmentActivity {

	SectionsPagerAdapter mSectionsPagerAdapter;
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
		//tema = new APPTema();
		//tema.setNombre(bundle.getString("nombre_tema"));
		//tema.setId(bundle.getInt("idtema"));
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
		mSectionsPagerAdapter = new SectionsPagerAdapter(
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

	public void convertirPreguntas(JSONObject json) {
		try {
			JSONArray array = json.getJSONArray("preguntas");
			this.preguntas = new ArrayList<Pregunta>();
			Pregunta pregunta;
			for (int i = 0; i < array.length(); i++) {
				JSONObject jobj = array.getJSONObject(i);
				pregunta = new Pregunta();
				pregunta.setIdPregunta(jobj.getInt("id"));
				pregunta.setPregunta(jobj.getString("pregunta"));
				pregunta.setRespuesta1(jobj.getString("respuesta1"));
				pregunta.setRespuesta2(jobj.getString("respuesta2"));
				pregunta.setRespuesta3(jobj.getString("respuesta3"));
				pregunta.setRespuesta4(jobj.getString("respuesta4"));
				pregunta.setRespuestaCorrecta(jobj.getInt("correcta"));
				pregunta.setLink(jobj.getString("recurso"));
				
				Log.i("examen", ".........................................");
				Log.i("examen", ""+pregunta.getIdPregunta());
				Log.i("examen", ""+pregunta.getPregunta());
				Log.i("examen", ""+pregunta.getRespuesta1());
				Log.i("examen", ""+pregunta.getRespuesta2());
				Log.i("examen", ""+pregunta.getRespuesta3());
				Log.i("examen", ""+pregunta.getRespuesta4());
				Log.i("examen", ""+pregunta.getLink());
				
				this.preguntas.add(pregunta);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.i("examen", "tama�o "+preguntas.size());
		
		mViewPager.setAdapter(mSectionsPagerAdapter);
	}

}
