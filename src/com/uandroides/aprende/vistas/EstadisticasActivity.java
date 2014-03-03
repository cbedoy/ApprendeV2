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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

import com.uandroides.aprende.R;
import com.uandroides.aprende.items.ItemEstadisticaRetroalimentacion;
import com.uandroides.aprende.modelos.Pregunta;

public class EstadisticasActivity extends Activity {

	
	public static EstadisticasActivity mthis;
	ArrayList<Pregunta>  preguntas;
	int cantidad;

	TextView puntos, calificacion, pregunta, aciertos, errores, vacias;
	private TextView fecha;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.push_up, R.anim.push_up);
		getActionBar().hide();
		setContentView(R.layout.activity_estadisticas);
		Bundle bundle = getIntent().getExtras();
	
		cantidad = bundle.getInt("cantidad");
		preguntas = ExamenActivity.mthis.preguntas;
		
		fecha = (TextView)findViewById(R.id.estadisticas_fecha);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String currentDateandTime = sdf.format(new Date());		
		
		fecha.setText(currentDateandTime);
		
		puntos = (TextView)findViewById(R.id.s_points);
		calificacion = (TextView)findViewById(R.id.s_rate);
		pregunta = (TextView)findViewById(R.id.s_numberOfQuestions);
		aciertos = (TextView)findViewById(R.id.s_successes);
		errores = (TextView)findViewById(R.id.s_erros);
		vacias = (TextView)findViewById(R.id.s_empty);
		Typeface tf = Typeface.createFromAsset(getAssets(),"font/dudu.ttf");
		puntos.setTypeface(tf);
		calificacion.setTypeface(tf);
		pregunta.setTypeface(tf);
		aciertos.setTypeface(tf);
		errores.setTypeface(tf);
		vacias.setTypeface(tf);
		fecha.setTypeface(tf);
		
		
		int aciertosCantidad=0, vaciasCantidad=0;
		for(Pregunta pregunta : preguntas){
			if(pregunta.getRespuestaCorrecta() == pregunta.getRespuestaUsuario())
				aciertosCantidad++;
			else if(pregunta.getRespuestaUsuario()==0)
				vaciasCantidad++;
		}
		
		
		float sPuntos = ((float)aciertosCantidad/(float)cantidad);
		
		puntos.setText(new DecimalFormat(".00").format(sPuntos*100));
		calificacion.setText(new DecimalFormat(".0").format(sPuntos*10));
		pregunta.setText(""+cantidad);
		aciertos.setText(""+aciertosCantidad);
		errores.setText(""+(cantidad-aciertosCantidad));
		vacias.setText("0");
	

		
		
		
		
		
		
		mthis = this;
		

		ListView lista = (ListView)findViewById(R.id.lista_resultados);

		lista.setAdapter(new ItemEstadisticaRetroalimentacion(this, preguntas));
		
		new Thread(new Runnable() {
	        public void run() {
	            try {
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            mthis.runOnUiThread(new Runnable() {
	                public void run() {
	                    mthis.startActivity(new Intent(mthis, PerfilActivity.class));
	                    mthis.finish();
	                }
	            });
	        }
	    }).start();
	}

	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.estadisticas, menu);
		return true;
	}
	

	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
       
	}


	public void setData(ArrayList<Pregunta> preguntas) {
		// TODO Auto-generated method stub
		this.preguntas = new ArrayList<Pregunta>();
		this.preguntas = preguntas;
		
		//Log.i("depu", "esta: "+preguntas.size());
		
	}





}