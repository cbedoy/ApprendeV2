package com.cbedoy.apprende.services;

import org.json.JSONException;
import org.json.JSONObject;

import com.cbedoy.apprende.models.Constantes;
import com.cbedoy.apprende.utils.RESTClient;
import com.cbedoy.apprende.utils.RESTClient.RequestMethod;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class AsyncServiceController extends AsyncTask<String, Integer, String> {

	private ProgressDialog 			progressDialog;
	private Context 				context;
	private IAsyncServiceDelegate 	ansyncServiceDelegate;
	
	public AsyncServiceController(Context context){
		this.context = context;
	}
	
	
	public void setAnsycServiceDelegate(IAsyncServiceDelegate ansyncServiceDelegate){
		this.ansyncServiceDelegate = ansyncServiceDelegate;
	}
	
	@Override
	protected void onPreExecute(){
		progressDialog = ProgressDialog.show(context, "", "Cargando...");
	}
	
	@Override
	protected String doInBackground(String... information) {
		try{
			RESTClient request = new RESTClient(Constantes.getAllUsers);
			request.Execute(RequestMethod.GET);
			if(request.getResponse()!=null)
				return request.getResponse();
			return null;
		}catch(Exception e){
			return null;
		}
	}
	
	@Override
	protected void onPostExecute(String response){
		progressDialog.dismiss();
		try {
			JSONObject json = new JSONObject(response);
			ansyncServiceDelegate.reloadData(json);
		} catch (JSONException e) {
			e.printStackTrace();
			
		}
	}

}
