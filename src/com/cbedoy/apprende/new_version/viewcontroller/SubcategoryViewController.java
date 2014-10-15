package com.cbedoy.apprende.new_version.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

import com.cbedoy.apprende.R;

/**
 * Created by Carlos on 14/10/2014.
 */
public class SubcategoryViewController extends AbstractViewController {

    private GridView gridView;
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
}
