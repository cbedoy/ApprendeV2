package com.uandroides.aprende.servicios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;

import com.uandroides.aprende.interfaces.IParser;
import com.uandroides.aprende.interfaces.IServiceInteractor;
import com.uandroides.aprende.modelos.Constantes;
import com.uandroides.aprende.parsers.ParserAllUsuarios;
import com.uandroides.aprende.utils.CBRESTClient;
import com.uandroides.aprende.vistas.MainActivity;

public class ServiceAllUsuarios extends AsyncTask<String, Integer, String> implements IServiceInteractor{
	private ProgressDialog pd;
	private Context contexto;
	
	
	public ServiceAllUsuarios(Context contexto){
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
			String respuesta = MainActivity.mthis.shared.getString(Constantes.usuarios, null);
			if(request.getResponse()!=null){
				Editor edit = MainActivity.mthis.shared.edit();
				edit.putString(Constantes.usuarios, request.getResponse());
				edit.commit();
				return request.getResponse();
				
			}
			return respuesta;
		}catch(Exception e){
			e.printStackTrace();
			String responsedsavedd = MainActivity.mthis.shared.getString(Constantes.usuarios, null);
			return responsedsavedd;
		
		}
	}
	
	
	
	@Override
	protected void onPostExecute(String respuesta){
		pd.dismiss();
		try{
			JSONArray array = new JSONObject(respuesta).getJSONArray("usuario");
			IParser parser = new ParserAllUsuarios();
			parser.setStringToParse(array);
		} catch (JSONException e) {
			e.printStackTrace();
			
		}
	}
}