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
    private ILoginInformationHandler loginInformationHandler;
    private ILoginRepresentationHandler loginRepresentationHandler;
    private ILoginTransactionHandler loginTransactionHandler;

    public void setLoginInformationHandler(ILoginInformationHandler loginInformationHandler) {
        this.loginInformationHandler = loginInformationHandler;
    }

    public void setLoginRepresentationHandler(ILoginRepresentationHandler loginRepresentationHandler) {
        this.loginRepresentationHandler = loginRepresentationHandler;
    }

    public void setLoginTransactionHandler(ILoginTransactionHandler loginTransactionHandler) {
        this.loginTransactionHandler = loginTransactionHandler;
    }

    @Override
    public void loginResponse(HashMap<String, Object> response) {

    }

    @Override
    public void loginWithData(String username, String password) {

        if(!isValidField(username) || isValidField(password))
        {
            loginRepresentationHandler.cleanLoginViewFields();
            mMessageRepresentationHandler.showCode(IMessageRepresentationHandler.NOTIFICATION_CODE.K_INVALID_LOGIN);
        }
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("username", username);
        data.put("password", password);
        mMementoHandler.setStateForOwner(data, this);

        mMessageRepresentationHandler.showLoading();
        loginInformationHandler.performLoginRequest();
    }

    @Override
    public void startLogin() {
        Memento topMemento = mMementoHandler.getTopMemento();
        HashMap<String, Object> mementoData = topMemento.getMementoData();
        boolean withUsername = mementoData.containsKey("username");
        boolean withPassword = mementoData.containsKey("password");
        if(withUsername && withPassword){
            loginWithData(mementoData.get("username").toString(), mementoData.get("password").toString());
        }else{
            loginRepresentationHandler.showLoginView();
        }
    }

    private boolean isValidField(String field){
        return  field.length() > 6 ? true : false;
    }


}
