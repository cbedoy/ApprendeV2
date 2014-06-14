package com.cbedoy.apprende.services;

import android.content.Context;

import com.cbedoy.apprende.bussiness.MasterController;
import com.cbedoy.apprende.keysets.ServiceKeySet;

import java.util.HashMap;

public class AppInstanceProvider {
	private static AppInstanceProvider  appInstanceProvider;
	private Context                     context;

	public static AppInstanceProvider getInstance(){
		if(appInstanceProvider == null) {
            appInstanceProvider = new AppInstanceProvider();
        }
		return appInstanceProvider;
	}

    public MasterController instanceServiceWithEnum(Context context, HashMap<String, Object> dataModel, ServiceKeySet serviceKeySet){

        MasterController masterController               = MasterController.getInstance();
        AsyncServiceController asyncServiceController   = new AsyncServiceController(context);
        CBRESTClient restClient                           = new CBRESTClient(serviceKeySet.toString());

        for(String key : dataModel.keySet()){
            restClient.AddParam(key, dataModel.get(key).toString());
        }

        asyncServiceController.setRestClient(restClient);
        masterController.setAsyncServiceController(asyncServiceController);

        return masterController;
    }



	 
}
