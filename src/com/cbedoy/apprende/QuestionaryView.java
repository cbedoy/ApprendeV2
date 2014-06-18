package com.cbedoy.apprende;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.cbedoy.apprende.adapters.QuestionAdapter;

public class QuestionaryView extends FragmentActivity  {

	private QuestionAdapter 		questionAdapter;
	private ViewPager 				mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionary_view);
		
		questionAdapter 	= new QuestionAdapter(getSupportFragmentManager());
		mViewPager 			= (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(questionAdapter);

	}


}
