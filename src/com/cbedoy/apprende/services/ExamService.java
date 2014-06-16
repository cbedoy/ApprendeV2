package com.cbedoy.apprende.services;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Carlos on 16/06/2014.
 */
public class ExamService extends AsyncTask<String, Integer, String> {


    private CBRESTClient 		restClient;
    private Context context;

    public ExamService(Context context){
        this.context = context;
    }

    public ExamService(){
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

            }else{

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

}


