package com.cbedoy.apprende.artifacts;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.cbedoy.apprende.viewcontroller.AbstractViewController;

import java.util.List;

/**
 * Created by Carlos on 19/10/2014.
 */
public class QuestionViewPager extends PagerAdapter
{

    private List<Object> viewModel;
    private List<AbstractViewController> questionModel;
    private Context context;
    public QuestionViewPager(Context context, List<Object> viewModel, List<AbstractViewController> questionModel){
        this.context = context;
        this.viewModel = viewModel;
        this.questionModel = questionModel;
    }

    @Override
    public int getCount() {
        return viewModel.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == ((AbstractViewController)o).getView();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        AbstractViewController abstractViewController = questionModel.get(position);
        ((ViewPager)container).addView(abstractViewController.getView());
        return abstractViewController;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(((AbstractViewController)object).getView());
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }

    @Override
    public Parcelable saveState() {
        return super.saveState();
    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
    }
}