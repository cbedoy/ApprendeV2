package com.uandroides.aprende.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;



public class SectionsPagerAdapter extends FragmentPagerAdapter {

	public SectionsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {

		return new PreguntaExamen();
	}

	@Override
	public int getCount() {
		// Show 3 total pages.
		return 5;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return null;

	}
}
