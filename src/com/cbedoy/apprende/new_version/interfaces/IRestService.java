package com.cbedoy.apprende.new_version.interfaces;

/**
 * Created by Carlos on 14/10/2014.
 */
import java.util.HashMap;

public interface IRestService {

    public void setPort(int port);

    public void setURL(String url);

    public void request(String url, HashMap<String, Object> parameters, IRestCallback callback);

    public interface IRestCallback {

        public void run(HashMap<String, Object> response);

    }

}