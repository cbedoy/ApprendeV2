package com.cbedoy.apprende.bussiness;

/**
 * Created by Carlos on 09/06/2014.
 */
public class MasterViewController {

    private static MasterViewController masterViewController;

    public static MasterViewController getInstance(){
        if (masterViewController == null)
            masterViewController = new MasterViewController();
        return masterViewController;
    }


}
