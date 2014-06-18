package com.cbedoy.apprende.keysets;

import com.cbedoy.apprende.services.AppInstanceProvider;

/**
 * Created by Carlos on 09/06/2014.
 */
public enum  ServiceKeySet {
	
	
    NEW_USER            ("http://"+AppInstanceProvider.SERVER_URL+"/apprende/user/new/$username/$password/$fistName/$lastName/$facebook/$twitter/$age/$points/$plays/"),
    GET_EXAM            ("http://"+AppInstanceProvider.SERVER_URL+"/apprende/user/get/$theme/$level/"),
    GET_EXAM_RANDOM     ("http://"+AppInstanceProvider.SERVER_URL+"/apprende/exam/get/20/"),
    GET_USER_INFO       ("http://"+AppInstanceProvider.SERVER_URL+"/apprende/user/get/$username/$password/"),
    GET_USER_STATISTICS (""),
    GET_EXAM_STATISTICS (""),
    GET_USER_RANKS      ("http://"+AppInstanceProvider.SERVER_URL+"/apprende/user/get/ranks/"),
    GET_THEME           ("http://"+AppInstanceProvider.SERVER_URL+"/apprende/theme/get/"),
    GET_COURSE          ("http://"+AppInstanceProvider.SERVER_URL+"/apprende/course/get/"),
    GET_UNIVERSITY		("http://"+AppInstanceProvider.SERVER_URL+"/apprende/university/get/");
    
    private final String url;

    ServiceKeySet(final String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.url;
    }
}
