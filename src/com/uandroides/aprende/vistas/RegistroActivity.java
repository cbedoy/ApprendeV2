package com.uandroides.aprende.vistas;

import com.uandroides.aprende.R;
import com.uandroides.aprende.R.array;
import com.uandroides.aprende.R.id;
import com.uandroides.aprende.R.layout;
import com.uandroides.aprende.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class RegistroActivity extends Activity {

	AutoCompleteTextView estados, universidades;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registro);
		
		this.estados = (AutoCompleteTextView)findViewById(R.id.registro_estados);
		this.universidades = (AutoCompleteTextView)findViewById(R.id.registro_universidades);
		String[] estadosArray = getResources().getStringArray(R.array.estados);
		estados.setAdapter(new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, estadosArray));
		String[] universidadArray = getResources().getStringArray(R.array.universidades);
		estados.setAdapter(new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, universidadArray));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registro, menu);
		return true;
	}

}
