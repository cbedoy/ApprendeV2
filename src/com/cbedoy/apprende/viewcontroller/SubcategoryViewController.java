package com.cbedoy.apprende.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.artifacts.CustomViewCell;
import com.cbedoy.apprende.business.subcategory.interfaces.ISubcategoryRepresentationDelegate;
import com.cbedoy.apprende.business.subcategory.interfaces.ISubcategoryRepresentationHandler;
import com.cbedoy.apprende.viewcontroller.abstracts.AbstractViewController;
import com.cbedoy.apprende.widgets.NavigationBar;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Carlos on 14/10/2014.
 */
public class SubcategoryViewController extends AbstractViewController implements ISubcategoryRepresentationHandler, NavigationBar.INavigationBarDelegate{

    private GridView gridView;
    private List<Object> subcategories;
    private LayoutInflater inflater;
    private ISubcategoryRepresentationDelegate subcategoryRepresentationDelegate;
    private CustomViewCell customViewCell;

    public void setSubcategoryRepresentationDelegate(ISubcategoryRepresentationDelegate subcategoryRepresentationDelegate) {
        this.subcategoryRepresentationDelegate = subcategoryRepresentationDelegate;
    }

    @Override
    protected View init() {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.app_subcategory_viewcontroller,  null);
        navigationBar.initWithView(view);
        navigationBar.setNavigationBarDelegate(this);
        gridView = (GridView) view.findViewById(R.id.app_subcategory_viewcontroller_gridview);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                messageRepresentation.showLoading();
                HashMap<String, Object> subcategory = (HashMap<String, Object>) subcategories.get(i);
                subcategoryRepresentationDelegate.userSelectedCategory(subcategory);
            }
        });
        return view;
    }

    @Override
    public void reload() {

    }

    @Override
    public void showSubcategoryViewWithData(List<Object> subcategories) {
        this.appViewManager.presentViewForTag(this.tag);
        this.appViewManager.statusByLeftMenu(false);
        this.subcategories = subcategories;
        this.customViewCell = new CustomViewCell(context, inflater, subcategories);
        this.gridView.setAdapter(customViewCell);
    }

    @Override
    public void showPreviewViewController() {
        this.appViewManager.presentViewForTag(CONTROLLER.CATEGORY);
    }


    @Override
    public boolean onBackPressed() {
        appViewManager.presentViewForTag(CONTROLLER.CATEGORY);
        return false;
    }
}
