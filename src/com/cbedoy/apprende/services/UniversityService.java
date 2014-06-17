package com.cbedoy.apprende.services;

import org.json.JSONArray;
import org.json.JSONException;

import com.cbedoy.apprende.interfaces.viewdelegates.IUniversityViewDelegate;

import android.os.AsyncTask;


public class UniversityService extends AsyncTask<String, Integer, String> {

    private CBRESTClient 				restClient;
    private IUniversityViewDelegate      	viewDelegate;
	private static UniversityService 	service;
	
	public static UniversityService getInstance(){
		if(service == null)
			service = new UniversityService();
		return service;
	}
	
	public UniversityService(){
	
	}

    public void setViewDelegate(IUniversityViewDelegate viewDelegate){
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
            restClient.Execute(CBRESTClient.RequestMethod.GET);
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
                viewDelegate.reloadDataWithUniversity(json);
            }else{
                viewDelegate.reloadDataWithUniversity(null);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

}
