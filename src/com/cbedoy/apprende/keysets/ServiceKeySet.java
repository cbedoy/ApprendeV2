package com.cbedoy.apprende.keysets;

/**
 * Created by Carlos on 09/06/2014.
 */
public enum  ServiceKeySet {
	
	
    NEW_USER            ("http:///apprende/user/new/$username/$password/$fistName/$lastName/$facebook/$twitter/$age/"),
    GET_EXAM            ("http:///apprende/exam/get/$theme/$level/"),
    GET_EXAM_RANDOM     ("http:///apprende/exam/get/20/"),
    GET_USER_INFO       ("http:///apprende/user/get/$username/$password/"),
    GET_USER_STATISTICS (""),
    GET_EXAM_STATISTICS (""),
    GET_USER_RANKS      ("http:///apprende/user/get/ranks/"),
    GET_THEME           ("http:///apprende/theme/get/"),
    GET_COURSE          ("http:///apprende/course/get/"),
    GET_UNIVERSITY		("http:///apprende/university/get/"),
    GET_GUYS			("http:///apprende/user/get/"),;
    
    private final String url;

    ServiceKeySet(final String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.url;
    }
}
