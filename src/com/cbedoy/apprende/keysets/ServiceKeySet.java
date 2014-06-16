package com.cbedoy.apprende.keysets;

/**
 * Created by Carlos on 09/06/2014.
 */
public enum  ServiceKeySet {

    NEW_USER            ("http://127.0.0.1:8000/apprende/user/get/$username/$password/$firstName/$lastName/$/$"),
    GET_EXAM            ("http://127.0.0.1:8000/apprende/user/get/$theme/$level/"),
    GET_USER_INFO       ("http://127.0.0.1:8000/apprende/user/get/$username/$password/"),
    GET_USER_STATISTICS (""),
    GET_EXAM_STATISTICS (""),
    GET_USER_RANKS      ("http://127.0.0.1:8000/apprende/user/get/ranks/"),
    GET_THEME           ("http://127.0.0.1:8000/apprende/theme/get/"),
    GET_COURSE          ("http://127.0.0.1:8000/apprende/course/get/");

    private final String url;

    ServiceKeySet(final String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.url;
    }
}
