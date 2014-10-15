package com.cbedoy.apprende.new_version.business.login;

import com.cbedoy.apprende.new_version.business.BusinessController;
import com.cbedoy.apprende.new_version.business.login.interfaces.ILoginInformationDelegate;
import com.cbedoy.apprende.new_version.business.login.interfaces.ILoginInformationHandler;
import com.cbedoy.apprende.new_version.business.login.interfaces.ILoginRepresentationDelegate;
import com.cbedoy.apprende.new_version.business.login.interfaces.ILoginRepresentationHandler;
import com.cbedoy.apprende.new_version.business.login.interfaces.ILoginTransactionDelegate;
import com.cbedoy.apprende.new_version.business.login.interfaces.ILoginTransactionHandler;

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
}
