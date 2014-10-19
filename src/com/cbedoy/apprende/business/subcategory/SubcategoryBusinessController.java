package com.cbedoy.apprende.business.subcategory;

import com.cbedoy.apprende.business.BusinessController;
import com.cbedoy.apprende.business.subcategory.interfaces.ISubcategoryInformationDelegate;
import com.cbedoy.apprende.business.subcategory.interfaces.ISubcategoryInformationHandler;
import com.cbedoy.apprende.business.subcategory.interfaces.ISubcategoryRepresentationDelegate;
import com.cbedoy.apprende.business.subcategory.interfaces.ISubcategoryRepresentationHandler;
import com.cbedoy.apprende.business.subcategory.interfaces.ISubcategoryTransactionDelegate;
import com.cbedoy.apprende.business.subcategory.interfaces.ISubcategoryTransactionHandler;
import com.cbedoy.apprende.interfaces.IMessageRepresentationHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Carlos on 15/10/2014.
 */
public class SubcategoryBusinessController extends BusinessController implements ISubcategoryTransactionDelegate, ISubcategoryInformationDelegate, ISubcategoryRepresentationDelegate
{
    private ISubcategoryInformationHandler subcategoryInformationHandler;
    private ISubcategoryRepresentationHandler subcategoryRepresentationHandler;
    private ISubcategoryTransactionHandler subcategoryTransactionHandler;

    public void setSubcategoryInformationHandler(ISubcategoryInformationHandler subcategoryInformationHandler) {
        this.subcategoryInformationHandler = subcategoryInformationHandler;
    }

    public void setSubcategoryRepresentationHandler(ISubcategoryRepresentationHandler subcategoryRepresentationHandler) {
        this.subcategoryRepresentationHandler = subcategoryRepresentationHandler;
    }

    public void setSubcategoryTransactionHandler(ISubcategoryTransactionHandler subcategoryTransactionHandler) {
        this.subcategoryTransactionHandler = subcategoryTransactionHandler;
    }

    @Override
    public void subcategoryResponse(HashMap<String, Object> response) {
        if(response.containsKey("error")){
            mMessageRepresentationHandler.hideLoading();
            mMessageRepresentationHandler.showCode(IMessageRepresentationHandler.NOTIFICATION_CODE.K_INVALID_COMMON_FIELDS);
        }else{
            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("subcategory_response", response);
            mMementoHandler.setStateForOwner(data, this);
            List<Object> categories = new ArrayList<Object>();
            for(String key : response.keySet())
                categories.add(response.get(key));
            subcategoryRepresentationHandler.showSubcategoryViewWithData(categories);
            mMessageRepresentationHandler.hideLoading();
        }
    }

    @Override
    public void userSelectedCategory(HashMap<String, Object> subcategoryInformation) {

    }

    @Override
    public void getSubcategories() {
        mMessageRepresentationHandler.showLoading();
        subcategoryInformationHandler.performSubcategoryRequest();
    }
}
