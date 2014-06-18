package com.cbedoy.apprende.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cbedoy.apprende.QuestionView;



public class QuestionAdapter extends FragmentPagerAdapter{

	
	private List<Object> dataModel;
	private QuestionView questionView;
	
	public QuestionAdapter(FragmentManager fm, List<Object> dataModel) {
		super(fm);
		this.dataModel = dataModel;
	}

	@Override
	public Fragment getItem(int position) {
		this.questionView = new QuestionView();
		this.questionView.reloadView((HashMap<Object, Object>) dataModel.get(position));
		return this.questionView;
	}

	@Override
	public int getCount() {
		return dataModel.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {	
		return null;
	}

}
