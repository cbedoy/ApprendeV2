package com.uandroides.aprende.controladores;

import org.json.JSONException;
import org.json.JSONObject;

import com.uandroides.aprende.modelos.Tema;
import com.uandroides.aprende.utils.RESTClient;
import com.uandroides.aprende.utils.RESTClient.RequestMethod;
import com.uandroides.aprende.vistas.ExamenActivity;
import com.uandroides.aprende.vistas.MainActivity;
import com.uandroides.aprende.vistas.PerfilActivity;
import com.uandroides.aprende.vistas.TemasActivity;



import android.os.AsyncTask;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;

public class MyAnsyTask extends AsyncTask<String, Integer, /*Boolean*/ String> {

	
	/*
	 * ESTA CLASE SE ENCARGA DE MANEJAR LOS WEB SERVICES DE LA APLICACION
	 * 
	 * */
	
	private ProgressDialog pd;
	private Context context;
	private int link; 
	private String parametro;
	private int idTema;
	
	private String usuario;
	private String contrasena;
	private Tema tema;
	private String url = "http://192.168.0.10/apprende/";
	
	
	public MyAnsyTask(Context context, int link, String parametro){
		this.context = context;
		this.link = link;
		this.parametro = parametro;
	}
	
	public MyAnsyTask(Context context){
		this.context = context;
		
	}
	
	public MyAnsyTask(Context context, int link, int idTema){
		this.context = context;
		this.link = link;
		this.idTema = idTema;
	}
	
	public MyAnsyTask(Context context, int link){
		this.context = context;
		this.link = link;
	}
	
	public MyAnsyTask(Context context, int link, String usuario, String contrasena) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.link= link;
		this.usuario = usuario;
		this.contrasena = contrasena;
	}

	public MyAnsyTask(Context context, int link, Tema tema) {
		this.context = context;
		this.link = link;
		this.tema = tema;
	}

	@Override
	protected void onPreExecute(){
		pd = ProgressDialog.show(context, "", "Cargando...");
	}
	
	@Override
	protected String doInBackground(String... arg0) {
	
		RESTClient request = null;
		String respuesta = null;
		switch(link){
			case 1:	
				/*
				 * ESTE WEB SERVICES RETORNA UNA RESPUESTA AL LOGIN DE USUARIO
				 * JUTNO CON SU RESPECTIVA CONTRASEÑA
				 * 
				 * */
				request= new RESTClient(url+
						"getUsuaurio.php?correo="+usuario+"&contrasena="+contrasena+"");
				respuesta = servicioLogin(request);	
				break;
			case 2:	
				/*
				 * ESTE WEB SERVICES RETORNA LAS ESTADISTICAS POR USUARIO DE LOS
				 * EXAMEN QUE HA REALIZADO 
				 * 
				 * */
				request= new RESTClient(url+
						"getEstadisticas.php");
				respuesta = servicioTemas(request);	
				break;
			case 3:	
				/*
				 * ESTE WEB SERVICES RETORNA LAS MATERIAS 
				 * QUE HAY EN LA BASE DE DATOS PARA
				 * DE AHI SACAR LOS TEMAS
				 * 
				 * */
				request= new RESTClient(url +
						"getTemario.php");
				respuesta = servicioTemario(request);	
				break;
			case 4:
			case 5:
				request= new RESTClient(url +
						"getExamen.php?tema="+tema.getNombre()+"&cantidad="+tema.getNumeroPreguntas());
				respuesta = servicioExamen(request);
				break;
		}
		
		Log.i("miLog", respuesta);
		return respuesta;
		
	}
	
	

	private String servicioTemario(RESTClient request) {
		try{
			request.Execute(RequestMethod.GET);
			String respuesta = TemasActivity.mthis.shared.getString("temario", null);
			Log.i("miLog", "temario"+respuesta);
			if(request.getResponse()!=null){
				Editor edit = TemasActivity.mthis.shared.edit();
				edit.putString("temario", request.getResponse());
				edit.commit();
				return request.getResponse();
				
			}
			return respuesta;
		}catch(Exception e){
			e.printStackTrace();
			String responsedsavedd = TemasActivity.mthis.shared.getString("temario", null);
			return responsedsavedd;
		
		}
	}

	private String servicioExamen(RESTClient request) {
		try{
			request.Execute(RequestMethod.GET);
			String respuesta = ExamenActivity.mthis.shared.getString("examen", null);
			Log.i("miLog", "temas"+respuesta);
			if(request.getResponse()!=null){
				Editor edit = ExamenActivity.mthis.shared.edit();
				edit.putString("examen", request.getResponse());
				edit.commit();
				return request.getResponse();
				
			}
			return respuesta;
		}catch(Exception e){
			e.printStackTrace();
			String responsedsavedd = ExamenActivity.mthis.shared.getString("examen", null);
			return responsedsavedd;
		
		}
	}

	private String servicioTemasContenido(RESTClient request) {
		try{
			request.Execute(RequestMethod.GET);
			String respuesta = TemasActivity.mthis.shared.getString("temas", null);
		//	Log.i("miLog", "temas"+respuesta);
			if(request.getResponse()!=null){
				Editor edit = TemasActivity.mthis.shared.edit();
				edit.putString("temas", request.getResponse());
				edit.commit();
				return request.getResponse();
				
			}
			return respuesta;
		}catch(Exception e){
			e.printStackTrace();
			String responsedsavedd = TemasActivity.mthis.shared.getString("temas", null);
			return responsedsavedd;
		
		}
	}

	private String servicioLogin(RESTClient request){
		try{
			request.Execute(RequestMethod.GET);
			String respuesta = MainActivity.mthis.shared.getString("usuario", null);
		//	Log.i("miLog", "datos"+respuesta);
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
	
	private String servicioTemas(RESTClient request){
		try{
			request.Execute(RequestMethod.GET);
			String respuesta = PerfilActivity.mthis.shared.getString("temas", null);
		//	Log.i("miLog", "temas"+respuesta);
			if(request.getResponse()!=null){
				Editor edit = PerfilActivity.mthis.shared.edit();
				edit.putString("temas", request.getResponse());
				edit.commit();
				return request.getResponse();
				
			}
			return respuesta;
		}catch(Exception e){
			e.printStackTrace();
			String responsedsavedd = PerfilActivity.mthis.shared.getString("temas", null);
			return responsedsavedd;
		
		}
		
	}
	
	
	
	
	
	@Override
	protected void onPostExecute(/*Boolean*/ String result){
		pd.dismiss();
		
		
		try {
			JSONObject json = new JSONObject(result);
			
			
			switch(this.link){
				case 1:
					PerfilActivity.mthis.convertirUsuario(json);
					break;
				case 2:
					PerfilActivity.mthis.convertirTemas(json);
					break;
				case 3:
					TemasActivity.mthis.convertirTemas(json);
					break;
				case 4:
					//TemasActivity.mthis.convertirTemas(json);
				case 5:
					ExamenActivity.mthis.convertirPreguntas(json);
					break;
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
			
		}
		
	}

}
