package com.cbedoy.apprende.widgets.abstracts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Carlos on 17/11/2014.
 * <p/>
 * Mobile App Developer
 * <p/>
 * Apprende
 */
public abstract class AbstractView
{
    protected List<HashMap<String, Object>> dataModel;
    protected List<HashMap<String, Object>> viewModel;
    protected Context context;
    protected View view;
    protected LayoutInflater layoutInflater;

    public View getView(){
        if(view == null)
            view = init();
        return view;
    }

    public abstract View init();
    public abstract void reload();

}
