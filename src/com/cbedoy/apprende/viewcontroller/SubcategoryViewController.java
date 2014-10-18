package com.cbedoy.apprende.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.business.subcategory.interfaces.ISubcategoryRepresentationDelegate;
import com.cbedoy.apprende.business.subcategory.interfaces.ISubcategoryRepresentationHandler;

import java.util.HashMap;

/**
 * Created by Carlos on 14/10/2014.
 */
public class SubcategoryViewController extends AbstractViewController implements ISubcategoryRepresentationHandler{

    private GridView gridView;

    private ISubcategoryRepresentationDelegate subcategoryRepresentationDelegate;

    public void setSubcategoryRepresentationDelegate(ISubcategoryRepresentationDelegate subcategoryRepresentationDelegate) {
        this.subcategoryRepresentationDelegate = subcategoryRepresentationDelegate;
    }

    @Override
    protected View init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.app_subcategory_viewcontroller,  null);
        gridView = (GridView) view.findViewById(R.id.app_subcategory_viewcontroller_gridview);
        return view;
    }

    @Override
    public void reload() {

    }

    @Override
    public void showSubcategoryViewWithData(HashMap<String, Object> data) {

    }
}
