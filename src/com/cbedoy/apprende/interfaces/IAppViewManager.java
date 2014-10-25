package com.cbedoy.apprende.interfaces;

import android.app.Activity;

import com.cbedoy.apprende.viewcontroller.AbstractViewController;

/**
 * Created by Carlos on 14/10/2014.
 */
public interface IAppViewManager
{
    public Activity getActivity();
    public void reActivateCurrentView();
    public int getViewControllerWidth();
    public int getViewControllerHeight();
    public void presentViewForTag(AbstractViewController.CONTROLLER tag);
    public void finishWithResult(String result);
    public void addActivityResultListener(IActivityResultListener listener);
    public void addViewWithTag(AbstractViewController controller, AbstractViewController.CONTROLLER tag);
}
