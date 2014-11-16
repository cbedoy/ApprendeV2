package com.cbedoy.apprende.interfaces;

import android.app.Activity;

import com.cbedoy.apprende.viewcontroller.AbstractViewController;

/**
 * Created by Carlos on 14/10/2014.
 */
public interface IAppViewManager
{
    public Activity getActivity();
    public void presentViewForTag(AbstractViewController.CONTROLLER tag);
    public void addViewWithTag(AbstractViewController controller, AbstractViewController.CONTROLLER tag);
    public void setLeftMenuView(AbstractViewController controller, AbstractViewController.CONTROLLER tag);
    public void presentLeftMenu();
    public void statusByLeftMenu(boolean status);
}
