package com.uandroides.aprende.servicios;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;

import com.uandroides.aprende.utils.RESTClient;
import com.uandroides.aprende.vistas.MainActivity;

public class obtainTemasByMateria extends AsyncTask<String, Integer, String> implements IServiceConector{
	private ProgressDialog pd;
	private Context contexto;
	private final String url="";
	
	
	public obtainTemasByMateria(Context contexto){
		this.contexto = contexto;
	}
	
	
	@Override
	protected void onPreExecute(){
		pd = ProgressDialog.show(contexto, "", "Cargando...");
	}
	
	@Override
	protected String doInBackground(String... arg0) {
		
		return null;
	}
	
	@Override
	public String correrServicio(Object objeto) {
		
		try{
			RESTClient request = new RESTClient(url);
			String respuesta = MainActivity.mthis.shared.getString("estadisticas", null);
			if(request.getResponse()!=null){
				Editor edit = MainActivity.mthis.shared.edit();
				edit.putString("estadisticas", request.getResponse());
				edit.commit();
				return request.getResponse();
				
			}
			return respuesta;
		}catch(Exception e){
			e.printStackTrace();
			String responsedsavedd = MainActivity.mthis.shared.getString("estadisticas", null);
			return responsedsavedd;
		
		}
	}
	
	
	
	@Override
	protected void onPostExecute(String respuesta){
		pd.dismiss();
		try {
			JSONObject json = new JSONObject(respuesta);
			
			
			
			
			
		} catch (JSONException e) {
			e.printStackTrace();
			
		}
	}
}