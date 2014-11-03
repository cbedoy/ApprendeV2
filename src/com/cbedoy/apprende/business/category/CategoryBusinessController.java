package com.cbedoy.apprende.business.category;

import com.cbedoy.apprende.business.BusinessController;
import com.cbedoy.apprende.business.category.interfaces.ICategoryInformationDelegate;
import com.cbedoy.apprende.business.category.interfaces.ICategoryInformationHandler;
import com.cbedoy.apprende.business.category.interfaces.ICategoryRepresentationDelegate;
import com.cbedoy.apprende.business.category.interfaces.ICategoryRepresentationHandler;
import com.cbedoy.apprende.business.category.interfaces.ICategoryTransactionDelegate;
import com.cbedoy.apprende.business.category.interfaces.ICategoryTransactionHandler;
import com.cbedoy.apprende.interfaces.IMessageRepresentationHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Carlos on 15/10/2014.
 */
public class CategoryBusinessController extends BusinessController implements ICategoryTransactionDelegate, ICategoryInformationDelegate, ICategoryRepresentationDelegate
{
    private ICategoryInformationHandler informationHandler;
    private ICategoryRepresentationHandler representationHandler;
    private ICategoryTransactionHandler transactionHandler;

    public void setInformationHandler(ICategoryInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setRepresentationHandler(ICategoryRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setTransactionHandler(ICategoryTransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @Override
    public void categoryResponse(HashMap<String, Object> response) {
        if(response.containsKey("error")){
            mMessageRepresentationHandler.hideLoading();
            mMessageRepresentationHandler.showCode(IMessageRepresentationHandler.NOTIFICATION_CODE.K_INVALID_COMMON_FIELDS);
        }else{
            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("category_response", response);
            mMementoHandler.setStateForOwner(data, this);
            List<Object> categories = new ArrayList<Object>();
            for(String key : response.keySet())
                categories.add(response.get(key));
            representationHandler.showCategoryViewWithData(categories);
            mMessageRepresentationHandler.hideLoading();
        }
    }

    @Override
    public void userSelectedCategory(HashMap<String, Object> categoryData) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("category_selected", categoryData);
        mMementoHandler.setStateForOwner(data, this);
        transactionHandler.presentCategoryView();
    }

    @Override
    public void getCategories() {
        mMessageRepresentationHandler.showLoading();
        informationHandler.performCategoriesRequest();
    }
}
