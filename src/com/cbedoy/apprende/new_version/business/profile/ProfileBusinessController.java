package com.cbedoy.apprende.new_version.business.profile;

import com.cbedoy.apprende.new_version.business.BusinessController;
import com.cbedoy.apprende.new_version.business.profile.interfaces.IProfileInformationDelegate;
import com.cbedoy.apprende.new_version.business.profile.interfaces.IProfileInformationHandler;
import com.cbedoy.apprende.new_version.business.profile.interfaces.IProfileRepresentationDelegate;
import com.cbedoy.apprende.new_version.business.profile.interfaces.IProfileRepresentationHandler;
import com.cbedoy.apprende.new_version.business.profile.interfaces.IProfileTransactionDelegate;

/**
 * Created by Carlos on 15/10/2014.
 */
public class ProfileBusinessController extends BusinessController implements IProfileTransactionDelegate, IProfileRepresentationDelegate, IProfileInformationDelegate
{
    private IProfileRepresentationHandler profileRepresentationHandler;
    private IProfileInformationHandler profileInformationHandler;
    private IProfileTransactionDelegate profileTransactionDelegate;

    public void setProfileInformationHandler(IProfileInformationHandler profileInformationHandler) {
        this.profileInformationHandler = profileInformationHandler;
    }

    public void setProfileRepresentationHandler(IProfileRepresentationHandler profileRepresentationHandler) {
        this.profileRepresentationHandler = profileRepresentationHandler;
    }

    public void setProfileTransactionDelegate(IProfileTransactionDelegate profileTransactionDelegate) {
        this.profileTransactionDelegate = profileTransactionDelegate;
    }
}
