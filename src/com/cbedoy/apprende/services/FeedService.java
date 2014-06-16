package com.cbedoy.apprende.services;

import android.content.Context;
import android.os.AsyncTask;

import com.cbedoy.apprende.interfaces.viewdelegates.IFeedViewDelegate;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Carlos on 16/06/2014.
 */
public class FeedService extends AsyncTask<String, Integer, String> {


    private CBRESTClient 		restClient;
    private IFeedViewDelegate   viewDelegate;
    private Context 			context;
	private static FeedService 	service;
	
	public static FeedService getInstance(){
		if(service == null)
			service = new FeedService();
		return service;
	}
	
	public FeedService(){
	}

    public void setViewDelegate(IFeedViewDelegate viewDelegate){
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
                JSONObject json = new JSONObject(response);
                viewDelegate.reloadData(json);
            }else{
                viewDelegate.reloadData(null);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

}
