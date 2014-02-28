package com.uandroides.aprende.fragments;

import java.util.ArrayList;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class PreguntaFragment extends Fragment {

	private RadioButton opcion1, opcion2, opcion3, opcion4;
	private TextView preg;
	
	private int posicion;
	public static final String ARG_SECTION_NUMBER = "section_number";

	private ArrayList<Pregunta> preguntas;
	private Application context;
	private int cantidad;
	
	public PreguntaFragment(Application context, ArrayList<Pregunta> preguntas) {
		this.context = context;
		this.preguntas = preguntas;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.cuestionario, container,
				false);
		


		ImageView imagen = (ImageView) rootView
				.findViewById(R.id.imagen_cuestionario);

		preg = (TextView) rootView
				.findViewById(R.id.pregunta_cuestionario);
		opcion1 = (RadioButton) rootView
				.findViewById(R.id.opcion1_cuestionario);
		opcion2 = (RadioButton) rootView
				.findViewById(R.id.opcion2_cuestionario);
		opcion3 = (RadioButton) rootView
				.findViewById(R.id.opcion3_cuestionario);
		opcion4 = (RadioButton) rootView
				.findViewById(R.id.opcion4_cuestionario);
		Button botonFinalizar = (Button) rootView
				.findViewById(R.id.botonFinalizar);

		posicion = getArguments().getInt(ARG_SECTION_NUMBER);

		if (posicion % 2 == 0)
			imagen.setVisibility(View.GONE);

		if (preguntas.get(posicion).getLink() == null || preguntas.get(posicion).getLink().equals("null")) {
			imagen.setVisibility(View.GONE);
		} else {
			imagen.setVisibility(View.VISIBLE);
		}

		if (posicion < cantidad-1) {
			botonFinalizar.setVisibility(View.INVISIBLE);

		} else {
			botonFinalizar.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(context,
							EstadisticasActivity.class);
					
					
					i.putExtra("cantidad", cantidad);
					i.putParcelableArrayListExtra("preguntas", preguntas);
					startActivity(i);
					//.finish();
					
					
				}
			});
		}

		preg.setText(preguntas.get(posicion).getPregunta());
		opcion1
				.setText(preguntas.get(posicion).getRespuesta1());
		opcion2
				.setText(preguntas.get(posicion).getRespuesta2());
		opcion3
				.setText(preguntas.get(posicion).getRespuesta3());
		opcion4
				.setText(preguntas.get(posicion).getRespuesta4());

		// mthis.setColorSeleccion();

		/*Typeface tf = Typeface.createFromAsset(mthis.getAssets(),
				"font/dudu.ttf");
		mthis.opcion1.setTypeface(tf);
		mthis.opcion2.setTypeface(tf);
		mthis.opcion3.setTypeface(tf);
		mthis.opcion4.setTypeface(tf);
		mthis.pregunta.setTypeface(tf);*/

		opcion1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				preguntas.get(posicion).setRespuestaUsuario(1);
				
			}
		});

		opcion2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				preguntas.get(posicion).setRespuestaUsuario(2);

			}
		});

		opcion3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				preguntas.get(posicion).setRespuestaUsuario(3);

			}
		});

		opcion4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				preguntas.get(posicion).setRespuestaUsuario(4);

			}
		});

		return rootView;

	}
}