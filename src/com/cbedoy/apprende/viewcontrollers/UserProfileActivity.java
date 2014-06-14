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
package com.cbedoy.apprende.viewcontrollers;

import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cbedoy.apprende.items.StatisticsView;
import com.cbedoy.apprende.models.EstadisticasLateral;
import com.uandroides.aprende.R;

public class UserProfileActivity extends Activity implements AdapterView.OnItemClickListener {

	public static UserProfileActivity mthis;
    private DrawerLayout mDrawer;
    private SlidingPaneLayout mPanes;
    private static final String[] values = {"Matematicas", "Numeros", "Logica", "Ingles", "Historia", "Frances"};
    
    
	private String[] mListItems;
	public SharedPreferences shared;
	private TextView s_nombre, s_edad, s_ubicacion, s_escuela, s_rankin, s_puntos, s_numero_pruebas;
	private ImageView imagen;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        overridePendingTransition(R.anim.push_out, R.anim.push_out);
        Bundle extras = getIntent().getExtras();
         
        shared = this.getPreferences(MODE_PRIVATE);
        init();

        getActionBar().setDisplayHomeAsUpEnabled(true);
         this.setTitle("Perfil de usuario");
        
        mPanes = (SlidingPaneLayout) findViewById(R.id.slidingPane);
        
        
        mthis = this;
        ActionBar action = getActionBar();
		action.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
		ListView list = (ListView) findViewById(R.id.left_drawer);
		
		ArrayList<EstadisticasLateral> laterales = new ArrayList<EstadisticasLateral>();
		laterales.add(new EstadisticasLateral("11/NOVIEMBRE/13", "10", "8", "2", "Suma y resta", "80.0"));
		laterales.add(new EstadisticasLateral("12/NOVIEMBRE/13", "20", "15", "5", "Ingles I", "75.0"));
		laterales.add(new EstadisticasLateral("13/NOVIMEBRE/13", "50", "45", "5", "Examen Oracle - Primer parcial", "90.0"));
		laterales.add(new EstadisticasLateral("14/NOVIEMBRE/13", "20", "10", "10", "Investigacion de operaciones", "50.0"));
		laterales.add(new EstadisticasLateral("15/NOVIEMBRE/13", "10", "10", "10", "Compresion lectora", "10.0"));
        list.setAdapter(new StatisticsView(laterales, this));
        this.loadPicture(null, R.id.profile_picture);
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "Pulsado " + values[i], Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mthis, ExamActivity.class);
		intent.putExtra("nombre_tema", values[i]);
		startActivity(intent);
		mDrawer.closeDrawers();
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lateral, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            if (mPanes.isOpen()) {
	                closePane();
	            } else {
	                openPane();
	            }
	            return true;
	        case R.id.action_examenes:
	        	mthis.startActivity(new Intent(mthis, ThemesActivity.class));
	        	break;
	        case R.id.action_salir:
	        	mthis.finish();
	        	break;
	    }

	    return super.onOptionsItemSelected(item);
	}
	    
	private void openPane() {
        mPanes.openPane();
        
    }

    private void closePane() {
        mPanes.closePane();
        
    }

    @Override
	public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
   
	}



	public void convertirUsuario(JSONObject json) {
		try {
			JSONArray arreglo = json.getJSONArray("usuarios");
			for(int i=0; i<arreglo.length(); i++){
				JSONObject jobj = arreglo.getJSONObject(i);
				mthis.loadPicture(jobj.getString("foto"), R.id.profile_picture);
				mthis.s_nombre.setText(jobj.getString("nombre"));
				mthis.s_edad.setText(jobj.getString("edad")+" a�os");
				mthis.s_escuela.setText(jobj.getString("escuela"));
				mthis.s_puntos.setText(jobj.getString("puntaje")+" points");
				mthis.s_rankin.setText(jobj.getString("posicion")+" th lugar");
				
				
				
				mthis.s_numero_pruebas.setText(jobj.getString("numero_pruebas").toString()+" examenes");
				Log.i("miLog", json.getString("ubicacion"));
				mthis.s_ubicacion.setText(json.getString("ubicacion"));
				
				
				
				
				
				
			}
			} catch (JSONException e) {
			// TODO Auto-generated catch block
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
		
	}
	
	public void loadPicture(final String url_string, /*Context context,*/ final int id){
		new Thread(new Runnable(){
			Bitmap bmp;
			//ProgressDialog pd = ProgressDialog.show(mthis, "", "Cargando imagen...");
			@Override
			public void run() {
				try{
					URL url = new URL("https://scontent-a-pao.xx.fbcdn.net/hphotos-ash3/t1/533423_365041146938186_881277949_n.jpg");
					Log.i("miLog", ""+url.toString());
					bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
					mthis.runOnUiThread(new Runnable(){

						@Override
						public void run() {
							
							mthis.imagen.setImageBitmap(bmp);
							
						}});
						
				}catch(Exception e){
					e.printStackTrace();
				}
				//pd.dismiss();
			}
			
		}).start();
	
	
	
	
	}



	public void convertirTemas(JSONObject json) {
		// TODO Auto-generated method stub
		try {
			JSONArray arreglo = json.getJSONArray("temas");
			mthis.mListItems = new String[arreglo.length()];
			for(int i=0; i<arreglo.length(); i++){
				JSONObject jobj = arreglo.getJSONObject(i);
				mthis.mListItems[i] = jobj.getString("nombre"); 
				
			}
			} catch (JSONException e) {
			// TODO Auto-generated catch block
			
		}
		
		
		
        ListView list = (ListView) findViewById(R.id.left_drawer);
        
        

	
	
	
	}
	
	private void init(){
		Typeface tf = Typeface.createFromAsset(getAssets(),"font/dudu.ttf");
		
		s_nombre = (TextView)findViewById(R.id.profile_username);
        s_edad = (TextView)findViewById(R.id.profile_age);
        s_ubicacion = (TextView)findViewById(R.id.location);
        s_escuela = (TextView)findViewById(R.id.profile_university);
        s_numero_pruebas = (TextView)findViewById(R.id.profile_plays);
        s_rankin = (TextView)findViewById(R.id.profile_position);
        s_puntos = (TextView)findViewById(R.id.profile_points);
        imagen = (ImageView)findViewById(R.id.profile_picture);
        
        s_nombre.setTypeface(tf);
        s_edad.setTypeface(tf);
        s_ubicacion.setTypeface(tf);
        s_escuela.setTypeface(tf);
        s_numero_pruebas.setTypeface(tf);
        s_rankin.setTypeface(tf);
        s_puntos.setTypeface(tf);
        
        
        s_nombre.setText("Esmeralda Hernandez Ruiz");
        s_edad.setText("27 a�os");
        s_ubicacion.setText("Aguascalientes");
        s_escuela.setText("Universidad Autonoma De Aguascalientes");
        s_numero_pruebas.setText("5 pruebas");
        s_rankin.setText("1th Lugar");
        s_puntos.setText("85.9 puntos");
	}

}
