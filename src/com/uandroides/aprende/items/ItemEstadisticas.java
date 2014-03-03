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
package com.uandroides.aprende.items;

import java.util.ArrayList;

import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uandroides.aprende.R;
import com.uandroides.aprende.modelos.EstadisticasLateral;

public class ItemEstadisticas extends BaseAdapter {

	private TextView fecha;
	private TextView titulo;
	private TextView preguntas;
	private TextView aciertos;
	private TextView errores;
	private TextView puntos;
	private ArrayList<EstadisticasLateral> data;
	private Activity contexto;

	public ItemEstadisticas(ArrayList<EstadisticasLateral> data, Activity contexto) {
		this.data = data;
		this.contexto = contexto;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int index) {
		return index;
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(final int position, View convertView,
			ViewGroup viewGroup) {

		if (convertView == null) {
			LayoutInflater inflate = (LayoutInflater) contexto
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View vistaView = inflate.inflate(R.layout.item_lateral, null);

			convertView = vistaView;
		}

		if (convertView != null) {
			LinearLayout layout = (LinearLayout) convertView;
			
			Typeface tf = Typeface.createFromAsset(contexto.getAssets(),"font/dudu.ttf");
			
			
			fecha = (TextView) layout.findViewById(R.id.textView1);
			titulo = (TextView) layout.findViewById(R.id.textView2);
			preguntas = (TextView) layout.findViewById(R.id.textView3);
			aciertos = (TextView) layout.findViewById(R.id.textView4);
			errores = (TextView) layout.findViewById(R.id.textView5);
			puntos = (TextView) layout.findViewById(R.id.textView6);
			
			fecha.setTypeface(tf);
			titulo.setTypeface(tf);
			preguntas.setTypeface(tf);
			aciertos.setTypeface(tf);
			errores.setTypeface(tf);
			puntos.setTypeface(tf);

			fecha.setText(data.get(position).getFecha());
			titulo.setText(data.get(position).getTitulo().toUpperCase());
			preguntas.setText("NUMERO DE REACTIVOS: "
					+ data.get(position).getPreguntas());
			aciertos.setText("NUMERO DE ACIERTOS: "
					+ data.get(position).getAciertos());
			errores.setText("NUMERO DE ERRORES: "
					+ data.get(position).getErrores());
			puntos.setText("NUMERO DE PUNTOS: "
					+ data.get(position).getPuntos());

			layout.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					AlertDialog.Builder dialogo = new AlertDialog.Builder(
							contexto);
					
					
					LayoutInflater inflater = contexto.getLayoutInflater();
					View view = inflater.inflate(R.layout.dialog_detalle, null);
					TextView titulo = (TextView) view
							.findViewById(R.id.dialogo_titulo);
					TextView preguntas = (TextView) view
							.findViewById(R.id.dialogo_reactivos);
					TextView puntos = (TextView) view
							.findViewById(R.id.dialogo_puntuacion);
					TextView fecha = (TextView) view
							.findViewById(R.id.dialogo_fecha);
					Button share = (Button) view.findViewById(R.id.button1);
					titulo.setText(data.get(position).getTitulo().toUpperCase());
					preguntas.setText(data.get(position).getPreguntas());
					puntos.setText(data.get(position).getPuntos());
					fecha.setText(data.get(position).getFecha());
					
					Typeface tf = Typeface.createFromAsset(contexto.getAssets(),"font/dudu.ttf");
					titulo.setTypeface(tf);
					preguntas.setTypeface(tf);
					puntos.setTypeface(tf);
					fecha.setTypeface(tf);
					share.setTypeface(tf);
					
					share.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							String mensaje = "Mi calificacion usando APPrende:\n";
							mensaje += ""
									+ data.get(position).getTitulo()
											.toUpperCase() + "\n"
									+ "NUMERO DE REACTIVOS: \n"
									+ data.get(position).getPreguntas() + ""
									+ "NUMERO DE PUNTOS: \n"
									+ data.get(position).getPuntos();
							Intent share = new Intent(Intent.ACTION_SEND);
							share.setType("text/plain");
							share.putExtra(Intent.EXTRA_TEXT, mensaje);

							contexto.startActivity(Intent.createChooser(share,
									"Compartir"));
						}
					});
					dialogo.setView(view);

					dialogo.show();
				}
			});

		}
		return convertView;

	}

}
