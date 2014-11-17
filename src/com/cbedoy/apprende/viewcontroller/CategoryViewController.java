package com.cbedoy.apprende.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.artifacts.CustomViewCell;
import com.cbedoy.apprende.business.category.interfaces.ICategoryRepresentationDelegate;
import com.cbedoy.apprende.business.category.interfaces.ICategoryRepresentationHandler;
import com.cbedoy.apprende.viewcontroller.abstracts.AbstractViewController;
import com.cbedoy.apprende.widgets.NavigationBar;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Carlos on 14/10/2014.
 *
 * Mobile App Developer
 *
 */
public class CategoryViewController extends AbstractViewController implements ICategoryRepresentationHandler, NavigationBar.INavigationBarDelegate{

    private ListView listView;
    private LayoutInflater inflater;
    private ICategoryRepresentationDelegate categoryRepresentationDelegate;
    private List<Object> categories;
    private CustomViewCell customViewCell;
    public void setCategoryRepresentationDelegate(ICategoryRepresentationDelegate categoryRepresentationDelegate) {
        this.categoryRepresentationDelegate = categoryRepresentationDelegate;
    }

    @Override
    protected View init() {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.app_category_viewcontroller,  null);
        navigationBar.initWithView(view);
        navigationBar.setNavigationBarDelegate(this);
        listView = (ListView) view.findViewById(R.id.app_category_viewcontroller_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, Object> category = (HashMap<String, Object>) categories.get(i);
                categoryRepresentationDelegate.userSelectedCategory(category);
            }
        });
        return view;
    }

    @Override
    public void reload() {
    }

    @Override
    public void showCategoryViewWithData(List<Object> categories) {
        this.appViewManager.presentViewForTag(this.tag);
        this.categories = categories;
        this.customViewCell = new CustomViewCell(context, inflater, categories);
        this.listView.setAdapter(customViewCell);
    }

    @Override
    public void showPreviewViewController() {
        this.appViewManager.presentViewForTag(CONTROLLER.PROFILE);
        this.appViewManager.statusByLeftMenu(false);
    }


    @Override
    public boolean onBackPressed() {
        appViewManager.presentViewForTag(CONTROLLER.PROFILE);
        return false;
    }
}
