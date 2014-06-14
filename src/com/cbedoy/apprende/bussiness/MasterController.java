package com.cbedoy.apprende.bussiness;

import com.cbedoy.apprende.services.AsyncServiceController;

/**
 * Created by Carlos on 09/06/2014.
 */
public class MasterController {

    private static MasterController masterController;
    private AsyncServiceController asyncServiceController;

    public static MasterController getInstance(){
        if(masterController == null)
            masterController = new MasterController();
        return  masterController;
    }

    public void setAsyncServiceController(AsyncServiceController asyncServiceController) {
        this.asyncServiceController = asyncServiceController;
    }

    public AsyncServiceController getAsyncServiceController() {
        return asyncServiceController;
    }
}
