package com.cbedoy.apprende.business;

import com.cbedoy.apprende.business.category.interfaces.ICategoryTransactionDelegate;
import com.cbedoy.apprende.business.category.interfaces.ICategoryTransactionHandler;
import com.cbedoy.apprende.business.feed.interfaces.IFeedTransactionDelegate;
import com.cbedoy.apprende.business.feed.interfaces.IFeedTransactionHandler;
import com.cbedoy.apprende.business.guy.interfaces.IGuyTransactionDelegate;
import com.cbedoy.apprende.business.guy.interfaces.IGuyTransactionHandler;
import com.cbedoy.apprende.business.login.interfaces.ILoginTransactionDelegate;
import com.cbedoy.apprende.business.login.interfaces.ILoginTransactionHandler;
import com.cbedoy.apprende.business.preview.interfaces.IPreviewTransactionDelegate;
import com.cbedoy.apprende.business.preview.interfaces.IPreviewTransactionHandler;
import com.cbedoy.apprende.business.profile.interfaces.IProfileTransactionDelegate;
import com.cbedoy.apprende.business.profile.interfaces.IProfileTransactionHandler;
import com.cbedoy.apprende.business.question.interfaces.IQuestionTransactionDelegate;
import com.cbedoy.apprende.business.question.interfaces.IQuestionTransactionHandler;
import com.cbedoy.apprende.business.rank.interfaces.IRankTransactionDelegate;
import com.cbedoy.apprende.business.rank.interfaces.IRankTransactionHandler;
import com.cbedoy.apprende.business.signup.interfaces.ISignUpTransactionDelegate;
import com.cbedoy.apprende.business.signup.interfaces.ISignUpTransactionHandler;
import com.cbedoy.apprende.business.subcategory.interfaces.ISubcategoryTransactionDelegate;
import com.cbedoy.apprende.business.subcategory.interfaces.ISubcategoryTransactionHandler;
import com.cbedoy.apprende.business.timeout.interfaces.ITimeOutTransactionDelegate;
import com.cbedoy.apprende.business.timeout.interfaces.ITimeOutTransactionHandler;

/**
 * Created by Carlos on 15/10/2014.
 */
public class MasterBusinessController extends BusinessController implements IGuyTransactionHandler, IFeedTransactionHandler, ITimeOutTransactionHandler, ICategoryTransactionHandler, IProfileTransactionHandler, ILoginTransactionHandler, ISubcategoryTransactionHandler, IPreviewTransactionHandler, ISignUpTransactionHandler, IQuestionTransactionHandler, IRankTransactionHandler
{
    private ILoginTransactionDelegate loginTransactionDelegate;
    private ISignUpTransactionDelegate singupTransactionDelegate;
    private IProfileTransactionDelegate profileTransactionDelegate;
    private ICategoryTransactionDelegate categoryTransactionDelegate;
    private IPreviewTransactionDelegate previewTransactionDelegate;
    private ISubcategoryTransactionDelegate subcategoryTransactionDelegate;
    private IQuestionTransactionDelegate questionTransactionDelegate;
    private ITimeOutTransactionDelegate timeOutTransactionDelegate;
    private IFeedTransactionDelegate feedTransactionDelegate;
    private IRankTransactionDelegate rankTransactionDelegate;
    private IGuyTransactionDelegate guyTransactionDelegate;

    public void setGuyTransactionDelegate(IGuyTransactionDelegate guyTransactionDelegate) {
        this.guyTransactionDelegate = guyTransactionDelegate;
    }

    public void setFeedTransactionDelegate(IFeedTransactionDelegate feedTransactionDelegate) {
        this.feedTransactionDelegate = feedTransactionDelegate;
    }

    public void setTimeOutTransactionDelegate(ITimeOutTransactionDelegate timeOutTransactionDelegate) {
        this.timeOutTransactionDelegate = timeOutTransactionDelegate;
    }

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

    public void setSingupTransactionDelegate(ISignUpTransactionDelegate singupTransactionDelegate) {
        this.singupTransactionDelegate = singupTransactionDelegate;
    }

    public void setSubcategoryTransactionDelegate(ISubcategoryTransactionDelegate subcategoryTransactionDelegate) {
        this.subcategoryTransactionDelegate = subcategoryTransactionDelegate;
    }

    public void setRankTransactionDelegate(IRankTransactionDelegate rankTransactionDelegate) {
        this.rankTransactionDelegate = rankTransactionDelegate;
    }

    public void startApprendeApp(){
        loginTransactionDelegate.startLogin();
    }

    @Override
    public void presentCategoryView() {
        subcategoryTransactionDelegate.getSubcategories();
    }

    @Override
    public void userAuthenticated()
    {
        profileTransactionDelegate.getProfileByUser();
    }

    @Override
    public void userWantsSingup() {
        singupTransactionDelegate.startSingup();
    }

    @Override
    public void apprendeItsReady()
    {
        questionTransactionDelegate.startQuestionaryApprende();
    }

    @Override
    public void presentProfile() {
        categoryTransactionDelegate.getCategories();
    }

    @Override
    public void userRegistred()
    {
    }

    @Override
    public void presentSubcategories()
    {
        previewTransactionDelegate.getPreview();
    }

    @Override
    public void questionaryPresented() {
        questionTransactionDelegate.startQuestionaryApprende();
    }

    @Override
    public void timerFinished() {

    }

    @Override
    public void presentFeedbacks() {

    }

    @Override
    public void needRankData() {

    }

    @Override
    public void showGuys() {

    }
}
