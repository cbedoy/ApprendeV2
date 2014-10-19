package com.cbedoy.apprende.business;

import com.cbedoy.apprende.business.category.interfaces.ICategoryTransactionDelegate;
import com.cbedoy.apprende.business.category.interfaces.ICategoryTransactionHandler;
import com.cbedoy.apprende.business.login.interfaces.ILoginTransactionDelegate;
import com.cbedoy.apprende.business.login.interfaces.ILoginTransactionHandler;
import com.cbedoy.apprende.business.preview.interfaces.IPreviewTransactionDelegate;
import com.cbedoy.apprende.business.preview.interfaces.IPreviewTransactionHandler;
import com.cbedoy.apprende.business.profile.interfaces.IProfileTransactionDelegate;
import com.cbedoy.apprende.business.profile.interfaces.IProfileTransactionHandler;
import com.cbedoy.apprende.business.question.interfaces.IQuestionTransactionDelegate;
import com.cbedoy.apprende.business.question.interfaces.IQuestionTransactionHandler;
import com.cbedoy.apprende.business.singup.interfaces.ISingupTransactionDelegate;
import com.cbedoy.apprende.business.singup.interfaces.ISingupTransactionHandler;
import com.cbedoy.apprende.business.subcategory.interfaces.ISubcategoryTransactionDelegate;
import com.cbedoy.apprende.business.subcategory.interfaces.ISubcategoryTransactionHandler;

/**
 * Created by Carlos on 15/10/2014.
 */
public class MasterBusinessController extends BusinessController implements ICategoryTransactionHandler, IProfileTransactionHandler, ILoginTransactionHandler, ISubcategoryTransactionHandler, IPreviewTransactionHandler, ISingupTransactionHandler, IQuestionTransactionHandler
{
    private ILoginTransactionDelegate loginTransactionDelegate;
    private ISingupTransactionDelegate singupTransactionDelegate;
    private IProfileTransactionDelegate profileTransactionDelegate;
    private ICategoryTransactionDelegate categoryTransactionDelegate;
    private IPreviewTransactionDelegate previewTransactionDelegate;
    private ISubcategoryTransactionDelegate subcategoryTransactionDelegate;
    private IQuestionTransactionDelegate questionTransactionDelegate;

    public void setQuestionTransactionDelegate(IQuestionTransactionDelegate questionTransactionDelegate) {
        this.questionTransactionDelegate = questionTransactionDelegate;
    }

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
        this.loginTransactionDelegate.startLogin();
    }

    @Override
    public void presentCategoryView() {

    }

    @Override
    public void userAuthenticated()
    {
        profileTransactionDelegate.getProfileByUser();
    }

    @Override
    public void presentPreview() {

    }

    @Override
    public void presentProfile() {
        categoryTransactionDelegate.getCategories();
    }

    @Override
    public void userRegistred() {

    }

    @Override
    public void presentSubcategories() {
    }

    @Override
    public void questionaryPresented() {

    }
}
