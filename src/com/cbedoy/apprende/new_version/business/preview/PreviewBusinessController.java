package com.cbedoy.apprende.new_version.business.preview;

import com.cbedoy.apprende.new_version.business.BusinessController;
import com.cbedoy.apprende.new_version.business.preview.interfaces.IPreviewInformationDelegate;
import com.cbedoy.apprende.new_version.business.preview.interfaces.IPreviewInformationHandler;
import com.cbedoy.apprende.new_version.business.preview.interfaces.IPreviewRepresentationDelegate;
import com.cbedoy.apprende.new_version.business.preview.interfaces.IPreviewRepresentationHandler;
import com.cbedoy.apprende.new_version.business.preview.interfaces.IPreviewTransactionDelegate;
import com.cbedoy.apprende.new_version.business.preview.interfaces.IPreviewTransactionHandler;
import com.cbedoy.apprende.new_version.business.singup.interfaces.ISingupTransactionHandler;

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

    }

    @Override
    public void userSelectedApprende() {

    }
}
