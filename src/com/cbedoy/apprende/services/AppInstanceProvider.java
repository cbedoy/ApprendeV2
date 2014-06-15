package com.cbedoy.apprende.services;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;

import com.cbedoy.apprende.bussiness.MasterController;
import com.cbedoy.apprende.interfaces.IAsyncServiceDelegate;
import com.cbedoy.apprende.keysets.ServiceKeySet;

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

    public MasterController instanceServiceWithEnum(Activity activity, HashMap<String, Object> dataModel, ServiceKeySet serviceKeySet){

        MasterController masterController               = MasterController.getInstance();
        AsyncServiceController asyncServiceController   = new AsyncServiceController(activity.getApplicationContext());
        String urlFormater 								= "";
        for(String key : dataModel.keySet()){
        	urlFormater +="/"+dataModel.get(key).toString();
        }
        CBRESTClient restClient                         = new CBRESTClient(serviceKeySet.toString()+urlFormater);
        asyncServiceController.setRestClient(restClient);
        asyncServiceController.setAnsycServiceDelegate(asyncServiceDelegate);
        masterController.setAsyncServiceController(asyncServiceController);
        return masterController;
    }
    
    
    
    
    
    
    



	 
}
