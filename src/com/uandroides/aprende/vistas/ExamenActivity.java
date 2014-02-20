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
import com.uandroides.aprende.modelos.APPTema;
import com.uandroides.aprende.modelos.APPregunta;
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
	private RadioButton opcion1, opcion2, opcion3, opcion4;
	private TextView pregunta;
	public ArrayList<APPregunta> preguntas;
	public SharedPreferences shared;
	private APPTema tema;
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

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = new Pregunta();
			Bundle args = new Bundle();
			args.putInt(Pregunta.ARG_SECTION_NUMBER, position);
			fragment.setArguments(args);
			Log.i("depu", ""
					+position);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return cantidad;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return null;

		}
	}


	public static class Pregunta extends Fragment {

		private int posicion;
		public static final String ARG_SECTION_NUMBER = "section_number";

		public Pregunta() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.cuestionario, container,
					false);
			


			ImageView imagen = (ImageView) rootView
					.findViewById(R.id.imagen_cuestionario);

			mthis.pregunta = (TextView) rootView
					.findViewById(R.id.pregunta_cuestionario);
			mthis.opcion1 = (RadioButton) rootView
					.findViewById(R.id.opcion1_cuestionario);
			mthis.opcion2 = (RadioButton) rootView
					.findViewById(R.id.opcion2_cuestionario);
			mthis.opcion3 = (RadioButton) rootView
					.findViewById(R.id.opcion3_cuestionario);
			mthis.opcion4 = (RadioButton) rootView
					.findViewById(R.id.opcion4_cuestionario);
			Button botonFinalizar = (Button) rootView
					.findViewById(R.id.botonFinalizar);

			posicion = getArguments().getInt(ARG_SECTION_NUMBER);

			if (posicion % 2 == 0)
				imagen.setVisibility(View.GONE);

			if (mthis.preguntas.get(posicion).getLink() == null || mthis.preguntas.get(posicion).getLink().equals("null")) {
				imagen.setVisibility(View.GONE);
			} else {
				imagen.setVisibility(View.VISIBLE);
			}

			if (posicion < mthis.cantidad-1) {
				botonFinalizar.setVisibility(View.INVISIBLE);

			} else {
				botonFinalizar.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(mthis,
								EstadisticasActivity.class);
						
						
						i.putExtra("cantidad", mthis.cantidad);
						i.putParcelableArrayListExtra("preguntas", mthis.preguntas);
						mthis.startActivity(i);
						mthis.finish();
						
						
					}
				});
			}

			mthis.pregunta.setText(mthis.preguntas.get(posicion).getPregunta());
			mthis.opcion1
					.setText(mthis.preguntas.get(posicion).getRespuesta1());
			mthis.opcion2
					.setText(mthis.preguntas.get(posicion).getRespuesta2());
			mthis.opcion3
					.setText(mthis.preguntas.get(posicion).getRespuesta3());
			mthis.opcion4
					.setText(mthis.preguntas.get(posicion).getRespuesta4());

			// mthis.setColorSeleccion();

			/*Typeface tf = Typeface.createFromAsset(mthis.getAssets(),
					"font/dudu.ttf");
			mthis.opcion1.setTypeface(tf);
			mthis.opcion2.setTypeface(tf);
			mthis.opcion3.setTypeface(tf);
			mthis.opcion4.setTypeface(tf);
			mthis.pregunta.setTypeface(tf);*/

			mthis.opcion1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mthis.preguntas.get(posicion).setRespuestaUsuario(1);
					
				}
			});

			mthis.opcion2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mthis.preguntas.get(posicion).setRespuestaUsuario(2);

				}
			});

			mthis.opcion3.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mthis.preguntas.get(posicion).setRespuestaUsuario(3);

				}
			});

			mthis.opcion4.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mthis.preguntas.get(posicion).setRespuestaUsuario(4);

				}
			});

			return rootView;

		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

	}

	public void convertirPreguntas(JSONObject json) {
		try {
			JSONArray array = json.getJSONArray("preguntas");
			this.preguntas = new ArrayList<APPregunta>();
			APPregunta pregunta;
			for (int i = 0; i < array.length(); i++) {
				JSONObject jobj = array.getJSONObject(i);
				pregunta = new APPregunta();
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
