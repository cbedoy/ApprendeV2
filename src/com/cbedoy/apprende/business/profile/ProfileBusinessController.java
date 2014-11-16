package com.cbedoy.apprende.business.profile;

import com.cbedoy.apprende.business.BusinessController;
import com.cbedoy.apprende.business.profile.interfaces.IProfileInformationDelegate;
import com.cbedoy.apprende.business.profile.interfaces.IProfileInformationHandler;
import com.cbedoy.apprende.business.profile.interfaces.IProfileRepresentationDelegate;
import com.cbedoy.apprende.business.profile.interfaces.IProfileRepresentationHandler;
import com.cbedoy.apprende.business.profile.interfaces.IProfileTransactionDelegate;
import com.cbedoy.apprende.business.profile.interfaces.IProfileTransactionHandler;
import com.cbedoy.apprende.service.Memento;

import java.util.HashMap;

/**
 * Created by Carlos on 15/10/2014.
 */
public class ProfileBusinessController extends BusinessController implements IProfileTransactionDelegate, IProfileRepresentationDelegate, IProfileInformationDelegate
{
    private IProfileRepresentationHandler representationHandler;
    private IProfileInformationHandler informationHandler;
    private IProfileTransactionHandler transactionHandler;

    public void setInformationHandler(IProfileInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setRepresentationHandler(IProfileRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setTransactionHandler(IProfileTransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @Override
    public void profileResponse(HashMap<String, Object> response) {

    }

    @Override
    public void userSelectedStartToLearn() {
        transactionHandler.presentProfile();
    }

    @Override
    public void getProfileByUser()
    {
        Memento topMemento = mementoHandler.getTopMemento();
        HashMap<String, Object> mementoData = topMemento.getMementoData();
        HashMap<String, Object> login_response = (HashMap<String, Object>) mementoData.get("login_response");
        HashMap<String, Object> fields = (HashMap<String, Object>) login_response.get("fields");
        this.representationHandler.showProfileViewWithData(fields);
    }

    @Override
    public void backRequested() {
        mementoHandler.popDataFor(this);
    }
}
