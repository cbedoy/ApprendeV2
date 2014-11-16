package com.cbedoy.apprende.business.preview;

import com.cbedoy.apprende.business.BusinessController;
import com.cbedoy.apprende.business.preview.interfaces.IPreviewInformationDelegate;
import com.cbedoy.apprende.business.preview.interfaces.IPreviewInformationHandler;
import com.cbedoy.apprende.business.preview.interfaces.IPreviewRepresentationDelegate;
import com.cbedoy.apprende.business.preview.interfaces.IPreviewRepresentationHandler;
import com.cbedoy.apprende.business.preview.interfaces.IPreviewTransactionDelegate;
import com.cbedoy.apprende.business.preview.interfaces.IPreviewTransactionHandler;
import com.cbedoy.apprende.interfaces.IMessageRepresentationHandler;
import com.cbedoy.apprende.service.Memento;

import java.util.HashMap;

/**
 * Created by Carlos on 15/10/2014.
 */
public class PreviewBusinessController extends BusinessController implements IPreviewTransactionDelegate, IPreviewRepresentationDelegate, IPreviewInformationDelegate
{
    private IPreviewInformationHandler informationHandler;
    private IPreviewRepresentationHandler representationHandler;
    private IPreviewTransactionHandler transactionHandler;

    public void setInformationHandler(IPreviewInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setRepresentationHandler(IPreviewRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setTransactionHandler(IPreviewTransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @Override
    public void previewResponse(HashMap<String, Object> response) {
        boolean status = response.size()>5;
        messageRepresentationHandler.hideLoading();
        if (status) {
            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("exam_response", response);
            mementoHandler.setStateForOwner(data, this);
            transactionHandler.apprendeItsReady();
        } else {
            messageRepresentationHandler.showCode(IMessageRepresentationHandler.NOTIFICATION_CODE.K_INVALID_LOGIN);
        }
    }

    @Override
    public void getPreview() {
        Memento topMemento = mementoHandler.getTopMemento();
        HashMap<String, Object> mementoData = topMemento.getMementoData();
        HashMap<String, Object> previewInformation = new HashMap<String, Object>();
        previewInformation.put ("category_selected", mementoData.get("category_selected"));
        previewInformation.put ("subcategory_selected", mementoData.get("subcategory_selected"));
        representationHandler.showPreviewWithData(previewInformation);
    }

    @Override
    public void userSelectedStartApprendeWithData(HashMap<String, Object> information) {
        informationHandler.performPreviewRequestWithData(information);
        messageRepresentationHandler.showLoading();
    }

    @Override
    public void backRequested() {
        mementoHandler.popDataFor(this);
    }
}
