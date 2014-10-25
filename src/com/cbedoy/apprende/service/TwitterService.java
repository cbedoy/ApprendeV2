package com.cbedoy.apprende.service;

import java.util.HashMap;

/**
 * Created by Carlos on 18/10/2014.
 */
public class TwitterService
{
    private static TwitterService instance;

    public static TwitterService getInstance(){
        if(instance == null)
            instance = new TwitterService();
        return instance;
    }

    public void postTweetWithInfo(HashMap<String, Object> info){
        //TODO Posting
    }
}
