package com.cbedoy.apprende.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.cbedoy.apprende.activity.ApprendeActivity;
import com.cbedoy.apprende.activity.QuestionerActivity;
import com.cbedoy.apprende.business.MasterBusinessController;
import com.cbedoy.apprende.business.category.CategoryBusinessController;
import com.cbedoy.apprende.business.login.LoginBusinessController;
import com.cbedoy.apprende.business.preview.PreviewBusinessController;
import com.cbedoy.apprende.business.profile.ProfileBusinessController;
import com.cbedoy.apprende.business.question.QuestionBusinessController;
import com.cbedoy.apprende.business.singup.SingupBusinessController;
import com.cbedoy.apprende.business.subcategory.SubcategoryBusinessController;
import com.cbedoy.apprende.interfaces.IAppViewManager;
import com.cbedoy.apprende.viewcontroller.AbstractViewController;
import com.cbedoy.apprende.viewcontroller.CategoryViewController;
import com.cbedoy.apprende.viewcontroller.LoginViewController;
import com.cbedoy.apprende.viewcontroller.PreviewViewController;
import com.cbedoy.apprende.viewcontroller.ProfileViewController;
import com.cbedoy.apprende.viewcontroller.QuestionViewController;
import com.cbedoy.apprende.viewcontroller.SingUpViewController;
import com.cbedoy.apprende.viewcontroller.SubcategoryViewController;
import com.cbedoy.apprende.widgets.LevelSelectorView;
import com.cbedoy.apprende.widgets.MessageRepresentation;
import com.cbedoy.apprende.widgets.NavigationBar;

import java.util.HashMap;

import static com.cbedoy.apprende.viewcontroller.AbstractViewController.CONTROLLER;

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
                this.rest_port = 7777;
                this.rest_url = "http://10.75.181.55";
                break;
            case 1: //pre
                this.rest_port = 8080;
                this.rest_url = "http://10.75.181.55";
                break;
            case 2: //pro
                this.rest_port = 8080;
                this.rest_url = "http://10.75.181.55";
                break;
            default:
                this.rest_port = 8080;
                this.rest_url = "http://10.75.181.55";
                break;
        }
    }

    public static final String MEDIA_URL = "http://10.75.181.55:7777/media/";

    public boolean isProduction() {
        return env == 2;
    }

    public boolean isPreProduction() {
        return env == 1;
    }

    public void performApprendeFlow(ApprendeActivity apprendeActivity){

        Context context = apprendeActivity.getApplicationContext();
        Bundle extras = apprendeActivity.getIntent().getExtras();
        IAppViewManager appViewManager = apprendeActivity;
        CONTROLLER tag;

        RestService restService = new RestService();
        restService.setURL(this.rest_url);
        restService.setPort(this.rest_port);

        MementoHandler mementoHandler = new MementoHandler();
        HashMap<String, Object> data = new HashMap<String, Object>();
        mementoHandler.setStateForOwner(data, this);

        MessageRepresentation messageRepresentation = new MessageRepresentation(apprendeActivity);
        LevelSelectorView levelSelectorView = new LevelSelectorView(apprendeActivity);
        InformationService informationService = new InformationService();
        MasterBusinessController masterBusinessController = new MasterBusinessController();

        tag = CONTROLLER.LOGIN;

        NavigationBar loginNavigationBar = new NavigationBar();
        LoginViewController loginViewController = new LoginViewController();
        LoginBusinessController loginBusinessController = new LoginBusinessController();
        loginBusinessController.setMementoHandler(mementoHandler);
        loginBusinessController.setMessageRepresentationHandler(messageRepresentation);
        loginBusinessController.setLoginRepresentationHandler(loginViewController);
        loginBusinessController.setLoginInformationHandler(informationService);
        loginBusinessController.setLoginTransactionHandler(masterBusinessController);
        loginViewController.setAppViewManager(appViewManager);
        loginViewController.setContext(context);
        loginViewController.setMessageRepresentation(messageRepresentation);
        loginViewController.setTag(tag);
        loginViewController.setLoginRepresentationDelegate(loginBusinessController);
        loginViewController.setNavigationBar(loginNavigationBar);
        appViewManager.addViewWithTag(loginViewController, tag);

        tag = CONTROLLER.SIGHUP;
        NavigationBar singupNavigationBar = new NavigationBar();
        SingUpViewController singUpViewController = new SingUpViewController();
        SingupBusinessController singupBusinessController = new SingupBusinessController();
        singupBusinessController.setMementoHandler(mementoHandler);
        singupBusinessController.setMessageRepresentationHandler(messageRepresentation);
        singupBusinessController.setSingupRepresentationHandler(singUpViewController);
        singupBusinessController.setSingupInformationHandler(informationService);
        singupBusinessController.setSingupTransactionHandler(masterBusinessController);
        singUpViewController.setAppViewManager(appViewManager);
        singUpViewController.setContext(context);
        singUpViewController.setMessageRepresentation(messageRepresentation);
        singUpViewController.setTag(tag);
        singUpViewController.setSingupRepresentationDelegate(singupBusinessController);
        singUpViewController.setNavigationBar(singupNavigationBar);
        appViewManager.addViewWithTag(singUpViewController, tag);
        apprendeActivity.setCameraInformationDelegate(singUpViewController);
        singUpViewController.setCameraInformationHandler(apprendeActivity);


        tag = CONTROLLER.PROFILE;
        NavigationBar profileNavigationBar = new NavigationBar();
        ProfileViewController profileViewController = new ProfileViewController();
        ProfileBusinessController profileBusinessController = new ProfileBusinessController();
        profileBusinessController.setMessageRepresentationHandler(messageRepresentation);
        profileBusinessController.setMementoHandler(mementoHandler);
        profileBusinessController.setProfileRepresentationHandler(profileViewController);
        profileBusinessController.setProfileInformationHandler(informationService);
        profileBusinessController.setProfileTransactionHandler(masterBusinessController);
        profileViewController.setAppViewManager(appViewManager);
        profileViewController.setContext(context);
        profileViewController.setMessageRepresentation(messageRepresentation);
        profileViewController.setTag(tag);
        profileViewController.setProfileRepresentationDelegate(profileBusinessController);
        profileViewController.setNavigationBar(profileNavigationBar);
        appViewManager.addViewWithTag(profileViewController, tag);

        tag = CONTROLLER.CATEGORY;
        NavigationBar categoryNabigationBar = new NavigationBar();
        CategoryViewController categoryViewController = new CategoryViewController();
        CategoryBusinessController categoryBusinessController = new CategoryBusinessController();
        categoryBusinessController.setMementoHandler(mementoHandler);
        categoryBusinessController.setMessageRepresentationHandler(messageRepresentation);
        categoryBusinessController.setCategoryRepresentationHandler(categoryViewController);
        categoryBusinessController.setCategoryInformationHandler(informationService);
        categoryBusinessController.setCategoryTransactionHandler(masterBusinessController);
        categoryViewController.setAppViewManager(appViewManager);
        categoryViewController.setContext(context);
        categoryViewController.setMessageRepresentation(messageRepresentation);
        categoryViewController.setTag(tag);
        categoryViewController.setCategoryRepresentationDelegate(categoryBusinessController);
        categoryViewController.setNavigationBar(categoryNabigationBar);
        appViewManager.addViewWithTag(categoryViewController, tag);

        tag = CONTROLLER.SUBCATEGORY;
        NavigationBar subcategoryNavigationBar = new NavigationBar();
        SubcategoryViewController subcategoryViewController = new SubcategoryViewController();
        SubcategoryBusinessController subcategoryBusinessController = new SubcategoryBusinessController();
        subcategoryBusinessController.setMessageRepresentationHandler(messageRepresentation);
        subcategoryBusinessController.setMementoHandler(mementoHandler);
        subcategoryBusinessController.setSubcategoryRepresentationHandler(subcategoryViewController);
        subcategoryBusinessController.setSubcategoryTransactionHandler(masterBusinessController);
        subcategoryBusinessController.setSubcategoryInformationHandler(informationService);
        subcategoryViewController.setAppViewManager(appViewManager);
        subcategoryViewController.setContext(context);
        subcategoryViewController.setMessageRepresentation(messageRepresentation);
        subcategoryViewController.setTag(tag);
        subcategoryViewController.setSubcategoryRepresentationDelegate(subcategoryBusinessController);
        subcategoryViewController.setNavigationBar(subcategoryNavigationBar);
        appViewManager.addViewWithTag(subcategoryViewController, tag);

        tag = CONTROLLER.PREVIEW;
        NavigationBar previewNavigationBar = new NavigationBar();
        PreviewViewController previewViewController = new PreviewViewController();
        PreviewBusinessController previewBusinessController = new PreviewBusinessController();
        previewBusinessController.setMessageRepresentationHandler(messageRepresentation);
        previewBusinessController.setMementoHandler(mementoHandler);
        previewBusinessController.setPreviewRepresentationHandler(previewViewController);
        previewBusinessController.setPreviewTransactionHandler(masterBusinessController);
        previewBusinessController.setPreviewInformationHandler(informationService);
        previewViewController.setAppViewManager(appViewManager);
        previewViewController.setContext(context);
        previewViewController.setMessageRepresentation(messageRepresentation);
        previewViewController.setTag(tag);
        previewViewController.setPreviewRepresentationDelegate(previewBusinessController);
        previewViewController.setLevelSelectorView(levelSelectorView);
        previewViewController.setNavigationBar(previewNavigationBar);
        levelSelectorView.setLevelSelectorViewDelegate(previewViewController);
        appViewManager.addViewWithTag(previewViewController, tag);

        tag = CONTROLLER.APPREMDE;
        QuestionViewController questionViewController = new QuestionViewController();
        QuestionBusinessController questionBusinessController = new QuestionBusinessController();
        questionBusinessController.setMementoHandler(mementoHandler);
        questionBusinessController.setMessageRepresentationHandler(messageRepresentation);
        questionBusinessController.setQuestionRepresentationHandler(questionViewController);
        questionBusinessController.setQuestionInformationHandler(informationService);
        questionBusinessController.setQuestionTransactionHandler(masterBusinessController);
        questionViewController.setAppViewManager(appViewManager);
        questionViewController.setContext(context);
        questionViewController.setMessageRepresentation(messageRepresentation);
        questionViewController.setTag(tag);
        questionViewController.setQuestionRepresentationDelegate(questionBusinessController);
        questionViewController.setMementoHandler(mementoHandler);
        appViewManager.addViewWithTag(questionViewController, tag);

        informationService.setCategoryInformationDelegate(categoryBusinessController);
        informationService.setLoginInformationDelegate(loginBusinessController);
        informationService.setPreviewInformationDelegate(previewBusinessController);
        informationService.setSubcategoryInformationDelegate(subcategoryBusinessController);
        informationService.setProfileInformationDelegate(profileBusinessController);
        informationService.setSingupInformationHandler(singupBusinessController);
        informationService.setQuestionInformationDelegate(questionBusinessController);
        informationService.setRestService(restService);
        informationService.setMementoHandler(mementoHandler);

        masterBusinessController.setMessageRepresentationHandler(messageRepresentation);
        masterBusinessController.setMementoHandler(mementoHandler);
        masterBusinessController.setCategoryTransactionDelegate(categoryBusinessController);
        masterBusinessController.setLoginTransactionDelegate(loginBusinessController);
        masterBusinessController.setPreviewTransactionDelegate(previewBusinessController);
        masterBusinessController.setSingupTransactionDelegate(singupBusinessController);
        masterBusinessController.setSubcategoryTransactionDelegate(subcategoryBusinessController);
        masterBusinessController.setProfileTransactionDelegate(profileBusinessController);
        masterBusinessController.setQuestionTransactionDelegate(questionBusinessController);

        masterBusinessController.startApprendeApp();
    }
}
