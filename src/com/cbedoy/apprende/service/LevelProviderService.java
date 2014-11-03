package com.cbedoy.apprende.service;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.activity.ApplicationLoader;

/**
 * Created by Carlos on 27/10/2014.
 */
public class LevelProviderService
{
    private static LevelProviderService instance;

    public static LevelProviderService getInstance(){
        if(instance == null)
            instance = new LevelProviderService();
        return instance;
    }

    public String getTitleByLevel(int level)
    {
        String value = "";
        switch (level)
        {
            case 30:
                value = ApplicationLoader.mainContext.getString(R.string.very_easy);
                break;
            case 20:
                value = ApplicationLoader.mainContext.getString(R.string.easy);
                break;
            case 15:
                value = ApplicationLoader.mainContext.getString(R.string.normal);
                break;
            case 10:
                value = ApplicationLoader.mainContext.getString(R.string.hard);
                break;
            case 5:
                value = ApplicationLoader.mainContext.getString(R.string.very_hard);
                break;
        }
        return value;
    }

    public int getPointsByLevel(int level)
    {
        int value = -1;
        switch (level)
        {
            case 30:
                value = 1;
                break;
            case 20:
                value = 2;
                break;
            case 15:
                value = 5;
                break;
            case 10:
                value = 10;
                break;
            case 5:
                value = 20;
                break;
        }
        return value;
    }

}
