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
    private IPreviewInformationHandler previewInformationHandler;
    private IPreviewRepresentationHandler previewRepresentationHandler;
    private IPreviewTransactionHandler previewTransactionHandler;

    public void setPreviewInformationHandler(IPreviewInformationHandler previewInformationHandler) {
        this.previewInformationHandler = previewInformationHandler;
    }

    public void setPreviewRepresentationHandler(IPreviewRepresentationHandler previewRepresentationHandler) {
        this.previewRepresentationHandler = previewRepresentationHandler;
    }

    public void setPreviewTransactionHandler(IPreviewTransactionHandler previewTransactionHandler) {
        this.previewTransactionHandler = previewTransactionHandler;
    }

    @Override
    public void previewResponse(HashMap<String, Object> response) {
        boolean status = response.containsKey("model") && response.containsKey("pk");
        mMessageRepresentationHandler.hideLoading();
        if (status) {
            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("exam_response", response);
            mMementoHandler.setStateForOwner(data, this);
            previewTransactionHandler.apprendeItsReady();
        } else {
            mMessageRepresentationHandler.showCode(IMessageRepresentationHandler.NOTIFICATION_CODE.K_INVALID_LOGIN);
        }
    }

    @Override
    public void getPreview() {
        Memento topMemento = mMementoHandler.getTopMemento();
        HashMap<String, Object> mementoData = topMemento.getMementoData();
        HashMap<String, Object> previewInformation = new HashMap<String, Object>();
        previewInformation.put ("category_selected", mementoData.get("category_selected"));
        previewInformation.put ("subcategory_selected", mementoData.get("subcategory_selected"));
        previewRepresentationHandler.showPreviewWithData(previewInformation);
    }

    @Override
    public void userSelectedStartApprendeWithData(HashMap<String, Object> information) {
        previewInformationHandler.performPreviewRequestWithData(information);
    }
}
