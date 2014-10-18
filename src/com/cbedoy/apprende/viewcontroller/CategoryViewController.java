package com.cbedoy.apprende.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.business.category.interfaces.ICategoryRepresentationDelegate;
import com.cbedoy.apprende.business.category.interfaces.ICategoryRepresentationHandler;

import java.util.HashMap;

/**
 * Created by Carlos on 14/10/2014.
 */
public class CategoryViewController extends AbstractViewController implements ICategoryRepresentationHandler{

    private ListView listView;

    private ICategoryRepresentationDelegate categoryRepresentationDelegate;

    public void setCategoryRepresentationDelegate(ICategoryRepresentationDelegate categoryRepresentationDelegate) {
        this.categoryRepresentationDelegate = categoryRepresentationDelegate;
    }

    @Override
    protected View init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.app_category_viewcontroller,  null);
        listView = (ListView) view.findViewById(R.id.app_category_viewcontroller_list);
        return view;
    }

    @Override
    public void reload() {

    }

    @Override
    public void showCategoryViewWithData(HashMap<String, Object> data) {

    }
}
