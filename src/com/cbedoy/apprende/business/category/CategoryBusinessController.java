package com.cbedoy.apprende.business.category;

import com.cbedoy.apprende.business.BusinessController;
import com.cbedoy.apprende.business.category.interfaces.ICategoryInformationDelegate;
import com.cbedoy.apprende.business.category.interfaces.ICategoryInformationHandler;
import com.cbedoy.apprende.business.category.interfaces.ICategoryRepresentationDelegate;
import com.cbedoy.apprende.business.category.interfaces.ICategoryRepresentationHandler;
import com.cbedoy.apprende.business.category.interfaces.ICategoryTransactionDelegate;
import com.cbedoy.apprende.business.category.interfaces.ICategoryTransactionHandler;

import java.util.HashMap;

/**
 * Created by Carlos on 15/10/2014.
 */
public class CategoryBusinessController extends BusinessController implements ICategoryTransactionDelegate, ICategoryInformationDelegate, ICategoryRepresentationDelegate
{
    private ICategoryInformationHandler categoryInformationHandler;
    private ICategoryRepresentationHandler categoryRepresentationHandler;
    private ICategoryTransactionHandler categoryTransactionHandler;

    public void setCategoryInformationHandler(ICategoryInformationHandler categoryInformationHandler) {
        this.categoryInformationHandler = categoryInformationHandler;
    }

    public void setCategoryRepresentationHandler(ICategoryRepresentationHandler categoryRepresentationHandler) {
        this.categoryRepresentationHandler = categoryRepresentationHandler;
    }

    public void setCategoryTransactionHandler(ICategoryTransactionHandler categoryTransactionHandler) {
        this.categoryTransactionHandler = categoryTransactionHandler;
    }

    @Override
    public void categoryResponse(HashMap<String, Object> response) {

    }

    @Override
    public void userSelectedCategory(HashMap<String, Object> categoryData) {

    }

    @Override
    public void getCategories() {

    }
}
