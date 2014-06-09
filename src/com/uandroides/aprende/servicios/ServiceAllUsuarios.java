package com.uandroides.aprende.servicios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.uandroides.aprende.modelos.Constantes;
import com.uandroides.aprende.utils.CBRESTClient;
import com.uandroides.aprende.utils.CBRESTClient.RequestMethod;
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
		Log.i("json", "Pre");
	}
	
	@Override
	protected String doInBackground(String... arg0) {
		Log.i("json", "DO");
		return correrServicio(null);
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
		Log.i("json", "POST");
		try{
			JSONArray array = new JSONObject(respuesta).getJSONArray("usuario");
			ParserAllUsuarios parser = new ParserAllUsuarios();
			parser.setStringToParse(array);
			MainActivity.mthis.ReloadData(parser.getDataParsed());
		} catch (JSONException e) {
			e.printStackTrace();
			
		}
	}
}