package com.cbedoy.apprende.new_version.interfaces;

import android.app.Activity;

import com.cbedoy.apprende.new_version.viewcontroller.AbstractViewController;

/**
 * Created by Carlos on 14/10/2014.
 */
public interface IAppViewManager
{
    public Activity getActivity();
    public void reActivateCurrentView();
    public int getViewControllerWidth();
    public int getViewControllerHeight();
    public void presentViewForTag(String tag);
    public void finishWithResult(String result);
    public void addActivityResultListener(IActivityResultListener listener);
    public void addViewWithTag(AbstractViewController controller, String tag);
}
