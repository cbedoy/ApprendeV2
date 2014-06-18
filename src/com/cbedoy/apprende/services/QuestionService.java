package com.cbedoy.apprende.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.cbedoy.apprende.interfaces.viewdelegates.IProfileViewDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.IQuestionaryRepresentationDelegate;
import com.cbedoy.apprende.services.CBRESTClient.RequestMethod;

public class QuestionService extends AsyncTask<String, Integer, String>{

	
	private CBRESTClient 						restClient;
	private IQuestionaryRepresentationDelegate 	viewDelegate;
	private Context 							context;

	
	public void setViewDelegate(IQuestionaryRepresentationDelegate viewDelegate){
		this.viewDelegate = viewDelegate;
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
				this.viewDelegate.reloadViewWithQuestions(json);
			}else{
				this.viewDelegate.reloadViewWithQuestions(null);
			}
				
		} catch (JSONException e) {
			e.printStackTrace();
			
		}
	}

}
