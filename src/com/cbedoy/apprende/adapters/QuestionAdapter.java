package com.cbedoy.apprende.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cbedoy.apprende.QuestionView;
import com.cbedoy.apprende.interfaces.viewdelegates.IQuestionViewHandler;



public class QuestionAdapter extends FragmentPagerAdapter implements IQuestionViewHandler{

	
	private List<Object> dataModel;
	private QuestionView questionView;
	
	public QuestionAdapter(FragmentManager fm, List<Object> dataModel) {
		super(fm);
		this.dataModel = dataModel;
	}

	@Override
	public Fragment getItem(int position) {
		this.questionView = new QuestionView((HashMap<Object, Object>) dataModel.get(position));
		Bundle params     = new Bundle();
		params.putBoolean("isLast", position == dataModel.size()-1 ? true : false);
		params.putInt("position", position);
		this.questionView.setArguments(params);
		this.questionView.setViewHandler(this);
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

	@Override
	public void updateInformation(Object information, int position) {
		this.dataModel.set(position, information);
	}

}
