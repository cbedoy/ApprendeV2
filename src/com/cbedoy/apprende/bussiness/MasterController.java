package com.cbedoy.apprende.bussiness;

import android.os.AsyncTask;


/**
 * Created by Carlos on 09/06/2014.
 */
public class MasterController {

    private static MasterController masterController;
    private AsyncTask<String, Integer, String> ansycTask;

    public static MasterController getInstance(){
        if(masterController == null)
            masterController = new MasterController();
        return  masterController;
    }

    public void setAnsycTask(AsyncTask<String, Integer, String> ansycTask){
    	this.ansycTask = ansycTask;
    }
    
    public AsyncTask<String, Integer, String> getAnsycTask(){
    	return this.ansycTask;
    }
    
}
