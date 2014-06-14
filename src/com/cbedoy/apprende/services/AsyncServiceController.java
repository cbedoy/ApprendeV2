package com.cbedoy.apprende.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.cbedoy.apprende.interfaces.IAsyncServiceDelegate;
import com.cbedoy.apprende.services.RESTClient.RequestMethod;

import org.json.JSONException;
import org.json.JSONObject;

public class AsyncServiceController extends AsyncTask<String, Integer, String> {

	private ProgressDialog 			progressDialog;
	private Context 				context;
	private IAsyncServiceDelegate ansyncServiceDelegate;
    private RESTClient              restClient;

    public void setRestClient(RESTClient restClient) {
        this.restClient = restClient;
    }

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
            restClient.Execute(RequestMethod.GET);
			if(restClient.getResponse()!=null)
				return restClient.getResponse();
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
