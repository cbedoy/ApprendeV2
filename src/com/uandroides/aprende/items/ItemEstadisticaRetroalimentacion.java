package com.uandroides.aprende.items;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uandroides.aprende.R;
import com.uandroides.aprende.modelos.Pregunta;

public class ItemEstadisticaRetroalimentacion extends BaseAdapter{

	private Activity context;
	private ArrayList<Pregunta> preguntas;
	
	public ItemEstadisticaRetroalimentacion(Activity context, ArrayList<Pregunta> preguntas){
		this.context = context;
		this.preguntas = preguntas;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return preguntas.size();
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
			LayoutInflater inflate = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
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
					showMensaje(position);
				}

				private void showMensaje(int position) {
					AlertDialog.Builder mensaje = new AlertDialog.Builder(context);
					mensaje.setMessage("Reactivo #"+(position+1));
					mensaje.setMessage("Tu respuesta:\n"+preguntas.get(position).getSeleccionUsuario());
					mensaje.setMessage("\nRetroalimentacion:\n"+preguntas.get(position).getRetroalimentacion());
					mensaje.setNeutralButton("OK", null);
					mensaje.show();
					
				}
			});
		}
		return convertView;
	}
	
}