package com.cbedoy.apprende.bussiness;

/**
 * Created by Carlos on 09/06/2014.
 */
public class MasterController {

    private static MasterController masterController;

    public static MasterController getInstance(){
        if(masterController == null)
            masterController = new MasterController();
        return  masterController;
    }

    public void setServiceKey(){

    }
}
