package com.cbedoy.apprende.new_version.viewcontroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import com.cbedoy.apprende.new_version.interfaces.IAppViewManager;
import com.cbedoy.apprende.new_version.NotificationMessages;

/**
 * Created by Carlos on 14/10/2014.
 */
public abstract class AbstractViewController
{
    protected Context context;
    protected String tag;
    protected IAppViewManager appViewManager;
    protected View view;
    protected Bitmap backgroundBitmap;
    protected NotificationMessages notificationMessages;
    protected boolean buttons_status;

    protected abstract View init();
    public abstract void reload();

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setAppViewManager(IAppViewManager appViewManager) {
        this.appViewManager = appViewManager;
    }

    public void setBackgroundBitmap(Bitmap backgroundBitmap) {
        this.backgroundBitmap = backgroundBitmap;
    }

    public void setButtons_status(boolean buttons_status) {
        this.buttons_status = buttons_status;
    }

    public void setNotificationMessages(NotificationMessages notificationMessages) {
        this.notificationMessages = notificationMessages;
    }

    public void setView(View view) {
        this.view = view;
    }



    public View getView() {
        if(view == null)
            view = init();
        return view;
    }

    public void backPressed() {
        this.appViewManager.getActivity().finish();
    }

    public void nextPressed() {

    }

    public void toogleButtons(boolean status) {
        this.buttons_status = status;
    }

    public boolean onBackPressed() {
        return true;
    }
}
