package com.cbedoy.apprende.artifacts;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.cbedoy.apprende.viewcontroller.AbstractViewController;
import com.cbedoy.apprende.widgets.QuestionView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Carlos on 19/10/2014.
 */
public class QuestionViewPager extends PagerAdapter
{

    private List<Object> dataModel;
    private List<QuestionView> questionModel;
    private Context context;
    public QuestionViewPager(Context context, List<Object> dataModel, List<QuestionView> questionModel){
        this.context = context;
        this.dataModel = dataModel;
        this.questionModel = questionModel;
    }

    @Override
    public int getCount() {
        return dataModel.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == ((QuestionView)o).getView();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        QuestionView questionView = questionModel.get(position);
        HashMap<String, Object> currentModel = (HashMap<String, Object>) dataModel.get(position);
        questionView.setDataModel(currentModel);
        questionView.getView();
        questionView.reload();
        ((ViewPager)container).addView(questionView.getView());
        return questionView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(((QuestionView)object).getView());
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