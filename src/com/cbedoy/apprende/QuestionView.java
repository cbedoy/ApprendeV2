package com.cbedoy.apprende;

import java.util.HashMap;
import java.util.List;

import com.cbedoy.apprende.keysets.QuestionKeySet;
import com.cbedoy.apprende.services.AppInstanceProvider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;



public  class QuestionView extends Fragment {

	private View 		rootView;
	private TextView 	question;
	private RadioButton optionOne;
	private RadioButton optionTwo;
	private RadioButton optionThree;
	private RadioButton optionFour;
	
	public QuestionView() {
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.rootView 		= inflater.inflate(R.layout.fragment_questionary_view, container, false);
		this.question 		= (TextView) rootView.findViewById(R.id.question);
		this.optionOne 		= (RadioButton) rootView.findViewById(R.id.optionOne);
		this.optionTwo 		= (RadioButton) rootView.findViewById(R.id.optionTwo);
		this.optionThree	= (RadioButton) rootView.findViewById(R.id.optionThree);
		this.optionFour 	= (RadioButton) rootView.findViewById(R.id.optionFour);
		
		CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton view, boolean isChecked) {
				
			}
		};
		
		question.setTypeface(AppInstanceProvider.regularFont);
		optionOne.setTypeface(AppInstanceProvider.lightFont);
		optionTwo.setTypeface(AppInstanceProvider.lightFont);
		optionThree.setTypeface(AppInstanceProvider.lightFont);
		optionFour.setTypeface(AppInstanceProvider.lightFont);
		optionOne.setOnCheckedChangeListener(checkedChangeListener);
		optionTwo.setOnCheckedChangeListener(checkedChangeListener);
		optionThree.setOnCheckedChangeListener(checkedChangeListener);
		optionFour.setOnCheckedChangeListener(checkedChangeListener);
		return rootView;
	}
	
	public void reloadView(HashMap<Object, Object> information){
		this.question.setText(information.get(QuestionKeySet.QUESTION).toString());
		this.optionOne.setText(information.get(QuestionKeySet.ANSWER_1).toString());
		this.optionTwo.setText(information.get(QuestionKeySet.ANSWER_2).toString());
		this.optionThree.setText(information.get(QuestionKeySet.ANSWER_3).toString());
		this.optionFour.setText(information.get(QuestionKeySet.ANSWER_4).toString());
	}
}
