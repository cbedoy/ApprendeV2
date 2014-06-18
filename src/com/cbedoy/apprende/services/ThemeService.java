package com.cbedoy.apprende.services;

import android.content.Context;
import android.os.AsyncTask;

import com.cbedoy.apprende.interfaces.viewdelegates.IThemeViewDelegate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Carlos on 15/06/2014.
 */
public class ThemeService  extends AsyncTask<String, Integer, String> {


    private CBRESTClient 			restClient;
    private IThemeViewDelegate      viewDelegate;
	private static ThemeService service;
	

	public ThemeService(){
	
	}

    public void setViewDelegate(IThemeViewDelegate viewDelegate){
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
                viewDelegate.reloadDataWithTheme(json);
            }else{
                viewDelegate.reloadDataWithTheme(null);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

}
