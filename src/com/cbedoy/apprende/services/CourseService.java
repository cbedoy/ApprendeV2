package com.cbedoy.apprende.services;

import android.content.Context;
import android.os.AsyncTask;

import com.cbedoy.apprende.interfaces.viewdelegates.ICourseViewDelegate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Carlos on 15/06/2014.
 */
public class CourseService extends AsyncTask<String, Integer, String> {


    private CBRESTClient 			restClient;
    private ICourseViewDelegate 	viewDelegate;
    private Context 				context;
	private static CourseService 	service;
	
	public static CourseService getInstance(){
		if(service == null)
			service = new CourseService();
		return service;
	}
	
	public CourseService(){
	}

    public void setViewDelegate(ICourseViewDelegate viewDelegate){
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
                viewDelegate.reloadDataWithCourse(json);
            }else{
                viewDelegate.reloadDataWithCourse(null);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

}
