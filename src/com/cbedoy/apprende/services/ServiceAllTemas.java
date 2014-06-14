package com.cbedoy.apprende.services;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.cbedoy.apprende.models.Constantes;
import com.cbedoy.apprende.utils.RESTClient;
import com.cbedoy.apprende.utils.RESTClient.RequestMethod;

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
			RESTClient request = new RESTClient(Constantes.getAllUsers);
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