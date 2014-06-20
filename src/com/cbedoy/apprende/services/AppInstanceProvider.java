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
import com.cbedoy.apprende.interfaces.viewdelegates.IGuyViewDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.ILoginViewDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.IProfileViewDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.IQuestionaryRepresentationDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.IThemeViewDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.IUniversityViewDelegate;
import com.cbedoy.apprende.keysets.ServiceKeySet;
import com.cbedoy.apprende.keysets.ThemeKeySet;
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
	private UniversityService			universityService;
	private QuestionService				questionService;
	private GuysService					guyService;
	private CBRESTClient				restClient;
	private String						urlResponse;
	private MasterController			masterController;
	public static String				SERVER_URL 			= "192.168.0.16:8000";

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

    	loginService   						= new LoginService();
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

    	profileService						= new ProfileService();
        masterController   					= MasterController.getInstance();
        urlResponse							= serviceKeySet.toString();
        restClient             				= CBRESTClient.getInstance();
        restClient.setURL(urlResponse);
        profileService.setRestClient(restClient);
        profileService.setViewDelegate(profileViewDelegate);
        masterController.setAnsycTask(profileService);
        return masterController;
    }
    
    public MasterController instanceServiceGuys(IGuyViewDelegate guyViewDelegate, ServiceKeySet serviceKeySet){

    	guyService							= new GuysService();
        masterController   					= MasterController.getInstance();
        urlResponse							= serviceKeySet.toString();
        restClient             				= CBRESTClient.getInstance();
        restClient.setURL(urlResponse);
        guyService.setRestClient(restClient);
        guyService.setViewDelegate(guyViewDelegate);
        masterController.setAnsycTask(guyService);
        return masterController;
    }

    public MasterController instanceServiceTheme(IThemeViewDelegate viewDelegate, ServiceKeySet serviceKeySet){

        themeService           				= new ThemeService();
        masterController   					= MasterController.getInstance();
        urlResponse							= serviceKeySet.toString();
        restClient             				= CBRESTClient.getInstance();
        restClient.setURL(urlResponse);
        themeService.setRestClient(restClient);
        themeService.setViewDelegate(viewDelegate);
        masterController.setAnsycTask(themeService);
        return masterController;
    }


    public MasterController instanceServiceCourse(ICourseViewDelegate viewDelegate, ServiceKeySet serviceKeySet){

    	courseService       				= new CourseService();
        masterController   					= MasterController.getInstance();
        urlResponse							= serviceKeySet.toString();
        restClient             				= CBRESTClient.getInstance();
        restClient.setURL(urlResponse);
        courseService.setRestClient(restClient);
        courseService.setViewDelegate(viewDelegate);
        masterController.setAnsycTask(courseService);
        return masterController;
    }

    public MasterController instanceServiceFeed(IFeedViewDelegate viewDelegate, ServiceKeySet serviceKeySet){

        feedService             			= new FeedService();
        masterController   					= MasterController.getInstance();
        urlResponse							= serviceKeySet.toString();
        restClient             				= CBRESTClient.getInstance();
        restClient.setURL(urlResponse);
        feedService.setRestClient(restClient);
        feedService.setViewDelegate(viewDelegate);
        masterController.setAnsycTask(feedService);
        return masterController;
    }
    
    public MasterController instanceServiceUniversity(IUniversityViewDelegate viewDelegate, ServiceKeySet serviceKeySet){

        universityService          			= new UniversityService();
        masterController   					= MasterController.getInstance();
        urlResponse							= serviceKeySet.toString();
        restClient             				= CBRESTClient.getInstance();
        restClient.setURL(urlResponse);
        universityService.setRestClient(restClient);
        universityService.setViewDelegate(viewDelegate);
        masterController.setAnsycTask(universityService);
        return masterController;
    }
    
    public MasterController instanceServiceQuestionary(IQuestionaryRepresentationDelegate viewDelegate, ServiceKeySet serviceKeySet) {
		questionService             		= new QuestionService();
        masterController   					= MasterController.getInstance();
        urlResponse							= serviceKeySet.toString();
        restClient             				= CBRESTClient.getInstance();
        restClient.setURL(urlResponse);
        questionService.setRestClient(restClient);
        questionService.setViewDelegate(viewDelegate);
        masterController.setAnsycTask(questionService);
        return masterController;
		
	}
    
    public MasterController instanceServiceQuestionary(IQuestionaryRepresentationDelegate viewDelegate, HashMap<Object, Object> dataModel, ServiceKeySet serviceKeySet) {
		questionService             		= new QuestionService();
        masterController   					= MasterController.getInstance();
        urlResponse							= serviceKeySet.toString();
        urlResponse 						= urlResponse.replace("$theme", dataModel.get(ThemeKeySet.ID).toString());
        urlResponse 						= urlResponse.replace("$level", dataModel.get(ThemeKeySet.LEVEL).toString());
        restClient             				= CBRESTClient.getInstance();
        restClient.setURL(urlResponse);
        questionService.setRestClient(restClient);
        questionService.setViewDelegate(viewDelegate);
        masterController.setAnsycTask(questionService);
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
