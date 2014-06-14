package com.cbedoy.apprende.services;

import android.content.Context;
import android.graphics.Typeface;

import com.cbedoy.apprende.bussiness.MasterController;
import com.cbedoy.apprende.keysets.ServiceKeySet;

import java.util.HashMap;

public class AppInstanceProvider {
	private static AppInstanceProvider  appInstanceProvider;
	private Context                     context;
	public static Typeface 				boldFont;	
	public static Typeface 				regularFont;	
	public static Typeface 				thinFont;	
	public static Typeface 				lightFont;	

	
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

    public MasterController instanceServiceWithEnum(Context context, HashMap<String, Object> dataModel, ServiceKeySet serviceKeySet){

        MasterController masterController               = MasterController.getInstance();
        AsyncServiceController asyncServiceController   = new AsyncServiceController(context);
        CBRESTClient restClient                         = new CBRESTClient(serviceKeySet.toString());

        for(String key : dataModel.keySet()){
            restClient.AddParam(key, dataModel.get(key).toString());
        }

        asyncServiceController.setRestClient(restClient);
        masterController.setAsyncServiceController(asyncServiceController);

        return masterController;
    }
    
    
    
    



	 
}
