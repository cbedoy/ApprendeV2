package com.cbedoy.apprende.service;



import com.cbedoy.apprende.business.category.interfaces.ICategoryInformationDelegate;
import com.cbedoy.apprende.business.category.interfaces.ICategoryInformationHandler;
import com.cbedoy.apprende.business.feed.interfaces.IFeedInformationDelegate;
import com.cbedoy.apprende.business.feed.interfaces.IFeedInformationHandler;
import com.cbedoy.apprende.business.guy.interfaces.IGuyInformationDelegate;
import com.cbedoy.apprende.business.guy.interfaces.IGuyInformationHandler;
import com.cbedoy.apprende.business.login.interfaces.ILoginInformationDelegate;
import com.cbedoy.apprende.business.login.interfaces.ILoginInformationHandler;
import com.cbedoy.apprende.business.preview.interfaces.IPreviewInformationDelegate;
import com.cbedoy.apprende.business.preview.interfaces.IPreviewInformationHandler;
import com.cbedoy.apprende.business.profile.interfaces.IProfileInformationDelegate;
import com.cbedoy.apprende.business.profile.interfaces.IProfileInformationHandler;
import com.cbedoy.apprende.business.question.interfaces.IQuestionInformationDelegate;
import com.cbedoy.apprende.business.question.interfaces.IQuestionInformationHandler;
import com.cbedoy.apprende.business.rank.interfaces.IRankInformationDelegate;
import com.cbedoy.apprende.business.rank.interfaces.IRankInformationHandler;
import com.cbedoy.apprende.business.signup.interfaces.ISignUpInformationDelegate;
import com.cbedoy.apprende.business.signup.interfaces.ISignUpInformationHandler;
import com.cbedoy.apprende.business.subcategory.interfaces.ISubcategoryInformationDelegate;
import com.cbedoy.apprende.business.subcategory.interfaces.ISubcategoryInformationHandler;
import com.cbedoy.apprende.interfaces.IMementoHandler;
import com.cbedoy.apprende.interfaces.IRestService;

import java.util.HashMap;

public class InformationService implements IFeedInformationHandler, IGuyInformationHandler, ICategoryInformationHandler, IPreviewInformationHandler, ISubcategoryInformationHandler, ISignUpInformationHandler, ILoginInformationHandler, IProfileInformationHandler, IQuestionInformationHandler, IRankInformationHandler {

    private IRestService restService;
    private IMementoHandler mementoHandler;
    private ICategoryInformationDelegate categoryInformationDelegate;
    private ISubcategoryInformationDelegate subcategoryInformationDelegate;
    private IPreviewInformationDelegate previewInformationDelegate;
    private IProfileInformationDelegate profileInformationDelegate;
    private ILoginInformationDelegate loginInformationDelegate;
    private ISignUpInformationDelegate singupInformationHandler;
    private IQuestionInformationDelegate questionInformationDelegate;
    private IFeedInformationDelegate feedInformationDelegate;
    private IRankInformationDelegate rankInformationDelegate;
    private IGuyInformationDelegate guyInformationDelegate;

    public void setGuyInformationDelegate(IGuyInformationDelegate guyInformationDelegate) {
        this.guyInformationDelegate = guyInformationDelegate;
    }

    public void setFeedInformationDelegate(IFeedInformationDelegate feedInformationDelegate) {
        this.feedInformationDelegate = feedInformationDelegate;
    }

    public void setCategoryInformationDelegate(ICategoryInformationDelegate categoryInformationDelegate) {
        this.categoryInformationDelegate = categoryInformationDelegate;
    }

    public void setLoginInformationDelegate(ILoginInformationDelegate loginInformationDelegate) {
        this.loginInformationDelegate = loginInformationDelegate;
    }

    public void setMementoHandler(IMementoHandler mementoHandler) {
        this.mementoHandler = mementoHandler;
    }

    public void setPreviewInformationDelegate(IPreviewInformationDelegate previewInformationDelegate) {
        this.previewInformationDelegate = previewInformationDelegate;
    }

    public void setProfileInformationDelegate(IProfileInformationDelegate profileInformationDelegate) {
        this.profileInformationDelegate = profileInformationDelegate;
    }

    public void setRestService(IRestService restService) {
        this.restService = restService;
    }

    public void setSingupInformationHandler(ISignUpInformationDelegate singupInformationHandler) {
        this.singupInformationHandler = singupInformationHandler;
    }

    public void setSubcategoryInformationDelegate(ISubcategoryInformationDelegate subcategoryInformationDelegate) {
        this.subcategoryInformationDelegate = subcategoryInformationDelegate;
    }

    public void setQuestionInformationDelegate(IQuestionInformationDelegate questionInformationDelegate) {
        this.questionInformationDelegate = questionInformationDelegate;
    }


    public void setRankInformationDelegate(IRankInformationDelegate rankInformationDelegate) {
        this.rankInformationDelegate = rankInformationDelegate;
    }

    @Override
    public void performCategoriesRequest() {
        String url = "/apprende/course/get/";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                categoryInformationDelegate.categoryResponse(response);
            }
        };
        restService.request(url, parameters, callback);
    }

    @Override
    public void performLoginRequest() {
        String url = "/apprende/user/get/$username/$password/";
        Memento memento = mementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("$username", data.get("username"));
        parameters.put("$password", data.get("password"));
        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                loginInformationDelegate.loginResponse(response);
            }
        };
        restService.request(url, parameters, callback);
    }

    @Override
    public void performPreviewRequestWithData(HashMap<String, Object> information) {
        String url = "/apprende/exam/get/$theme/$level";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("$theme", information.get("theme"));
        parameters.put("$level", information.get("level"));
        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                previewInformationDelegate.previewResponse(response);
            }
        };
        restService.request(url, parameters, callback);
    }

    @Override
    public void performProfileRequest() {
        String url = "url";
        Memento memento = mementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        //TODO get parameters from memento
        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                profileInformationDelegate.profileResponse(response);
            }
        };
        restService.request(url, parameters, callback);
    }

    @Override
    public void performSingupRequest() {
        String url = "url";
        Memento memento = mementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        //TODO get parameters from memento
        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                singupInformationHandler.singupResponse(response);
            }
        };
        restService.request(url, parameters, callback);
    }

    @Override
    public void performSubcategoryRequest() {
        String url = "/apprende/theme/get/$categorySelected/";
        Memento memento = mementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("$categorySelected", ((HashMap)data.get("category_selected")).get("pk"));
        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                subcategoryInformationDelegate.subcategoryResponse(response);
            }
        };
        restService.request(url, parameters, callback);
    }

    @Override
    public void performQuestionaryRequest() {
        String url = "/apprende/user/get/$username/$password/";
        Memento memento = mementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("$username", data.get("username"));
        parameters.put("$password", data.get("password"));
        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                loginInformationDelegate.loginResponse(response);
            }
        };
        restService.request(url, parameters, callback);
    }

    @Override
    public void sendQuestionaryInformation() {
        String url = "/apprende/feed/new/$level/$correct/$wrongs/$points/$player";
        Memento memento = mementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> exam_results = (HashMap<String, Object>)data.get("exam_results");
        HashMap<String, Object> login_response = (HashMap<String, Object>) data.get("login_response");
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("$level", exam_results.get("level_factor"));
        parameters.put("$points", exam_results.get("level_points"));
        parameters.put("$player", login_response.get("pk"));
        parameters.put("$wrongs", exam_results.get("level_wrongs"));
        parameters.put("$correct", exam_results.get("level_corrects"));
        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                questionInformationDelegate.questionaryResponse(response);
            }
        };
        restService.request(url, parameters, callback);
    }

    @Override
    public void perfomFeedbackRequest() {
        //TODO
    }

    @Override
    public void performRankRequest() {
        //TODO
    }

    @Override
    public void requestGuyData() {
        //TODO
    }

    @Override
    public void sendMessageToGuy() {
        //TODO
    }
}
