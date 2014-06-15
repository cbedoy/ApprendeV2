package com.cbedoy.apprende.keysets;

/**
 * Created by Carlos on 09/06/2014.
 */
public enum  ServiceKeySet {

    NEW_USER            (""),
    NEW_EXAM            (""),
    GET_USER_INFO       ("http://127.0.0.1:8000/apprende/user/get"),
    GET_USER_STATISTICS (""),
    GET_EXAM_STATISTICS (""),
    GET_USER_RANKS      (""),
    GET_THEME           (""),
    GET_COURSE          ("");

    private final String url;

    ServiceKeySet(final String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.url;
    }
}
