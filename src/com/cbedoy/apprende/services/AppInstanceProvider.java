package com.cbedoy.apprende.services;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;

import com.cbedoy.apprende.bussiness.MasterController;
import com.cbedoy.apprende.interfaces.viewdelegates.ICourseViewDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.IFeedViewDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.ILoginViewDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.IProfileViewDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.IThemeViewDelegate;
import com.cbedoy.apprende.keysets.ServiceKeySet;
import com.cbedoy.apprende.keysets.UserKeySet;

public class AppInstanceProvider {
	
	private static AppInstanceProvider  appInstanceProvider;
	public static Typeface 				boldFont;	
	public static Typeface 				regularFont;	
	public static Typeface 				thinFont;	
	public static Typeface 				lightFont;
	private LoginService				loginService;
	private ProfileService				profileService;
	private ThemeService				themeService;
	private CourseService				courseService;
	private FeedService					feedService;
	private CBRESTClient				restClient;
	private String						urlResponse;
	private MasterController			masterController;

	public static AppInstanceProvider getInstance(Context context){
		if(appInstanceProvider == null) {
            appInstanceProvider = new AppInstanceProvider(context);
        }
		return appInstanceProvider;
	}
	
	public static AppInstanceProvider getInstance(){
		if(appInstanceProvider == null) {
            appInstanceProvider = new AppInstanceProvider();
        }
		return appInstanceProvider;
	}
		
	private AppInstanceProvider(Context context){
		reloadFonts(context);
	}
	
	private void reloadFonts(Context context) {
		boldFont 		= Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
		regularFont 	= Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
		thinFont 		= Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
		lightFont 		= Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");		
	}

	private AppInstanceProvider(){
	}

    public MasterController instanceServiceLogin(ILoginViewDelegate loginViewDelegate, HashMap<Object, Object> dataModel, ServiceKeySet serviceKeySet){

    	loginService   						= LoginService.getInstance();
        masterController   					= MasterController.getInstance();
        urlResponse							= serviceKeySet.toString();
        urlResponse 						= urlResponse.replace("$username", dataModel.get(UserKeySet.USERNAME).toString());
        urlResponse							= urlResponse.replace("$password", dataModel.get(UserKeySet.PASSWORD).toString());
        restClient             				= CBRESTClient.getInstance();
        restClient.setURL(urlResponse);
        loginService.setRestClient(restClient);
        loginService.setLoginViewDelegate(loginViewDelegate);
        masterController.setAnsycTask(loginService);
        return masterController;
    }
    
    public MasterController instanceServiceProfile(IProfileViewDelegate profileViewDelegate, ServiceKeySet serviceKeySet){

    	profileService						= ProfileService.getInstance();
        masterController   					= MasterController.getInstance();
        urlResponse							= serviceKeySet.toString();
        restClient             				= CBRESTClient.getInstance();
        restClient.setURL(urlResponse);
        profileService.setViewDelegate(profileViewDelegate);
        masterController.setAnsycTask(profileService);
        return masterController;
    }

    public MasterController instanceServiceTheme(IThemeViewDelegate viewDelegate, ServiceKeySet serviceKeySet){

        themeService           				= ThemeService.getInstance();
        masterController   					= MasterController.getInstance();
        urlResponse							= serviceKeySet.toString();
        restClient             				= CBRESTClient.getInstance();
        restClient.setURL(urlResponse);
        themeService.setViewDelegate(viewDelegate);
        masterController.setAnsycTask(themeService);
        return masterController;
    }


    public MasterController instanceServiceCourse(ICourseViewDelegate viewDelegate, ServiceKeySet serviceKeySet){

    	courseService       				= CourseService.getInstance();
        masterController   					= MasterController.getInstance();
        urlResponse							= serviceKeySet.toString();
        restClient             				= CBRESTClient.getInstance();
        restClient.setURL(urlResponse);
        courseService.setViewDelegate(viewDelegate);
        masterController.setAnsycTask(courseService);
        return masterController;
    }

    public MasterController instanceServiceFeed(IFeedViewDelegate viewDelegate, ServiceKeySet serviceKeySet){

        feedService             			= FeedService.getInstance();
        masterController   					= MasterController.getInstance();
        urlResponse							= serviceKeySet.toString();
        restClient             				= CBRESTClient.getInstance();
        restClient.setURL(urlResponse);
        feedService.setViewDelegate(viewDelegate);
        masterController.setAnsycTask(feedService);
        return masterController;
    }
    
    public Bitmap getImageFromURL(String placeholder){
    	try {
    		String urlFacebook				= new String("https://graph.facebook.com/$placeholder/picture");
    		urlFacebook 					= urlFacebook.replace("$placeholder", placeholder);
    		URL	urlImage					= new URL(urlFacebook);
    		Bitmap bitmap					= BitmapFactory.decodeStream(urlImage.openConnection().getInputStream());
    		return bitmap;
    	} catch (IOException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
        
}
