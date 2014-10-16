package com.cbedoy.apprende.new_version.business.singup;

import com.cbedoy.apprende.new_version.business.BusinessController;
import com.cbedoy.apprende.new_version.business.singup.interfaces.ISingupInformationDelegate;
import com.cbedoy.apprende.new_version.business.singup.interfaces.ISingupInformationHandler;
import com.cbedoy.apprende.new_version.business.singup.interfaces.ISingupRepresentationDelegate;
import com.cbedoy.apprende.new_version.business.singup.interfaces.ISingupRepresentationHandler;
import com.cbedoy.apprende.new_version.business.singup.interfaces.ISingupTransactionDelegate;
import com.cbedoy.apprende.new_version.business.singup.interfaces.ISingupTransactionHandler;

import java.util.HashMap;

/**
 * Created by Carlos on 15/10/2014.
 */
public class SingupBusinessController extends BusinessController implements ISingupTransactionDelegate, ISingupRepresentationDelegate, ISingupInformationDelegate
{
    private ISingupInformationHandler singupInformationHandler;
    private ISingupRepresentationHandler singupRepresentationHandler;
    private ISingupTransactionHandler singupTransactionHandler;

    public void setSingupInformationHandler(ISingupInformationHandler singupInformationHandler) {
        this.singupInformationHandler = singupInformationHandler;
    }

    public void setSingupRepresentationHandler(ISingupRepresentationHandler singupRepresentationHandler) {
        this.singupRepresentationHandler = singupRepresentationHandler;
    }

    public void setSingupTransactionHandler(ISingupTransactionHandler singupTransactionHandler) {
        this.singupTransactionHandler = singupTransactionHandler;
    }

    @Override
    public void singupResponse(HashMap<String, Object> response) {

    }

    @Override
    public void userSelectedSingup() {

    }

    @Override
    public void startSingup() {

    }
}
