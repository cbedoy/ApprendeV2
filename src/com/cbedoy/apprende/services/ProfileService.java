package com.cbedoy.apprende.services;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.cbedoy.apprende.interfaces.viewdelegates.ILoginViewDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.IProfileViewDelegate;
import com.cbedoy.apprende.services.CBRESTClient.RequestMethod;

public class ProfileService extends AsyncTask<String, Integer, String>{

	
	private CBRESTClient 			restClient;
	private IProfileViewDelegate 	profileViewDelegate;
	private Context 				context;
	
	public ProfileService(Context context){
		this.context = context;
	}
	
	public ProfileService(){
	}
	
	public void setViewDelegate(IProfileViewDelegate viewDelegate){
		this.profileViewDelegate = viewDelegate;
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
				profileViewDelegate.reloadData(json);
			}else{
				profileViewDelegate.reloadData(null);
			}
				
		} catch (JSONException e) {
			e.printStackTrace();
			
		}
	}

}
