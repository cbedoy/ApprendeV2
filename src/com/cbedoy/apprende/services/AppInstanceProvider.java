package com.cbedoy.apprende.services;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.BaseAdapter;

import com.cbedoy.apprende.bussiness.MasterController;
import com.cbedoy.apprende.interfaces.IAsyncServiceDelegate;
import com.cbedoy.apprende.interfaces.IContextDetection;
import com.cbedoy.apprende.keysets.ServiceKeySet;
import com.cbedoy.apprende.keysets.UserKeySet;

import java.util.HashMap;

public class AppInstanceProvider {
	private static AppInstanceProvider  appInstanceProvider;
	private Context                     context;
	public static Typeface 				boldFont;	
	public static Typeface 				regularFont;	
	public static Typeface 				thinFont;	
	public static Typeface 				lightFont;	
	private IAsyncServiceDelegate       asyncServiceDelegate;

	
	public static AppInstanceProvider getInstance(Context context){
		if(appInstanceProvider == null) {
            appInstanceProvider = new AppInstanceProvider(context);
        }
		return appInstanceProvider;
	}
	
	public static AppInstanceProvider getInstance(){
		if(appInstanceProvider == null) {
            appInstanceProvider = new AppInstanceProvider();
        }
		return appInstanceProvider;
	}
	
	
	public void setAnsycServiceDelegate(IAsyncServiceDelegate asyncServiceDelegate){
		this.asyncServiceDelegate = asyncServiceDelegate;
	}
	
	private AppInstanceProvider(Context context){
		reloadFonts(context);
	}
	
	private void reloadFonts(Context context) {
		boldFont 		= Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
		regularFont 	= Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
		thinFont 		= Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
		lightFont 		= Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");		
	}

	private AppInstanceProvider(){
		
	}

    public MasterController instanceServiceLogin(IContextDetection view, HashMap<Object, Object> dataModel, ServiceKeySet serviceKeySet){

    	AsyncServiceController asyncServiceController   = null;
        MasterController masterController               = MasterController.getInstance();
        
        if(view instanceof Activity){
        	asyncServiceController   					= new AsyncServiceController(((Activity)view).getApplicationContext());
        }else if(view instanceof BaseAdapter){
        	asyncServiceController   					= new AsyncServiceController(((View)view).getContext());
        }
        String urlResponse								= serviceKeySet.toString();
        urlResponse 									= urlResponse.replace("$username", dataModel.get(UserKeySet.USERNAME).toString());
        urlResponse										= urlResponse.replace("$password", dataModel.get(UserKeySet.PASSWORD).toString());
        CBRESTClient restClient                         = new CBRESTClient(urlResponse);
        asyncServiceController.setRestClient(restClient);
        asyncServiceController.setAnsycServiceDelegate(asyncServiceDelegate);
        masterController.setAsyncServiceController(asyncServiceController);
        return masterController;
    }
    
    public MasterController instanceServiceTheme(Activity activity, ServiceKeySet serviceKeySet){

        MasterController masterController               = MasterController.getInstance();
        AsyncServiceController asyncServiceController   = new AsyncServiceController(activity.getApplicationContext());
        String url										= serviceKeySet.toString();
        CBRESTClient restClient                         = new CBRESTClient(url);
        asyncServiceController.setRestClient(restClient);
        asyncServiceController.setAnsycServiceDelegate(asyncServiceDelegate);
        masterController.setAsyncServiceController(asyncServiceController);
        return masterController;
    }
    
    public MasterController instanceServiceCourse(Activity activity, ServiceKeySet serviceKeySet){

        MasterController masterController               = MasterController.getInstance();
        AsyncServiceController asyncServiceController   = new AsyncServiceController(activity.getApplicationContext());
        String url										= serviceKeySet.toString();
        CBRESTClient restClient                         = new CBRESTClient(url);
        asyncServiceController.setRestClient(restClient);
        asyncServiceController.setAnsycServiceDelegate(asyncServiceDelegate);
        masterController.setAsyncServiceController(asyncServiceController);
        return masterController;
    }
    
    
    
    
    
    
    



	 
}
