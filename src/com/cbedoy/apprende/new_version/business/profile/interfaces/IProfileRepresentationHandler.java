package com.cbedoy.apprende.new_version.business.profile.interfaces;

import java.util.HashMap;

/**
 * Created by Carlos on 15/10/2014.
 */
public interface IProfileRepresentationHandler
{
    public void showPreviewViewWithData(HashMap<String, Object> userInformation);
    public void showAvatarView(String avatarBase64);
    public void animateBlurBackground();
}
