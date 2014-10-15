package com.cbedoy.apprende.new_version.business;

import com.cbedoy.apprende.new_version.interfaces.IBackCore;
import com.cbedoy.apprende.new_version.interfaces.IMementoHandler;
import com.cbedoy.apprende.new_version.interfaces.IMessageRepresentationHandler;

/**
 * Created by Carlos on 15/10/2014.
 */
public abstract class BusinessController implements IBackCore
{
    protected IMementoHandler mMementoHandler;
    protected IMessageRepresentationHandler mMessageRepresentationHandler;

    public void setMementoHandler(IMementoHandler mementoHandler) {
        mMementoHandler = mementoHandler;
    }

    public void setMessageRepresentationHandler(IMessageRepresentationHandler messageRepresentationHandler) {
        mMessageRepresentationHandler = messageRepresentationHandler;
    }

    @Override
    public void backRequested() {

    }
}
