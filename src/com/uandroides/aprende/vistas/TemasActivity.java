package com.uandroides.aprende.vistas;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.uandroides.aprende.R;
import com.uandroides.aprende.R.id;
import com.uandroides.aprende.R.layout;
import com.uandroides.aprende.R.menu;
import com.uandroides.aprende.controladores.MyAnsyTask;
import com.uandroides.aprende.modelos.APPMaterias;
import com.uandroides.aprende.modelos.APPTemario;




import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class TemasActivity extends Activity implements AdapterView.OnItemClickListener{

	private DrawerLayout mDrawer;
    private SlidingPaneLayout mPanes;
	public static TemasActivity mthis;
	public SharedPreferences shared;
	public ArrayList<APPTemario> temas;
	private String dificultad;
	public ArrayList<APPMaterias> data;
	public int llave;
	public String llaveHash;
	private String[] niveles = new String[] { "FACIL", "INTERMEDIO",
			"DIFICIL"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temas);
		overridePendingTransition(R.anim.push_up, R.anim.push_up);
	    mPanes = (SlidingPaneLayout) findViewById(R.id.slidingPane);
		shared = this.getPreferences(MODE_PRIVATE);
		temas = new ArrayList<APPTemario>();
		//MyAnsyTask miTarea = new MyAnsyTask(this, 3);
		//miTarea.execute();

		mthis = this;
		ActionBar action = getActionBar();
		action.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#000000")));
		
		
		
		ListView list = (ListView) findViewById(R.id.left_drawer);
        list.setAdapter(new MyAdaptadorMaterias());
        
        ListView lista = (ListView) findViewById(R.id.lista_temas);
		lista.setAdapter(new MyAdaptadorOffline());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.temas, menu);

		return super.onCreateOptionsMenu(menu);
	}
	
	private class MyAdaptadorOffline extends BaseAdapter {
		public TextView materia;
		public TextView tema, descripcion;
		public Button seleccion;
		public ArrayList<APPTemario> temario;
		public MyAdaptadorOffline() {
			temario = new ArrayList<APPTemario>();
			temario.add(new APPTemario(99, "Examen Demo", "Este es un examen que contiene multiples temas sobre diferentes materias en este comprobaras tus conocimeintos genelares", "Demo"));
			for(int i=0, k=100; i<data.size(); i++, k++){
			temario.add(new APPTemario(k, "Tema #1", "Temas selectos del area de "+data.get(i).getNombre(), data.get(i).getNombre()));
			temario.add(new APPTemario(k, "Tema #2", "Temas selectos del area de "+data.get(i).getNombre(), data.get(i).getNombre()));
			temario.add(new APPTemario(k, "Tema #3", "Temas selectos del area de "+data.get(i).getNombre(), data.get(i).getNombre()));
			}
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return temario.size();
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

				View vistaView = inflate.inflate(R.layout.item_tema, null);

				convertView = vistaView;
			}

			if (convertView != null) {
				LinearLayout layout = (LinearLayout) convertView;

				tema = (TextView) layout.findViewById(R.id.nombre_tema);
				tema.setText(temario.get(position).getNombre());
				seleccion = (Button) layout.findViewById(R.id.boton_tema);
				
				descripcion = (TextView) layout
						.findViewById(R.id.descripcion_tema);
				descripcion.setText((temario.get(position).getDescripcion()
						.equals("null") ? "" : temario.get(position)
						.getDescripcion()));
				if(position==0){
					seleccion.setVisibility(View.VISIBLE);
					seleccion.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							showMensaje(position, tema, descripcion);

						}

					});
				}else{
					seleccion.setVisibility(View.GONE);
				}
				

			}
			return convertView;

		}

		private void showMensaje(final int position, final TextView tema,
				TextView descripcion) {
			AlertDialog.Builder mensaje = new AlertDialog.Builder(mthis);
			mensaje.setTitle("Tema #" + temario.get(position).getIdTema());
			mensaje.setMessage("Usted selecciono el tema: "
					+ tema.getText().toString().toUpperCase()
					+ ", los detalles son los " + "siguientes: "
					+ descripcion.getText().toString().toUpperCase());
			mensaje.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							showSelectNivel(position);

						}
					});
			mensaje.setNegativeButton("Cancelar",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
			mensaje.show();
		}

		private void showSelectNivel(int position) {
			AlertDialog.Builder mensaje = new AlertDialog.Builder(mthis);
			mensaje.setTitle("Seleccione la dificultad");
			mensaje.setCancelable(false);

			mensaje.setSingleChoiceItems(niveles , 0, null);

			mensaje.setPositiveButton("Seleccionar",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							int index = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
				            Log.i("depu", ""+index);  
							
							
								Intent i = new Intent(mthis,
										ExamenActivity.class);
								i.putExtra("dificultad", index);
								i.putExtra("nombre_tema", tema.getText());
								startActivity(i);
								finish();
								
						}
							
			
						
					});

			mensaje.show();
		}
	}

	

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	public void convertirTemas(JSONObject json) {
		// TODO Auto-generated method stub

		try {
			JSONArray arreglo = json.getJSONArray("temario");
			mthis.temas.clear();
			APPTemario temario;
			for (int i = 0; i < arreglo.length(); i++) {
				JSONObject jobj = arreglo.getJSONObject(i);
				temario  = new APPTemario();
				temario.setIdTema(jobj.getInt("idTema"));
				temario.setNombre(jobj.getString("nombre"));
				temario.setDescripcion(jobj.getString("descripcion"));
				temario.setIdMateria(jobj.getInt("idMateria"));
				temario.setNombreMateria(jobj.getString("nombre_materia"));
				temas.add(temario);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("miLog", e.getMessage());
		}

		ListView lista = (ListView) findViewById(R.id.lista_temas);
		lista.setAdapter(new MyAdaptador());
		Log.i("miLog", "tamaï¿½o"+temas.size());
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	private class MyAdaptadorMaterias extends BaseAdapter {
		

		public MyAdaptadorMaterias() {
			data = new ArrayList<APPMaterias>();
			data.add(new APPMaterias(1, "Parciales"));
			data.add(new APPMaterias(2, "Matematicas"));
			data.add(new APPMaterias(3, "Ingles"));
			data.add(new APPMaterias(4, "Español"));
			data.add(new APPMaterias(5, "Fisica"));
			data.add(new APPMaterias(6, "Geografia"));
			
			data.add(new APPMaterias(7, "Ciencias naturales"));
			data.add(new APPMaterias(8, "Ciencias sociales"));
			data.add(new APPMaterias(9, "Psicologia"));
			data.add(new APPMaterias(10, "Quimica"));
			data.add(new APPMaterias(11, "Biologia"));
			data.add(new APPMaterias(12, "IT"));
			
			llave = 1;
			llaveHash = "Parciales";
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

				View vistaView = inflate.inflate(R.layout.item_materia, null);

				convertView = vistaView;
			}

			if (convertView != null) {
				LinearLayout layout = (LinearLayout) convertView;
				TextView nombre = (TextView)layout.findViewById(R.id.bMateria);
				nombre.setText(data.get(position).getNombre());
				
				layout.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						llave = data.get(position).getId();
						llaveHash = data.get(position).getNombre();
						
					}
				});
			}
			return convertView;

		}

		
	}
	
	
	
	private class MyAdaptador extends BaseAdapter {
		public TextView materia;
		public TextView tema, descripcion;
		public Button seleccion;

		public MyAdaptador() {

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return temas.size();
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

				View vistaView = inflate.inflate(R.layout.item_tema, null);

				convertView = vistaView;
			}

			if (convertView != null) {
				LinearLayout layout = (LinearLayout) convertView;

				tema = (TextView) layout.findViewById(R.id.nombre_tema);
				tema.setText(temas.get(position).getNombre());
				seleccion = (Button) layout.findViewById(R.id.boton_tema);
				descripcion = (TextView) layout
						.findViewById(R.id.descripcion_tema);
				descripcion.setText((temas.get(position).getDescripcion()
						.equals("null") ? "" : temas.get(position)
						.getDescripcion()));

				seleccion.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						showMensaje(position, tema, descripcion);

					}

				});

			}
			return convertView;

		}

		private void showMensaje(final int position, final TextView tema,
				TextView descripcion) {
			AlertDialog.Builder mensaje = new AlertDialog.Builder(mthis);
			mensaje.setTitle("Tema #" + temas.get(position).getIdTema());
			mensaje.setMessage("Usted selecciono el tema: "
					+ tema.getText().toString().toUpperCase()
					+ ", los detalles son los " + "siguientes: "
					+ descripcion.getText().toString().toUpperCase());
			mensaje.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							showSelectNivel(position);

						}
					});
			mensaje.setNegativeButton("Cancelar",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
			mensaje.show();
		}

		private void showSelectNivel(int position) {
			AlertDialog.Builder mensaje = new AlertDialog.Builder(mthis);
			mensaje.setTitle("Seleccione la dificultad");
			mensaje.setCancelable(false);

			mensaje.setSingleChoiceItems(niveles , 0, null);

			mensaje.setPositiveButton("Seleccionar",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							int index = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
				            Log.i("depu", ""+index);  
							
							
								Intent i = new Intent(mthis,
										ExamenActivity.class);
								i.putExtra("dificultad", index);
								i.putExtra("nombre_tema", tema.getText());
								startActivity(i);
								finish();
								
						}
							
			
						
					});

			mensaje.show();
		}
	}
}
