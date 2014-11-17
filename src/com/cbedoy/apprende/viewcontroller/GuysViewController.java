package com.cbedoy.apprende.viewcontroller;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.business.guy.interfaces.IGuyRepresentationDelegate;
import com.cbedoy.apprende.business.guy.interfaces.IGuyRepresentationHandler;
import com.cbedoy.apprende.viewcontroller.abstracts.AbstractViewController;
import com.cbedoy.apprende.widgets.GuyView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Carlos on 17/11/2014.
 * <p/>
 * Mobile App Developer
 * <p/>
 * Apprende
 */
public class GuysViewController extends AbstractViewController implements IGuyRepresentationHandler
{

    private IGuyRepresentationDelegate representationDelegate;
    private ImageView background;
    private ViewPager viewPager;
    private ArrayList<Object> guyDataModel;
    private ArrayList<GuyView> guyViewModel;

    public void setRepresentationDelegate(IGuyRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    @Override
    protected View init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = inflater.inflate(R.layout.app_aprende_viewcontroller,  null);
        this.background = (ImageView) view.findViewById(R.id.background_apprende);
        this.viewPager = (ViewPager) view.findViewById(R.id.pager);
        this.guyDataModel = new ArrayList<Object>();
        this.guyViewModel = new ArrayList<GuyView>();
        return view;
    }

    @Override
    public void reload() {

    }

    @Override
    public void reloadWithData(List<HashMap<String, Object>> guyList) {

    }
}
