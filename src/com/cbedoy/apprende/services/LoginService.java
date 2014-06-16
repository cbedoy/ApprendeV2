package com.cbedoy.apprende.services;

import org.json.JSONException;
import org.json.JSONObject;

import com.cbedoy.apprende.interfaces.viewdelegates.ILoginViewDelegate;
import com.cbedoy.apprende.services.CBRESTClient.RequestMethod;

import android.content.Context;
import android.os.AsyncTask;

public class LoginService extends AsyncTask<String, Integer, String>{

	
	private CBRESTClient 		restClient;
	private ILoginViewDelegate 	loginViewDelegate;
	private Context 			context;
	
	public LoginService(Context context){
		this.context = context;
	}
	
	public LoginService(){
	}
	
	public void setLoginViewDelegate(ILoginViewDelegate loginViewDelegate){
		this.loginViewDelegate = loginViewDelegate;
	}
	
	public void setRestClient(CBRESTClient restClient){
		this.restClient = restClient;
	}
	
	@Override
	protected void onPreExecute(){

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
		try {
			if(response != null){
				JSONObject json = new JSONObject(response);
				loginViewDelegate.reloadData(json);
			}else{
				loginViewDelegate.reloadData(null);
			}
				
		} catch (JSONException e) {
			e.printStackTrace();
			
		}
	}

}
