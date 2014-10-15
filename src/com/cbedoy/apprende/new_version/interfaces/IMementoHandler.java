package com.cbedoy.apprende.new_version.interfaces;



import com.cbedoy.apprende.new_version.service.Memento;

import java.util.HashMap;

public interface IMementoHandler {

    public void clearStack();

    public Memento getTopMemento();

    public boolean popDataFor(Object owner);

    public Object getLastOwnerWithBackSupport();

    public void setStateForOwner(HashMap<String, Object> data, Object owner);

}
