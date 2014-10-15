package com.cbedoy.apprende.new_version.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.cbedoy.apprende.R;

/**
 * Created by Carlos on 14/10/2014.
 */
public class CategoryViewController extends AbstractViewController {

    private ListView listView;
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
}
