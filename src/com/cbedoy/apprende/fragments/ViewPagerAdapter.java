package com.cbedoy.apprende.fragments;

import com.cbedoy.apprende.models.CuestionarioDemo;
import com.cbedoy.apprende.models.Pregunta;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;



public class ViewPagerAdapter extends FragmentPagerAdapter {
	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = new QuestionView();
		Bundle args = new Bundle();
		args.putInt(QuestionView.ARG_SECTION_NUMBER, position);
		args.putInt("size", 10);
		fragment.setArguments(args);
		Log.i("depu", "Posicion del fragment"
				+position);
		return fragment;
	}

	@Override
	public int getCount() {
		// Show 3 total pages.
		return 10;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return null;

	}
	
	
}
