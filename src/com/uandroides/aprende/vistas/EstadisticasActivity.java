package com.uandroides.aprende.vistas;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.uandroides.aprende.R;
import com.uandroides.aprende.R.color;
import com.uandroides.aprende.R.id;
import com.uandroides.aprende.R.layout;
import com.uandroides.aprende.R.menu;
import com.uandroides.aprende.modelos.APPregunta;
import com.uandroides.aprende.modelos.Serializador;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class EstadisticasActivity extends Activity {

	
	public static EstadisticasActivity mthis;
	ArrayList<APPregunta>  preguntas;
	int cantidad;
	MyAdaptador adapter;
	Serializador dataSerializada;
	TextView puntos, calificacion, pregunta, aciertos, errores, vacias;
	private TextView fecha;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.push_up, R.anim.push_up);
		
		setContentView(R.layout.activity_estadisticas);
		ActionBar action = getActionBar();
		action.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
		Bundle bundle = getIntent().getExtras();
	
		cantidad = bundle.getInt("cantidad");
		preguntas = bundle.getParcelableArrayList("preguntas");
		
		fecha = (TextView)findViewById(R.id.estadisticas_fecha);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String currentDateandTime = sdf.format(new Date());		
		
		fecha.setText(currentDateandTime);
		
		puntos = (TextView)findViewById(R.id.s_points);
		calificacion = (TextView)findViewById(R.id.s_rate);
		pregunta = (TextView)findViewById(R.id.s_numberOfQuestions);
		aciertos = (TextView)findViewById(R.id.s_successes);
		errores = (TextView)findViewById(R.id.s_erros);
		vacias = (TextView)findViewById(R.id.s_empty);
		
		int aciertosCantidad=0, vaciasCantidad=0;
		for(int i=0; i<cantidad; i++){
			APPregunta pregunta = preguntas.get(i);
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

		lista.setAdapter(new MyAdaptador());
		
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

	
	public void showMensaje(int index){
		AlertDialog.Builder mensaje = new AlertDialog.Builder(mthis);
		mensaje.setMessage("Reactivo #"+(index+1));
		mensaje.setMessage("Tu respuesta:\n"+preguntas.get(index).getSeleccionUsuario());
		mensaje.setMessage("\nRetroalimentacion:\n"+preguntas.get(index).getRetroalimentacion());
		mensaje.setNeutralButton("OK", null);
		mensaje.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.estadisticas, menu);
		return true;
	}
	
	private class MyAdaptador extends BaseAdapter{

		public MyAdaptador(){
			
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return cantidad;
		}

		@Override
		public Object getItem(int index) {
			// TODO Auto-generated method stub
			return index;
		}

		@Override
		public long getItemId(int index) {
			// TODO Auto-generated method stub
			return index;
		}

		@Override
		public View getView(final int position, View convertView, 
				ViewGroup viewGroup) {
			
			if(convertView == null){
				LayoutInflater inflate = (LayoutInflater)mthis.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
				View vistaView  = inflate.inflate(R.layout.inflate_estadisticas, null);
				
				convertView = vistaView;
			}
			
			if(convertView != null){
				LinearLayout layout = (LinearLayout)convertView; 
				
				Button boton = (Button)layout.findViewById(R.id.item_boton);
				
				TextView pregunta = (TextView)layout.findViewById(R.id.item_contenido);
				pregunta.setText(preguntas.get(position).getPregunta());
				TextView respuesta = (TextView)layout.findViewById(R.id.item_respuesta_usuario);
				respuesta.setText(preguntas.get(position).getSeleccionUsuario());
				TextView foco = (TextView)layout.findViewById(R.id.item_valor);
				
				
				if(preguntas.get(position).getRespuestaCorrecta() == preguntas.get(position).getRespuestaUsuario()){
					foco.setBackgroundResource(R.color.correcto);
					foco.setText("  CORRECTO  ");
				}else {
					foco.setBackgroundResource(R.color.error);
					foco.setText(" INCORRECTO ");
				}
				
				
				boton.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						mthis.showMensaje(position);
					}
				});
			}
			return convertView;
		}
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
       
	}


	public void setData(ArrayList<APPregunta> preguntas) {
		// TODO Auto-generated method stub
		this.preguntas = new ArrayList<APPregunta>();
		this.preguntas = preguntas;
		
		//Log.i("depu", "esta: "+preguntas.size());
		
	}





}