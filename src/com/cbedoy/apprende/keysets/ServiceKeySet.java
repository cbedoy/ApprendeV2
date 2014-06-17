package com.cbedoy.apprende.keysets;

/**
 * Created by Carlos on 09/06/2014.
 */
public enum  ServiceKeySet {
	
	
    NEW_USER            ("http://148.211.75.182:8000/apprende/user/new/$username/$password/$fistName/$lastName/$facebook/$twitter/$age/$points/$plays/"),
    GET_EXAM            ("http://148.211.75.182:8000/apprende/user/get/$theme/$level/"),
    GET_USER_INFO       ("http://148.211.75.182:8000/apprende/user/get/$username/$password/"),
    GET_USER_STATISTICS (""),
    GET_EXAM_STATISTICS (""),
    GET_USER_RANKS      ("http://148.211.75.182:8000/apprende/user/get/ranks/"),
    GET_THEME           ("http://148.211.75.182:8000/apprende/theme/get/"),
    GET_COURSE          ("http://148.211.75.182:8000/apprende/course/get/"),
    GET_UNIVERSITY		("http://148.211.75.182:8000/apprende/university/get/");
    
    private final String url;

    ServiceKeySet(final String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.url;
    }
}
