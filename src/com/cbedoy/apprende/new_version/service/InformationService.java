package com.cbedoy.apprende.new_version.service;



import com.cbedoy.apprende.new_version.business.category.interfaces.ICategoryInformationDelegate;
import com.cbedoy.apprende.new_version.business.category.interfaces.ICategoryInformationHandler;
import com.cbedoy.apprende.new_version.business.login.interfaces.ILoginInformationDelegate;
import com.cbedoy.apprende.new_version.business.login.interfaces.ILoginInformationHandler;
import com.cbedoy.apprende.new_version.business.preview.interfaces.IPreviewInformationDelegate;
import com.cbedoy.apprende.new_version.business.preview.interfaces.IPreviewInformationHandler;
import com.cbedoy.apprende.new_version.business.profile.interfaces.IProfileInformationDelegate;
import com.cbedoy.apprende.new_version.business.profile.interfaces.IProfileInformationHandler;
import com.cbedoy.apprende.new_version.business.singup.interfaces.ISingupInformationDelegate;
import com.cbedoy.apprende.new_version.business.singup.interfaces.ISingupInformationHandler;
import com.cbedoy.apprende.new_version.business.subcategory.interfaces.ISubcategoryInformationDelegate;
import com.cbedoy.apprende.new_version.business.subcategory.interfaces.ISubcategoryInformationHandler;
import com.cbedoy.apprende.new_version.interfaces.IMementoHandler;
import com.cbedoy.apprende.new_version.interfaces.IRestService;

import java.util.HashMap;

public class InformationService implements ICategoryInformationHandler, IPreviewInformationHandler, ISubcategoryInformationHandler, ISingupInformationHandler, ILoginInformationHandler, IProfileInformationHandler {

    private IRestService restService;
    private IMementoHandler mementoHandler;
    private ICategoryInformationDelegate categoryInformationDelegate;
    private ISubcategoryInformationDelegate subcategoryInformationDelegate;
    private IPreviewInformationDelegate previewInformationDelegate;
    private IProfileInformationDelegate profileInformationDelegate;
    private ILoginInformationDelegate loginInformationDelegate;
    private ISingupInformationDelegate singupInformationHandler;

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

    public void setSingupInformationHandler(ISingupInformationDelegate singupInformationHandler) {
        this.singupInformationHandler = singupInformationHandler;
    }

    public void setSubcategoryInformationDelegate(ISubcategoryInformationDelegate subcategoryInformationDelegate) {
        this.subcategoryInformationDelegate = subcategoryInformationDelegate;
    }

    @Override
    public void performCategoriesRequest() {

    }

    @Override
    public void performLoginRequest() {
        String url = "url";
        Memento memento = mementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        //TODO get parameters from memento
        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                loginInformationDelegate.loginResponse(response);
            }
        };
        restService.request(url, parameters, callback);
    }

    @Override
    public void performPreviewRequest() {
        String url = "url";
        Memento memento = mementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        //TODO get parameters from memento
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
        String url = "url";
        Memento memento = mementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        //TODO get parameters from memento
        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                subcategoryInformationDelegate.subcategoryResponse(response);
            }
        };
        restService.request(url, parameters, callback);
    }
}
