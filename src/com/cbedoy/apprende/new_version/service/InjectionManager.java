package com.cbedoy.apprende.new_version.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.cbedoy.apprende.new_version.InAppActivity;
import com.cbedoy.apprende.new_version.NotificationMessages;
import com.cbedoy.apprende.new_version.business.MasterBusinessController;
import com.cbedoy.apprende.new_version.business.category.CategoryBusinessController;
import com.cbedoy.apprende.new_version.business.login.LoginBusinessController;
import com.cbedoy.apprende.new_version.business.preview.PreviewBusinessController;
import com.cbedoy.apprende.new_version.business.profile.ProfileBusinessController;
import com.cbedoy.apprende.new_version.business.singup.SingupBusinessController;
import com.cbedoy.apprende.new_version.business.subcategory.SubcategoryBusinessController;
import com.cbedoy.apprende.new_version.interfaces.IAppViewManager;
import com.cbedoy.apprende.new_version.viewcontroller.CategoryViewController;
import com.cbedoy.apprende.new_version.viewcontroller.LoginViewController;
import com.cbedoy.apprende.new_version.viewcontroller.PreviewViewController;
import com.cbedoy.apprende.new_version.viewcontroller.ProfileViewController;
import com.cbedoy.apprende.new_version.viewcontroller.SingUpViewController;
import com.cbedoy.apprende.new_version.viewcontroller.SubcategoryViewController;

import java.util.HashMap;

/**
 * Created by Carlos on 14/10/2014.
 */
public class InjectionManager
{
    private int env = 0;
    private int rest_port;
    private String rest_url;
    private static InjectionManager _instance;

    public static InjectionManager getInstance() {
        if (_instance == null)
            _instance = new InjectionManager();

        return _instance;
    }

    private InjectionManager() {
        switch (this.env) {
            case 0: //dev
                this.rest_port = 70;
                this.rest_url = "http://staging.pademobile.com:";
                break;
            case 1: //pre
                this.rest_port = 50;
                this.rest_url = "http://staging.pademobile.com:";
                break;
            case 2: //pro
                this.rest_port = 50;
                this.rest_url = "https://www.pademobile.com:";
                break;
            default:
                this.rest_port = 70;
                this.rest_url = "http://staging.pademobile.com:";
                break;
        }
    }

    public boolean isProduction() {
        return env == 2;
    }

    public boolean isPreProduction() {
        return env == 1;
    }

    public void performApprendeFlow(InAppActivity inAppActivity){

        Context context = inAppActivity.getApplicationContext();
        Bundle extras = inAppActivity.getIntent().getExtras();
        IAppViewManager appViewManager = inAppActivity;
        String tag;
        String imageService = extras.getString("image_service");
        Drawable drawable = Utils.getDrawableImage(imageService);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap imageBlur = BlurService.getInstance().blurRenderScript(bitmap, context);

        MasterBusinessController masterBusinessController = new MasterBusinessController();

        RestService restService = new RestService();
        restService.setURL(this.rest_url);
        restService.setPort(this.rest_port);

        MementoHandler mementoHandler = new MementoHandler();
        HashMap<String, Object> data = new HashMap<String, Object>();
        for(String key : extras.keySet())
            data.put(key, extras.get(key));
        mementoHandler.setStateForOwner(data, this);


        NotificationMessages notificationMessages = new NotificationMessages();

        masterBusinessController.setMessageRepresentationHandler(notificationMessages);
        masterBusinessController.setMementoHandler(mementoHandler);

        tag = "LoginViewController";
        LoginViewController loginViewController = new LoginViewController();
        LoginBusinessController loginBusinessController = new LoginBusinessController();
        loginBusinessController.setMementoHandler(mementoHandler);
        loginBusinessController.setMessageRepresentationHandler(notificationMessages);
        loginBusinessController.setLoginRepresentationHandler(loginViewController);
        loginViewController.setAppViewManager(appViewManager);
        loginViewController.setContext(context);
        loginViewController.setNotificationMessages(notificationMessages);
        loginViewController.setTag(tag);
        loginViewController.setLoginRepresentationDelegate(loginBusinessController);
        appViewManager.addViewWithTag(loginViewController, tag);

        tag = "SingUpViewController";
        SingUpViewController singUpViewController = new SingUpViewController();
        SingupBusinessController singupBusinessController = new SingupBusinessController();
        singupBusinessController.setMementoHandler(mementoHandler);
        singupBusinessController.setMessageRepresentationHandler(notificationMessages);
        singupBusinessController.setSingupRepresentationHandler(singUpViewController);
        singUpViewController.setAppViewManager(appViewManager);
        singUpViewController.setContext(context);
        singUpViewController.setNotificationMessages(notificationMessages);
        singUpViewController.setTag(tag);
        singUpViewController.setSingupRepresentationDelegate(singupBusinessController);
        appViewManager.addViewWithTag(singUpViewController, tag);

        tag = "ProfileViewController";
        ProfileViewController profileViewController = new ProfileViewController();
        ProfileBusinessController profileBusinessController = new ProfileBusinessController();
        profileBusinessController.setMessageRepresentationHandler(notificationMessages);
        profileBusinessController.setMementoHandler(mementoHandler);
        profileBusinessController.setProfileRepresentationHandler(profileViewController);
        profileViewController.setAppViewManager(appViewManager);
        profileViewController.setContext(context);
        profileViewController.setNotificationMessages(notificationMessages);
        profileViewController.setTag(tag);
        profileViewController.setProfileRepresentationDelegate(profileBusinessController);
        appViewManager.addViewWithTag(profileViewController, tag);

        tag = "CategoryViewController";
        CategoryViewController categoryViewController = new CategoryViewController();
        CategoryBusinessController categoryBusinessController = new CategoryBusinessController();
        categoryBusinessController.setMementoHandler(mementoHandler);
        categoryBusinessController.setMessageRepresentationHandler(notificationMessages);
        categoryBusinessController.setCategoryRepresentationHandler(categoryViewController);
        categoryViewController.setAppViewManager(appViewManager);
        categoryViewController.setContext(context);
        categoryViewController.setNotificationMessages(notificationMessages);
        categoryViewController.setTag(tag);
        categoryViewController.setCategoryRepresentationDelegate(categoryBusinessController);
        appViewManager.addViewWithTag(categoryViewController, tag);

        tag = "SubcategoryViewController";
        SubcategoryViewController subcategoryViewController = new SubcategoryViewController();
        SubcategoryBusinessController subcategoryBusinessController = new SubcategoryBusinessController();
        subcategoryBusinessController.setMessageRepresentationHandler(notificationMessages);
        subcategoryBusinessController.setMementoHandler(mementoHandler);
        subcategoryBusinessController.setSubcategoryRepresentationHandler(subcategoryViewController);
        subcategoryViewController.setAppViewManager(appViewManager);
        subcategoryViewController.setContext(context);
        subcategoryViewController.setNotificationMessages(notificationMessages);
        subcategoryViewController.setTag(tag);
        subcategoryViewController.setSubcategoryRepresentationDelegate(subcategoryBusinessController);
        appViewManager.addViewWithTag(subcategoryViewController, tag);

        tag = "PreviewViewController";
        PreviewViewController previewViewController = new PreviewViewController();
        PreviewBusinessController previewBusinessController = new PreviewBusinessController();
        previewBusinessController.setMessageRepresentationHandler(notificationMessages);
        previewBusinessController.setMementoHandler(mementoHandler);
        previewBusinessController.setPreviewRepresentationHandler(previewViewController);
        previewViewController.setAppViewManager(appViewManager);
        previewViewController.setContext(context);
        previewViewController.setNotificationMessages(notificationMessages);
        previewViewController.setTag(tag);
        previewViewController.setPreviewRepresentationDelegate(previewBusinessController);
        appViewManager.addViewWithTag(previewViewController, tag);

        masterBusinessController.setCategoryTransactionDelegate(categoryBusinessController);
        masterBusinessController.setLoginTransactionDelegate(loginBusinessController);
        masterBusinessController.setPreviewTransactionDelegate(previewBusinessController);
        masterBusinessController.setSingupTransactionDelegate(singupBusinessController);
        masterBusinessController.setSubcategoryTransactionDelegate(subcategoryBusinessController);
        masterBusinessController.setProfileTransactionDelegate(profileBusinessController);

    }
}
