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
    private ISubcategoryInformationHandler informationHandler;
    private ISubcategoryRepresentationHandler representationHandler;
    private ISubcategoryTransactionHandler transactionHandler;

    public void setInformationHandler(ISubcategoryInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setRepresentationHandler(ISubcategoryRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setTransactionHandler(ISubcategoryTransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
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
            representationHandler.showSubcategoryViewWithData(categories);
            mMessageRepresentationHandler.hideLoading();
        }
    }

    @Override
    public void userSelectedCategory(HashMap<String, Object> subcategoryInformation) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("subcategory_selected", subcategoryInformation);
        mMementoHandler.setStateForOwner(data, this);
        transactionHandler.presentSubcategories();
    }

    @Override
    public void getSubcategories() {
        mMessageRepresentationHandler.showLoading();
        informationHandler.performSubcategoryRequest();
    }
}
