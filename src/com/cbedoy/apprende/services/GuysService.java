package com.cbedoy.apprende.services;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.os.AsyncTask;

import com.cbedoy.apprende.interfaces.viewdelegates.IGuyViewDelegate;
import com.cbedoy.apprende.services.CBRESTClient.RequestMethod;

public class GuysService extends AsyncTask<String, Integer, String>{

	
	private CBRESTClient 		restClient;
	private IGuyViewDelegate 	guyViewDelegate;
	private Context 			context;

	public GuysService(){
	}
	
	public void setViewDelegate(IGuyViewDelegate viewDelegate){
		this.guyViewDelegate = viewDelegate;
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
				guyViewDelegate.reloadDataWithGuys(json);
			}else{
				guyViewDelegate.reloadDataWithGuys(null);
			}
				
		} catch (JSONException e) {
			guyViewDelegate.reloadDataWithGuys(null);
			
		}
	}

}
