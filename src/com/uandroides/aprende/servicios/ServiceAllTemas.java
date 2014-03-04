package com.uandroides.aprende.servicios;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;

import com.uandroides.aprende.interfaces.IServiceInteractor;
import com.uandroides.aprende.modelos.Constantes;
import com.uandroides.aprende.utils.CBRESTClient;
import com.uandroides.aprende.utils.CBRESTClient.RequestMethod;
import com.uandroides.aprende.vistas.MainActivity;

public class ServiceAllTemas extends AsyncTask<String, Integer, String> implements IServiceInteractor{
	private ProgressDialog pd;
	private Context contexto;
	private final String url="";
	
	
	public ServiceAllTemas(Context contexto){
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
			CBRESTClient request = new CBRESTClient(Constantes.getAllUsers);
			request.Execute(RequestMethod.GET);
			if(request.getResponse()!=null){
				return request.getResponse();
			}
			return null;
		}catch(Exception e){
			return "";
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