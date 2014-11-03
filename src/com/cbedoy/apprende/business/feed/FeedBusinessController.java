package com.cbedoy.apprende.business.feed;

import com.cbedoy.apprende.business.BusinessController;
import com.cbedoy.apprende.business.feed.interfaces.IFeedInformationDelegate;
import com.cbedoy.apprende.business.feed.interfaces.IFeedInformationHandler;
import com.cbedoy.apprende.business.feed.interfaces.IFeedRepresentationDelegate;
import com.cbedoy.apprende.business.feed.interfaces.IFeedRepresentationHandler;
import com.cbedoy.apprende.business.feed.interfaces.IFeedTransactionDelegate;
import com.cbedoy.apprende.business.feed.interfaces.IFeedTransactionHandler;
import com.cbedoy.apprende.interfaces.IMessageRepresentationHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Carlos on 15/10/2014.
 */
public class FeedBusinessController extends BusinessController implements IFeedTransactionDelegate, IFeedInformationDelegate, IFeedRepresentationDelegate
{
    private IFeedRepresentationHandler representationHandler;
    private IFeedTransactionHandler transactionHandler;
    private IFeedInformationHandler informationHandler;

    public void setTransactionHandler(IFeedTransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    public void setRepresentationHandler(IFeedRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setInformationHandler(IFeedInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    @Override
    public void feedbackResponse(HashMap<String, Object> response) {

    }

    @Override
    public void userSelectedFeedback(HashMap<String, Object> categoryData) {

    }

    @Override
    public void getFeedbacks() {

    }
}
