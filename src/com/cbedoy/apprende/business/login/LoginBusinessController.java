package com.cbedoy.apprende.business.login;

import com.cbedoy.apprende.business.BusinessController;
import com.cbedoy.apprende.business.login.interfaces.ILoginInformationDelegate;
import com.cbedoy.apprende.business.login.interfaces.ILoginInformationHandler;
import com.cbedoy.apprende.business.login.interfaces.ILoginRepresentationDelegate;
import com.cbedoy.apprende.business.login.interfaces.ILoginRepresentationHandler;
import com.cbedoy.apprende.business.login.interfaces.ILoginTransactionDelegate;
import com.cbedoy.apprende.business.login.interfaces.ILoginTransactionHandler;
import com.cbedoy.apprende.interfaces.IMessageRepresentationHandler;
import com.cbedoy.apprende.service.Memento;

import java.util.HashMap;

/**
 * Created by Carlos on 15/10/2014.
 */
public class LoginBusinessController extends BusinessController implements ILoginTransactionDelegate, ILoginRepresentationDelegate, ILoginInformationDelegate
{
    private ILoginInformationHandler informationHandler;
    private ILoginRepresentationHandler representationHandler;
    private ILoginTransactionHandler transactionHandler;

    public void setInformationHandler(ILoginInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setRepresentationHandler(ILoginRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setTransactionHandler(ILoginTransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @Override
    public void loginResponse(HashMap<String, Object> response) {
        boolean status = response.containsKey("model") && response.containsKey("pk");
        messageRepresentationHandler.hideLoading();
        if (status) {
            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("login_response", response);
            mementoHandler.setStateForOwner(data, this);
            transactionHandler.userAuthenticated();
        } else {
            representationHandler.cleanLoginViewFields();
            messageRepresentationHandler.showCode(IMessageRepresentationHandler.NOTIFICATION_CODE.K_INVALID_LOGIN);
        }
    }

    @Override
    public void loginWithData(String username, String password) {

        if(!isValidField(username) || !isValidField(password))
        {
            representationHandler.cleanLoginViewFields();
            messageRepresentationHandler.showCode(IMessageRepresentationHandler.NOTIFICATION_CODE.K_INVALID_LOGIN);
        }
        else
        {
            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("username", username);
            data.put("password", password);
            mementoHandler.setStateForOwner(data, this);
            messageRepresentationHandler.showLoading();
            informationHandler.performLoginRequest();
        }
    }

    @Override
    public void userNeedSingup() {
        transactionHandler.userWantsSingup();
    }

    @Override
    public void startLogin() {
        Memento topMemento = mementoHandler.getTopMemento();
        HashMap<String, Object> mementoData = topMemento.getMementoData();
        boolean withUsername = mementoData.containsKey("username");
        boolean withPassword = mementoData.containsKey("password");
        if(withUsername && withPassword){
            loginWithData(mementoData.get("username").toString(), mementoData.get("password").toString());
        }else{
            representationHandler.showLoginView();
        }
    }

    private boolean isValidField(String field){
        return  field.length() > 6 ? true : false;
    }


    @Override
    public void backRequested() {
        mementoHandler.popDataFor(this);
    }
}
