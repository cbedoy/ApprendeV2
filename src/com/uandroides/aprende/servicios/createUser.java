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
package com.uandroides.aprende.servicios;

import org.json.JSONException;
import org.json.JSONObject;

import com.uandroides.aprende.controladores.RESTClient;
import com.uandroides.aprende.controladores.RESTClient.RequestMethod;
import com.uandroides.aprende.modelos.APPUsuario;
import com.uandroides.aprende.vistas.ExamenActivity;
import com.uandroides.aprende.vistas.MainActivity;
import com.uandroides.aprende.vistas.PerfilActivity;
import com.uandroides.aprende.vistas.TemasActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;

public class createUser extends AsyncTask<String, Integer, String> implements IServiceConector{
	private ProgressDialog pd;
	private Context contexto;
	private final String url="";
	public createUser(Context contexto){
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
		APPUsuario usuario = (APPUsuario) objeto;
		try{
			RESTClient request = new RESTClient(url);
			request .Execute(RequestMethod.GET);
			request.AddParam("correo", usuario.getCorreo());
			request.AddParam("contrasena", usuario.getContrasena());
			request.AddParam("nombre", usuario.getNombre());
			request.AddParam("edad", usuario.getEdad()+"");
			request.AddParam("foto", usuario.getLink());
			String respuesta = MainActivity.mthis.shared.getString("usuario", null);
			if(request.getResponse()!=null){
				Editor edit = MainActivity.mthis.shared.edit();
				edit.putString("usuario", request.getResponse());
				edit.commit();
				return request.getResponse();
				
			}
			return respuesta;
		}catch(Exception e){
			e.printStackTrace();
			String responsedsavedd = MainActivity.mthis.shared.getString("usuario", null);
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
