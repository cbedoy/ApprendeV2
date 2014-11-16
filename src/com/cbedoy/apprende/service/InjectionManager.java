package com.cbedoy.apprende.service;

import android.content.Context;

import com.cbedoy.apprende.activity.MainActivity;
import com.cbedoy.apprende.business.MasterBusinessController;
import com.cbedoy.apprende.business.category.CategoryBusinessController;
import com.cbedoy.apprende.business.feed.FeedBusinessController;
import com.cbedoy.apprende.business.login.LoginBusinessController;
import com.cbedoy.apprende.business.preview.PreviewBusinessController;
import com.cbedoy.apprende.business.profile.ProfileBusinessController;
import com.cbedoy.apprende.business.question.QuestionBusinessController;
import com.cbedoy.apprende.business.singup.SignUpBusinessController;
import com.cbedoy.apprende.business.subcategory.SubcategoryBusinessController;
import com.cbedoy.apprende.business.timeout.TimeOutBusinessController;
import com.cbedoy.apprende.interfaces.IAppViewManager;
import com.cbedoy.apprende.viewcontroller.CategoryViewController;
import com.cbedoy.apprende.viewcontroller.LeftMenuViewController;
import com.cbedoy.apprende.viewcontroller.LoginViewController;
import com.cbedoy.apprende.viewcontroller.PreviewViewController;
import com.cbedoy.apprende.viewcontroller.ProfileViewController;
import com.cbedoy.apprende.viewcontroller.QuestionViewController;
import com.cbedoy.apprende.viewcontroller.SignUpViewController;
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
                this.rest_port = 8080;
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

    public static final String MEDIA_URL = "http://10.75.181.55:8080/media/";

    public boolean isProduction() {
        return env == 2;
    }

    public boolean isPreProduction() {
        return env == 1;
    }

    public void performApprendeFlow(MainActivity mainActivity){

        Context context = mainActivity.getApplicationContext();
        IAppViewManager appViewManager = mainActivity;
        CONTROLLER tag;

        RestService restService = new RestService();
        restService.setURL(this.rest_url);
        restService.setPort(this.rest_port);

        MementoHandler mementoHandler = new MementoHandler();
        HashMap<String, Object> data = new HashMap<String, Object>();
        mementoHandler.setStateForOwner(data, this);

        MessageRepresentation messageRepresentation = new MessageRepresentation(mainActivity);
        LevelSelectorView levelSelectorView = new LevelSelectorView(mainActivity);
        InformationService informationService = new InformationService();
        MasterBusinessController masterBusinessController = new MasterBusinessController();
        TimeOutBusinessController timeOutBusinessController = new TimeOutBusinessController();
        timeOutBusinessController.setTransactionHandler(masterBusinessController);

        mainActivity.setTimeOutTransactionDelegate(timeOutBusinessController);
        mainActivity.setMessageRepresentationHandler(messageRepresentation);

        tag = CONTROLLER.LOGIN;
        NavigationBar loginNavigationBar = new NavigationBar();
        LoginViewController loginViewController = new LoginViewController();
        LoginBusinessController loginBusinessController = new LoginBusinessController();
        loginBusinessController.setMementoHandler(mementoHandler);
        loginBusinessController.setMessageRepresentationHandler(messageRepresentation);
        loginBusinessController.setRepresentationHandler(loginViewController);
        loginBusinessController.setInformationHandler(informationService);
        loginBusinessController.setTransactionHandler(masterBusinessController);
        loginViewController.setAppViewManager(appViewManager);
        loginViewController.setContext(context);
        loginViewController.setMessageRepresentation(messageRepresentation);
        loginViewController.setTag(tag);
        loginViewController.setLoginRepresentationDelegate(loginBusinessController);
        loginViewController.setNavigationBar(loginNavigationBar);
        appViewManager.addViewWithTag(loginViewController, tag);

        tag = CONTROLLER.SIGNUP;
        NavigationBar signUpNavigationBar = new NavigationBar();
        SignUpViewController signUpViewController = new SignUpViewController();
        SignUpBusinessController signUpBusinessController = new SignUpBusinessController();
        signUpBusinessController.setMementoHandler(mementoHandler);
        signUpBusinessController.setMessageRepresentationHandler(messageRepresentation);
        signUpBusinessController.setRepresentationHandler(signUpViewController);
        signUpBusinessController.setInformationHandler(informationService);
        signUpBusinessController.setTransactionHandler(masterBusinessController);
        signUpViewController.setAppViewManager(appViewManager);
        signUpViewController.setContext(context);
        signUpViewController.setMessageRepresentation(messageRepresentation);
        signUpViewController.setTag(tag);
        signUpViewController.setSingupRepresentationDelegate(signUpBusinessController);
        signUpViewController.setNavigationBar(signUpNavigationBar);
        appViewManager.addViewWithTag(signUpViewController, tag);
        mainActivity.setCameraInformationDelegate(signUpViewController);
        signUpViewController.setCameraInformationHandler(mainActivity);


        tag = CONTROLLER.PROFILE;
        NavigationBar profileNavigationBar = new NavigationBar();
        ProfileViewController profileViewController = new ProfileViewController();
        ProfileBusinessController profileBusinessController = new ProfileBusinessController();
        profileBusinessController.setMessageRepresentationHandler(messageRepresentation);
        profileBusinessController.setMementoHandler(mementoHandler);
        profileBusinessController.setRepresentationHandler(profileViewController);
        profileBusinessController.setInformationHandler(informationService);
        profileBusinessController.setTransactionHandler(masterBusinessController);
        profileViewController.setAppViewManager(appViewManager);
        profileViewController.setContext(context);
        profileViewController.setMessageRepresentation(messageRepresentation);
        profileViewController.setTag(tag);
        profileViewController.setProfileRepresentationDelegate(profileBusinessController);
        profileViewController.setNavigationBar(profileNavigationBar);
        appViewManager.addViewWithTag(profileViewController, tag);

        tag = CONTROLLER.CATEGORY;
        NavigationBar categoryNavigationBar = new NavigationBar();
        CategoryViewController categoryViewController = new CategoryViewController();
        CategoryBusinessController categoryBusinessController = new CategoryBusinessController();
        categoryBusinessController.setMementoHandler(mementoHandler);
        categoryBusinessController.setMessageRepresentationHandler(messageRepresentation);
        categoryBusinessController.setRepresentationHandler(categoryViewController);
        categoryBusinessController.setInformationHandler(informationService);
        categoryBusinessController.setTransactionHandler(masterBusinessController);
        categoryViewController.setAppViewManager(appViewManager);
        categoryViewController.setContext(context);
        categoryViewController.setMessageRepresentation(messageRepresentation);
        categoryViewController.setTag(tag);
        categoryViewController.setCategoryRepresentationDelegate(categoryBusinessController);
        categoryViewController.setNavigationBar(categoryNavigationBar);
        appViewManager.addViewWithTag(categoryViewController, tag);

        tag = CONTROLLER.SUBCATEGORY;
        NavigationBar subcategoryNavigationBar = new NavigationBar();
        SubcategoryViewController subcategoryViewController = new SubcategoryViewController();
        SubcategoryBusinessController subcategoryBusinessController = new SubcategoryBusinessController();
        subcategoryBusinessController.setMessageRepresentationHandler(messageRepresentation);
        subcategoryBusinessController.setMementoHandler(mementoHandler);
        subcategoryBusinessController.setRepresentationHandler(subcategoryViewController);
        subcategoryBusinessController.setTransactionHandler(masterBusinessController);
        subcategoryBusinessController.setInformationHandler(informationService);
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
        previewBusinessController.setRepresentationHandler(previewViewController);
        previewBusinessController.setTransactionHandler(masterBusinessController);
        previewBusinessController.setInformationHandler(informationService);
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
        questionBusinessController.setRepresentationHandler(questionViewController);
        questionBusinessController.setInformationHandler(informationService);
        questionBusinessController.setTransactionHandler(masterBusinessController);
        questionViewController.setAppViewManager(appViewManager);
        questionViewController.setContext(context);
        questionViewController.setMessageRepresentation(messageRepresentation);
        questionViewController.setTag(tag);
        questionViewController.setQuestionRepresentationDelegate(questionBusinessController);
        questionViewController.setMementoHandler(mementoHandler);
        appViewManager.addViewWithTag(questionViewController, tag);

        tag = CONTROLLER.FEED;
        LeftMenuViewController leftMenuViewController = new LeftMenuViewController();
        FeedBusinessController feedBusinessController = new FeedBusinessController();
        feedBusinessController.setMementoHandler(mementoHandler);
        feedBusinessController.setMessageRepresentationHandler(messageRepresentation);
        feedBusinessController.setInformationHandler(informationService);
        feedBusinessController.setTransactionHandler(masterBusinessController);
        feedBusinessController.setRepresentationHandler(leftMenuViewController);
        leftMenuViewController.setAppViewManager(appViewManager);
        leftMenuViewController.setContext(context);
        leftMenuViewController.setMessageRepresentation(messageRepresentation);
        leftMenuViewController.setTag(tag);
        leftMenuViewController.setFeedRepresentationDelegate(feedBusinessController);
        leftMenuViewController.setMementoHandler(mementoHandler);
        appViewManager.addViewWithTag(leftMenuViewController, tag);
        appViewManager.setLeftMenuView(leftMenuViewController, tag);

        informationService.setCategoryInformationDelegate(categoryBusinessController);
        informationService.setLoginInformationDelegate(loginBusinessController);
        informationService.setPreviewInformationDelegate(previewBusinessController);
        informationService.setSubcategoryInformationDelegate(subcategoryBusinessController);
        informationService.setProfileInformationDelegate(profileBusinessController);
        informationService.setSingupInformationHandler(signUpBusinessController);
        informationService.setQuestionInformationDelegate(questionBusinessController);
        informationService.setFeedInformationDelegate(feedBusinessController);
        informationService.setRestService(restService);
        informationService.setMementoHandler(mementoHandler);

        masterBusinessController.setMessageRepresentationHandler(messageRepresentation);
        masterBusinessController.setMementoHandler(mementoHandler);
        masterBusinessController.setCategoryTransactionDelegate(categoryBusinessController);
        masterBusinessController.setLoginTransactionDelegate(loginBusinessController);
        masterBusinessController.setPreviewTransactionDelegate(previewBusinessController);
        masterBusinessController.setSingupTransactionDelegate(signUpBusinessController);
        masterBusinessController.setSubcategoryTransactionDelegate(subcategoryBusinessController);
        masterBusinessController.setProfileTransactionDelegate(profileBusinessController);
        masterBusinessController.setQuestionTransactionDelegate(questionBusinessController);
        masterBusinessController.setTimeOutTransactionDelegate(timeOutBusinessController);
        masterBusinessController.setFeedTransactionDelegate(feedBusinessController);
        masterBusinessController.startApprendeApp();
    }
}
