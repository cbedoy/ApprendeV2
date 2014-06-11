package com.cbedoy.apprende.services;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;

import com.cbedoy.apprende.utils.CBRESTClient;
import com.cbedoy.apprende.viewcontrollers.MainActivity;

public class ServiceAllMaterias extends AsyncTask<String, Integer, String> implements IServiceInteractor{
	private ProgressDialog pd;
	private Context contexto;
	private final String url="";
	
	
	public ServiceAllMaterias(Context contexto){
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
			CBRESTClient request = new CBRESTClient(url);
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