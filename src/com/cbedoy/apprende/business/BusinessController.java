package com.cbedoy.apprende.business;

import com.cbedoy.apprende.interfaces.IBackCore;
import com.cbedoy.apprende.interfaces.IMementoHandler;
import com.cbedoy.apprende.interfaces.IMessageRepresentationHandler;

/**
 * Created by Carlos on 15/10/2014.
 */
public abstract class BusinessController implements IBackCore
{
    protected IMementoHandler mementoHandler;
    protected IMessageRepresentationHandler messageRepresentationHandler;

    public void setMementoHandler(IMementoHandler mementoHandler) {
        this.mementoHandler = mementoHandler;
    }

    public void setMessageRepresentationHandler(IMessageRepresentationHandler messageRepresentationHandler) {
        this.messageRepresentationHandler = messageRepresentationHandler;
    }

    @Override
    public void backRequested() {
        mementoHandler.popDataFor(this);
    }
}
