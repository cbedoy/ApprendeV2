package com.cbedoy.apprende.services;

import android.content.Context;
import android.graphics.Typeface;

import com.cbedoy.apprende.bussiness.MasterController;
import com.cbedoy.apprende.interfaces.viewdelegates.ICourseViewDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.ILoginViewDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.IProfileViewDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.IThemeViewDelegate;
import com.cbedoy.apprende.keysets.ServiceKeySet;
import com.cbedoy.apprende.keysets.UserKeySet;

import java.util.HashMap;

public class AppInstanceProvider {
	private static AppInstanceProvider  appInstanceProvider;
	private Context                     context;
	public static Typeface 				boldFont;	
	public static Typeface 				regularFont;	
	public static Typeface 				thinFont;	
	public static Typeface 				lightFont;	
	private ILoginViewDelegate       	loginViewDelegate;
	//private IThemeViewDelegate			themeViewDelegate;
	private ICourseViewDelegate			courseViewDelegate;
	//private IRegistrationViewDelegate	registrationViewDelegate;
	//private IFeedViewDelegate			feedViewDelegate;
	

	
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

    	LoginService loginService   		= new LoginService();
        MasterController masterController   = MasterController.getInstance();
        String urlResponse					= serviceKeySet.toString();
        urlResponse 						= urlResponse.replace("$username", dataModel.get(UserKeySet.USERNAME).toString());
        urlResponse							= urlResponse.replace("$password", dataModel.get(UserKeySet.PASSWORD).toString());
        CBRESTClient restClient             = new CBRESTClient(urlResponse);
        loginService.setRestClient(restClient);
        loginService.setLoginViewDelegate(loginViewDelegate);
        masterController.setAnsycTask(loginService);
        return masterController;
    }
    
    public MasterController instanceServiceProfile(IProfileViewDelegate profileViewDelegate, ServiceKeySet serviceKeySet){

    	ProfileService profileService		= new ProfileService();
        MasterController masterController   = MasterController.getInstance();
        String urlResponse					= serviceKeySet.toString();
        CBRESTClient restClient             = new CBRESTClient(urlResponse);
        profileService.setRestClient(restClient);
        profileService.setViewDelegate(profileViewDelegate);
        masterController.setAnsycTask(profileService);
        return masterController;
    }

    public MasterController instanceServiceTheme(IThemeViewDelegate viewDelegate, ServiceKeySet serviceKeySet){

        ThemeService themeService           = new ThemeService();
        MasterController masterController   = MasterController.getInstance();
        String urlResponse					= serviceKeySet.toString();
        CBRESTClient restClient             = new CBRESTClient(urlResponse);
        themeService.setRestClient(restClient);
        themeService.setViewDelegate(viewDelegate);
        masterController.setAnsycTask(themeService);
        return masterController;
    }


    public MasterController instanceServiceCourse(ICourseViewDelegate viewDelegate, ServiceKeySet serviceKeySet){

        CourseService   courseService       = new CourseService();
        MasterController masterController   = MasterController.getInstance();
        String urlResponse					= serviceKeySet.toString();
        CBRESTClient restClient             = new CBRESTClient(urlResponse);
        courseService.setRestClient(restClient);
        courseService.setViewDelegate(viewDelegate);
        masterController.setAnsycTask(courseService);
        return masterController;
    }
    
    
    /*
    public MasterController instanceServiceTheme(Activity activity, ServiceKeySet serviceKeySet){

        MasterController masterController               = MasterController.getInstance();
        AsyncServiceController asyncServiceController   = new AsyncServiceController(activity.getApplicationContext());
        String url										= serviceKeySet.toString();
        CBRESTClient restClient                         = new CBRESTClient(url);
        asyncServiceController.setRestClient(restClient);
        asyncServiceController.setAnsycServiceDelegate(asyncServiceDelegate);
        masterController.setAsyncServiceController(asyncServiceController);
        return masterController;
    }
    
    public MasterController instanceServiceCourse(Activity activity, ServiceKeySet serviceKeySet){

        MasterController masterController               = MasterController.getInstance();
        AsyncServiceController asyncServiceController   = new AsyncServiceController(activity.getApplicationContext());
        String url										= serviceKeySet.toString();
        CBRESTClient restClient                         = new CBRESTClient(url);
        asyncServiceController.setRestClient(restClient);
        asyncServiceController.setAnsycServiceDelegate(asyncServiceDelegate);
        masterController.setAsyncServiceController(asyncServiceController);
        return masterController;
    }
    */
    
    
    
    
    
    



	 
}
