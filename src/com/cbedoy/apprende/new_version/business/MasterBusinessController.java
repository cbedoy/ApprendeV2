package com.cbedoy.apprende.new_version.business;

import com.cbedoy.apprende.new_version.business.category.interfaces.ICategoryTransactionDelegate;
import com.cbedoy.apprende.new_version.business.category.interfaces.ICategoryTransactionHandler;
import com.cbedoy.apprende.new_version.business.login.interfaces.ILoginTransactionDelegate;
import com.cbedoy.apprende.new_version.business.login.interfaces.ILoginTransactionHandler;
import com.cbedoy.apprende.new_version.business.preview.interfaces.IPreviewTransactionDelegate;
import com.cbedoy.apprende.new_version.business.preview.interfaces.IPreviewTransactionHandler;
import com.cbedoy.apprende.new_version.business.profile.interfaces.IProfileTransactionDelegate;
import com.cbedoy.apprende.new_version.business.profile.interfaces.IProfileTransactionHandler;
import com.cbedoy.apprende.new_version.business.singup.interfaces.ISingupTransactionDelegate;
import com.cbedoy.apprende.new_version.business.singup.interfaces.ISingupTransactionHandler;
import com.cbedoy.apprende.new_version.business.subcategory.interfaces.ISubcategoryTransactionDelegate;
import com.cbedoy.apprende.new_version.business.subcategory.interfaces.ISubcategoryTransactionHandler;

/**
 * Created by Carlos on 15/10/2014.
 */
public class MasterBusinessController extends BusinessController implements ICategoryTransactionHandler, IProfileTransactionHandler, ILoginTransactionHandler, ISubcategoryTransactionHandler, IPreviewTransactionHandler, ISingupTransactionHandler
{
    private ILoginTransactionDelegate loginTransactionDelegate;
    private ISingupTransactionDelegate singupTransactionDelegate;
    private IProfileTransactionDelegate profileTransactionDelegate;
    private ICategoryTransactionDelegate categoryTransactionDelegate;
    private IPreviewTransactionDelegate previewTransactionDelegate;
    private ISubcategoryTransactionDelegate subcategoryTransactionDelegate;

    public void setCategoryTransactionDelegate(ICategoryTransactionDelegate categoryTransactionDelegate) {
        this.categoryTransactionDelegate = categoryTransactionDelegate;
    }

    public void setLoginTransactionDelegate(ILoginTransactionDelegate loginTransactionDelegate) {
        this.loginTransactionDelegate = loginTransactionDelegate;
    }

    public void setPreviewTransactionDelegate(IPreviewTransactionDelegate previewTransactionDelegate) {
        this.previewTransactionDelegate = previewTransactionDelegate;
    }

    public void setProfileTransactionDelegate(IProfileTransactionDelegate profileTransactionDelegate) {
        this.profileTransactionDelegate = profileTransactionDelegate;
    }

    public void setSingupTransactionDelegate(ISingupTransactionDelegate singupTransactionDelegate) {
        this.singupTransactionDelegate = singupTransactionDelegate;
    }

    public void setSubcategoryTransactionDelegate(ISubcategoryTransactionDelegate subcategoryTransactionDelegate) {
        this.subcategoryTransactionDelegate = subcategoryTransactionDelegate;
    }

    public void startApprendeApp(){

    }

    @Override
    public void presentCategoryView() {

    }

    @Override
    public void userAuthenticated() {

    }

    @Override
    public void presentPreview() {

    }

    @Override
    public void presentProfile() {

    }

    @Override
    public void userRegistred() {

    }

    @Override
    public void presentSubcategories() {

    }
}
