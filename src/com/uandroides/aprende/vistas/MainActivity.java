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

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.uandroides.aprende.R;
import com.uandroides.aprende.interfaces.IModel;
import com.uandroides.aprende.interfaces.IRepresentationDelegate;
import com.uandroides.aprende.modelos.Usuario;
import com.uandroides.aprende.servicios.ServiceAllUsuarios;

public class MainActivity extends Activity implements IRepresentationDelegate{

	private ActionBar action;
	private EditText usuario_txt, password_txt;
	private Button botonLogin;
	private Intent intent;
	public SharedPreferences shared;
	public static MainActivity mthis;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		overridePendingTransition(R.anim.push_up, R.anim.push_up);
        
		mthis = this;
		action = getActionBar();
		action.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
		usuario_txt = (EditText)findViewById(R.id.usuario_txt);
		
		password_txt = (EditText)findViewById(R.id.contrasena_txt);
		
		botonLogin = (Button)findViewById(R.id.button1);
		botonLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				mthis.intent = new Intent(mthis, PerfilActivity.class);
				
				
				intent.putExtra("usuario", usuario_txt.getText().toString());
				intent.putExtra("contrasena", password_txt.getText().toString());
				mthis.startActivity(intent);
				finish();
			}
		});
		
		TextView link = (TextView)findViewById(R.id.sLink);
		link.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mthis.startActivity(new Intent(mthis, RegistroActivity.class));
			}
		});
		
		ServiceAllUsuarios task = new ServiceAllUsuarios(this);
		task.execute();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        
	}

	@Override
	public void ReloadData(ArrayList<IModel> modelo) {
		//TODO RELOAD DATA
		Log.i("parsed", modelo.size()+"");
		for(IModel model: modelo){
			Usuario user = (Usuario) model;
			
		}
	}

}
