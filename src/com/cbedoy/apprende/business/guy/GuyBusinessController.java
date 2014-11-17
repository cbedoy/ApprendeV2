package com.cbedoy.apprende.business.guy;

import com.cbedoy.apprende.business.BusinessController;
import com.cbedoy.apprende.business.guy.interfaces.IGuyInformationDelegate;
import com.cbedoy.apprende.business.guy.interfaces.IGuyInformationHandler;
import com.cbedoy.apprende.business.guy.interfaces.IGuyRepresentationDelegate;
import com.cbedoy.apprende.business.guy.interfaces.IGuyRepresentationHandler;
import com.cbedoy.apprende.business.guy.interfaces.IGuyTransactionDelegate;
import com.cbedoy.apprende.business.guy.interfaces.IGuyTransactionHandler;

import java.util.HashMap;

/**
 * Created by Carlos on 17/11/2014.
 * <p/>
 * Mobile App Developer
 * <p/>
 * Apprende
 */
public class GuyBusinessController extends BusinessController implements IGuyInformationDelegate, IGuyRepresentationDelegate, IGuyTransactionDelegate {

    private IGuyRepresentationHandler representationHandler;
    private IGuyInformationHandler informationHandler;
    private IGuyTransactionHandler transactionHandler;

    public void setRepresentationHandler(IGuyRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setInformationHandler(IGuyInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setTransactionHandler(IGuyTransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @Override
    public void guyResponse(HashMap<String, Object> guyResponse) {

    }

    @Override
    public void messageResponse(HashMap<String, Object> messageResponse) {

    }

    @Override
    public void didUserSelectedGuy() {

    }

    @Override
    public void sendMessageToGuy() {

    }

    @Override
    public void selectedGuyModule() {

    }
}
