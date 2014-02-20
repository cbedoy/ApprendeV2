package com.uandroides.aprende.vistas;

import java.net.URL;
import java.util.ArrayList;

import com.uandroides.aprende.modelos.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.uandroides.aprende.R;
import com.uandroides.aprende.R.id;
import com.uandroides.aprende.R.layout;
import com.uandroides.aprende.R.menu;
import com.uandroides.aprende.modelos.APPLateral;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilActivity extends Activity implements AdapterView.OnItemClickListener {

	public static PerfilActivity mthis;
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
        s_nombre = (TextView)findViewById(R.id.perfil_nombre);
        s_edad = (TextView)findViewById(R.id.perfil_edad);
        s_ubicacion = (TextView)findViewById(R.id.perfil_ubicacion);
        s_escuela = (TextView)findViewById(R.id.perfil_universidad);
        s_numero_pruebas = (TextView)findViewById(R.id.perfil_pruebas);
        s_rankin = (TextView)findViewById(R.id.perfil_ranking);
        s_puntos = (TextView)findViewById(R.id.perfil_puntos);
        imagen = (ImageView)findViewById(R.id.perfil_foto);
        
        s_nombre.setText("Mauricio Tiscareño Aguilera");
        s_edad.setText("24 años");
        s_ubicacion.setText("Aguascalientes");
        s_escuela.setText("Universidad Autonoma De Aguascalientes");
        s_numero_pruebas.setText("5 pruebas");
        s_rankin.setText("1th Lugar");
        s_puntos.setText("85.9 puntos");
     
        
        //MyAnsyTask tarea = new MyAnsyTask(this, 1, 
        //		extras.getString("usuario"), extras.getString("contrasena"));
        //tarea.execute();
        
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        getActionBar().setDisplayHomeAsUpEnabled(true);
         this.setTitle("Perfil de usuario");
       // mDrawerOptions = (ListView) findViewById(R.id.left_drawer);
       // mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);


       // mDrawerOptions.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values));
       // mDrawerOptions.setOnItemClickListener(this);
        
        mPanes = (SlidingPaneLayout) findViewById(R.id.slidingPane);
        

        // tarea = new MyAnsyTask(this, 2); 
        		
       // tarea.execute();

        // ListView initialization
        
        mthis = this;
        ActionBar action = getActionBar();
		action.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
		ListView list = (ListView) findViewById(R.id.left_drawer);
		
		ArrayList<APPLateral> laterales = new ArrayList<APPLateral>();
		laterales.add(new APPLateral("11/NOVIEMBRE/13", "10", "8", "2", "Suma y resta", "80.0"));
		laterales.add(new APPLateral("12/NOVIEMBRE/13", "20", "15", "5", "Ingles I", "75.0"));
		laterales.add(new APPLateral("13/NOVIMEBRE/13", "50", "45", "5", "Examen Oracle - Primer parcial", "90.0"));
		laterales.add(new APPLateral("14/NOVIEMBRE/13", "20", "10", "10", "Investigacion de operaciones", "50.0"));
		laterales.add(new APPLateral("15/NOVIEMBRE/13", "10", "10", "10", "Compresion lectora", "10.0"));
        list.setAdapter(new MyAdaptadorEstadisticas(laterales));
        this.loadPicture(null, R.id.perfil_foto);
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "Pulsado " + values[i], Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mthis, ExamenActivity.class);
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
	        	mthis.startActivity(new Intent(mthis, TemasActivity.class));
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
				mthis.loadPicture(jobj.getString("foto"), R.id.perfil_foto);
				mthis.s_nombre.setText(jobj.getString("nombre"));
				mthis.s_edad.setText(jobj.getString("edad")+" aï¿½os");
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
					URL url = new URL("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc3/c0.0.412.412/s200x200/543271_3813037598062_1196470297_n.jpg");
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
        
        
        
      //  list.setAdapter(new MyAdaptadorEstadisticas());
       // list.setOnItemClickListener(this);
        
	}
	
	
	
	
	
	
	private class MyAdaptadorEstadisticas extends BaseAdapter {
		

		private TextView fecha;
		private TextView titulo;
		private TextView preguntas;
		private TextView aciertos;
		private TextView errores;
		private TextView puntos;
		private ArrayList<APPLateral> data;

		public MyAdaptadorEstadisticas(ArrayList<APPLateral> data) {
			this.data = data;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
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
			// TODO Auto-generated method stub

			if (convertView == null) {
				LayoutInflater inflate = (LayoutInflater) mthis
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				View vistaView = inflate.inflate(R.layout.item_lateral, null);
				
				convertView = vistaView;
			}

			if (convertView != null) {
				LinearLayout layout = (LinearLayout) convertView;
				fecha = (TextView)layout.findViewById(R.id.textView1);
				titulo = (TextView)layout.findViewById(R.id.textView2);
				preguntas = (TextView)layout.findViewById(R.id.textView3);
				aciertos = (TextView)layout.findViewById(R.id.textView4);
				errores = (TextView)layout.findViewById(R.id.textView5);
				puntos = (TextView)layout.findViewById(R.id.textView6);
				
				fecha.setText(data.get(position).getFecha());
				titulo.setText(data.get(position).getTitulo().toUpperCase());
				preguntas.setText("NUMERO DE REACTIVOS: "+data.get(position).getPreguntas());
				aciertos.setText("NUMERO DE ACIERTOS: "+data.get(position).getAciertos());
				errores.setText("NUMERO DE ERRORES: "+data.get(position).getErrores());
				puntos.setText("NUMERO DE PUNTOS: "+data.get(position).getPuntos());
				
				layout.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						AlertDialog.Builder dialogo = new AlertDialog.Builder(mthis);
						LayoutInflater inflater = mthis.getLayoutInflater();
						View view = inflater.inflate(R.layout.dialog_detalle, null);
						TextView titulo = (TextView) view.findViewById(R.id.dialogo_titulo);
						TextView preguntas = (TextView)view.findViewById(R.id.dialogo_reactivos);
						TextView puntos = (TextView)view.findViewById(R.id.dialogo_puntuacion);
						TextView fecha = (TextView)view.findViewById(R.id.dialogo_fecha);
						Button share = (Button)view.findViewById(R.id.button1);
						titulo.setText(data.get(position).getTitulo().toUpperCase());
						preguntas.setText(data.get(position).getPreguntas());
						puntos.setText(data.get(position).getPuntos());
						fecha.setText(data.get(position).getFecha());
						share.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								String mensaje = "Mi calificacion usando APPrende:\n";
								mensaje+=""+data.get(position).getTitulo().toUpperCase()+"\n"
										+"NUMERO DE REACTIVOS: \n"+data.get(position).getPreguntas()+""
												+ "NUMERO DE PUNTOS: \n"+data.get(position).getPuntos();
								Intent share = new Intent(Intent.ACTION_SEND);
								share.setType("text/plain");
								share.putExtra(Intent.EXTRA_TEXT, mensaje);

								startActivity(Intent.createChooser(share, "Compartir"));
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

}
