package com.uandroides.aprende.fragments;

import java.util.ArrayList;

import android.app.Application;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.uandroides.aprende.R;
import com.uandroides.aprende.modelos.Pregunta;
import com.uandroides.aprende.vistas.EstadisticasActivity;
import com.uandroides.aprende.vistas.ExamenActivity;

public class PreguntaFragment extends Fragment {

	private RadioButton opcion1;
	private RadioButton opcion2;
	private RadioButton opcion3;
	private RadioButton opcion4;
	private TextView preg;
	private int posicion;
	private ArrayList<Pregunta> preguntas;
	public static final String ARG_SECTION_NUMBER = "section_number";

	private int cantidad;
	


	public PreguntaFragment() {
		// TODO Auto-generated constructor stub
		
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.cuestionario, container,false);
		
		
		preguntas = ExamenActivity.mthis.preguntas;
		
		
		Log.i("Depu", "Posicion en el fragment: "+posicion);

		ImageView imagen = (ImageView) rootView.findViewById(R.id.imagen_cuestionario);

		preg = (TextView) rootView.findViewById(R.id.pregunta_cuestionario);
		opcion1 = (RadioButton) rootView.findViewById(R.id.opcion1_cuestionario);
		opcion2 = (RadioButton) rootView.findViewById(R.id.opcion2_cuestionario);
		opcion3 = (RadioButton) rootView.findViewById(R.id.opcion3_cuestionario);
		opcion4 = (RadioButton) rootView.findViewById(R.id.opcion4_cuestionario);
		Button botonFinalizar = (Button) rootView.findViewById(R.id.botonFinalizar);

		posicion = getArguments().getInt(ARG_SECTION_NUMBER);
		cantidad = getArguments().getInt("size");
		
		if (posicion % 2 == 0)
			imagen.setVisibility(View.GONE);

		imagen.setVisibility((preguntas.get(posicion).getLink() == null || preguntas.get(posicion).getLink().equals("null"))?View.GONE:View.INVISIBLE);
	

		if (posicion < cantidad-1) {
			botonFinalizar.setVisibility(View.INVISIBLE);

		} else {
			botonFinalizar.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					showNewActivity();
				}

				private void showNewActivity() {
					Intent i = new Intent(getActivity(),EstadisticasActivity.class);
					i.putExtra("cantidad", cantidad);
					startActivity(i);
					
				}
			});
		}

		preg.setText(preguntas.get(posicion).getPregunta());
		opcion1.setText("A) "+preguntas.get(posicion).getRespuesta1());
		opcion2.setText("B) "+preguntas.get(posicion).getRespuesta2());		
		opcion3.setText("C) "+preguntas.get(posicion).getRespuesta3());
		opcion4.setText("D) "+preguntas.get(posicion).getRespuesta4());


		Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"font/dudu.ttf");
		opcion1.setTypeface(tf);
		opcion2.setTypeface(tf);
		opcion3.setTypeface(tf);
		opcion4.setTypeface(tf);
		preg.setTypeface(tf);

		opcion1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				preguntas.get(posicion).setRespuestaUsuario(1);
			}
		});

		opcion2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				preguntas.get(posicion).setRespuestaUsuario(2);
			}
		});

		opcion3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				preguntas.get(posicion).setRespuestaUsuario(3);
			}
		});

		opcion4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				preguntas.get(posicion).setRespuestaUsuario(4);
			}
		});

		return rootView;

	}
}