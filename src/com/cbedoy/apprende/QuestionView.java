package com.cbedoy.apprende;

import com.cbedoy.apprende.services.AppInstanceProvider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;



public  class QuestionView extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";

	public static Fragment newInstance(int sectionNumber) {
		QuestionView fragment 	= new QuestionView();
		Bundle args 			= new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public QuestionView() {
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView 			= inflater.inflate(R.layout.fragment_questionary_view, container, false);
		TextView question 		= (TextView) rootView.findViewById(R.id.question);
		RadioButton optionOne 	= (RadioButton) rootView.findViewById(R.id.optionOne);
		RadioButton optionTwo 	= (RadioButton) rootView.findViewById(R.id.optionTwo);
		RadioButton optionThree	= (RadioButton) rootView.findViewById(R.id.optionThree);
		RadioButton optionFour 	= (RadioButton) rootView.findViewById(R.id.optionFour);
		
		question.setTypeface(AppInstanceProvider.regularFont);
		optionOne.setTypeface(AppInstanceProvider.lightFont);
		optionTwo.setTypeface(AppInstanceProvider.lightFont);
		optionThree.setTypeface(AppInstanceProvider.lightFont);
		optionFour.setTypeface(AppInstanceProvider.lightFont);
		return rootView;
	}
}
