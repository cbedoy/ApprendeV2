package com.cbedoy.apprende.new_version.business.category;

import com.cbedoy.apprende.new_version.business.BusinessController;
import com.cbedoy.apprende.new_version.business.category.interfaces.ICategoryInformationDelegate;
import com.cbedoy.apprende.new_version.business.category.interfaces.ICategoryInformationHandler;
import com.cbedoy.apprende.new_version.business.category.interfaces.ICategoryRepresentationDelegate;
import com.cbedoy.apprende.new_version.business.category.interfaces.ICategoryRepresentationHandler;
import com.cbedoy.apprende.new_version.business.category.interfaces.ICategoryTransactionDelegate;
import com.cbedoy.apprende.new_version.business.category.interfaces.ICategoryTransactionHandler;

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
}
