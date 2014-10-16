package com.cbedoy.apprende.new_version.business.subcategory;

import com.cbedoy.apprende.new_version.business.BusinessController;
import com.cbedoy.apprende.new_version.business.subcategory.interfaces.ISubcategoryInformationDelegate;
import com.cbedoy.apprende.new_version.business.subcategory.interfaces.ISubcategoryInformationHandler;
import com.cbedoy.apprende.new_version.business.subcategory.interfaces.ISubcategoryRepresentationDelegate;
import com.cbedoy.apprende.new_version.business.subcategory.interfaces.ISubcategoryRepresentationHandler;
import com.cbedoy.apprende.new_version.business.subcategory.interfaces.ISubcategoryTransactionDelegate;
import com.cbedoy.apprende.new_version.business.subcategory.interfaces.ISubcategoryTransactionHandler;

import java.util.HashMap;

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

    }

    @Override
    public void userSelectedCategory(HashMap<String, Object> subcategoryInformation) {

    }
}
