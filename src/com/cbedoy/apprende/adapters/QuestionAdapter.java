package com.cbedoy.apprende.adapters;

import java.util.Locale;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cbedoy.apprende.QuestionView;
import com.cbedoy.apprende.R;


public class QuestionAdapter extends FragmentPagerAdapter {

	
	public QuestionAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {

		return QuestionView.newInstance(position + 1);
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		
		return null;
	}
}
