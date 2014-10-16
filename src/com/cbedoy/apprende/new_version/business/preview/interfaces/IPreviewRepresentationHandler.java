package com.cbedoy.apprende.new_version.business.preview.interfaces;

import java.util.HashMap;

/**
 * Created by Carlos on 15/10/2014.
 */
public interface IPreviewRepresentationHandler
{
    public void showPreviewWithData(HashMap<String, Object> previewInformation);
    public void showBackground(String base64image);
}
