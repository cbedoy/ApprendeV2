package com.cbedoy.apprende.bussiness;

import java.util.HashMap;
import java.util.List;

import android.os.AsyncTask;


/**
 * Created by Carlos on 09/06/2014.
 */
public class MasterController {

    private static MasterController 			masterController;
    private AsyncTask<String, Integer, String> 	ansycTask;
    private HashMap<Object, Object> 			userInfo;
    private List<Object> 						questionaryInfo;

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
    
    public void setUserInfo(HashMap<Object, Object> userInfo){
    	this.userInfo = userInfo;
    }
    
    public HashMap<Object, Object> getUserInformation(){
    	return this.userInfo;
    }
    
    public void setQuestionaryInfo(List<Object> dataModel){
    	this.questionaryInfo = dataModel;
    }
    
    public List<Object> getQuestionaryInfo(){
    	return this.questionaryInfo;
    }
    
}
