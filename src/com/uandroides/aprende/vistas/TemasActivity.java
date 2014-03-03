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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.uandroides.aprende.R;
import com.uandroides.aprende.items.ItemMaterias;
import com.uandroides.aprende.items.ItemTemario;
import com.uandroides.aprende.modelos.Materia;
import com.uandroides.aprende.modelos.Temario;

public class TemasActivity extends Activity implements AdapterView.OnItemClickListener{

	private DrawerLayout mDrawer;
    private SlidingPaneLayout mPanes;
	public static TemasActivity mthis;
	public SharedPreferences shared;
	public ArrayList<Temario> temas;
	private String dificultad;
	public ArrayList<Materia> data;
	public int llave;
	public String llaveHash;
	private String[] niveles = new String[] { "FACILdasdasd", "INTERMEDIO",
			"DIFICIL"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temas);
		overridePendingTransition(R.anim.push_up, R.anim.push_up);
	    mPanes = (SlidingPaneLayout) findViewById(R.id.slidingPane);
		shared = this.getPreferences(MODE_PRIVATE);
		temas = new ArrayList<Temario>();


		mthis = this;
		ActionBar action = getActionBar();
		action.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#000000")));
		
		
		
		ListView list = (ListView) findViewById(R.id.left_drawer);
        list.setAdapter(new ItemMaterias(this));
        
        ListView lista = (ListView) findViewById(R.id.lista_temas);
      		lista.setAdapter(new ItemTemario(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.temas, menu);

		return super.onCreateOptionsMenu(menu);
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
			Temario temario;
			for (int i = 0; i < arreglo.length(); i++) {
				JSONObject jobj = arreglo.getJSONObject(i);
				temario  = new Temario();
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
		lista.setAdapter(new ItemTemario(this));
		Log.i("miLog", "tama�o"+temas.size());
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}
