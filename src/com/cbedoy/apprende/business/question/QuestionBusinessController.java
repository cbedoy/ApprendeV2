package com.cbedoy.apprende.business.question;

import com.cbedoy.apprende.business.BusinessController;
import com.cbedoy.apprende.business.question.interfaces.IQuestionInformationDelegate;
import com.cbedoy.apprende.business.question.interfaces.IQuestionInformationHandler;
import com.cbedoy.apprende.business.question.interfaces.IQuestionRepresentationDelegate;
import com.cbedoy.apprende.business.question.interfaces.IQuestionRepresentationHandler;
import com.cbedoy.apprende.business.question.interfaces.IQuestionTransactionDelegate;
import com.cbedoy.apprende.business.question.interfaces.IQuestionTransactionHandler;
import com.cbedoy.apprende.interfaces.IMessageRepresentationHandler;

import java.util.HashMap;

/**
 * Created by Carlos on 15/10/2014.
 */
public class QuestionBusinessController extends BusinessController implements IQuestionTransactionDelegate, IQuestionRepresentationDelegate, IQuestionInformationDelegate{

    private IQuestionTransactionHandler transactionHandler;
    private IQuestionInformationHandler informationHandler;
    private IQuestionRepresentationHandler representationHandler;

    public void setInformationHandler(IQuestionInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setRepresentationHandler(IQuestionRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setTransactionHandler(IQuestionTransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @Override
    public void questionaryResponse(HashMap<String, Object> response) {
        mMessageRepresentationHandler.hideLoading();
        boolean status = response.get("status").toString().equals("1");
        if(status)
        {
            representationHandler.showFeedback();
        }
        else
        {
            mMessageRepresentationHandler.showCode(IMessageRepresentationHandler.NOTIFICATION_CODE.K_INVALID_COMMON_FIELDS);
        }
    }

    @Override
    public void userFinishExam() {
        informationHandler.sendQuestionaryInformation();
    }

    @Override
    public void userRequieredProfileView() {
        informationHandler.performQuestionaryRequest();
    }

    @Override
    public void startQuestionaryApprende()
    {
        this.mMessageRepresentationHandler.hideLoading();
        this.representationHandler.showQuestionary();
    }
}

