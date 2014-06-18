package com.cbedoy.apprende.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cbedoy.apprende.interfaces.viewdelegates.ILoginViewDelegate;
import com.cbedoy.apprende.services.CBRESTClient.RequestMethod;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class LoginService extends AsyncTask<String, Integer, String>{

	
	private CBRESTClient 		restClient;
	private ILoginViewDelegate 	loginViewDelegate;
	private Context 			context;
	private static LoginService service;

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
				JSONArray json = new JSONArray(response);
				loginViewDelegate.reloadData(json.getJSONObject(0));
			}else{
				loginViewDelegate.reloadData(null);
			}
				
		} catch (JSONException e) {
			loginViewDelegate.reloadData(null);
			
		}
	}

}
