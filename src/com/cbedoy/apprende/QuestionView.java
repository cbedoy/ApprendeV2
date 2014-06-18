package com.cbedoy.apprende;

import java.util.HashMap;
import java.util.List;

import com.cbedoy.apprende.interfaces.viewdelegates.IQuestionViewHandler;
import com.cbedoy.apprende.keysets.QuestionKeySet;
import com.cbedoy.apprende.services.AppInstanceProvider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;



public  class QuestionView extends Fragment {

	private View 					rootView;
	private TextView 				question;
	private RadioButton 			optionOne;
	private RadioButton 			optionTwo;
	private RadioButton 			optionThree;
	private RadioButton 			optionFour;
	private Button 					finishAction;
	private HashMap<Object, Object> information;
	private IQuestionViewHandler 	questionHandler;
	
	public QuestionView(HashMap<Object, Object> information) {
		this.information = information;
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.rootView 		= inflater.inflate(R.layout.fragment_questionary_view, container, false);
		this.question 		= (TextView) rootView.findViewById(R.id.question);
		this.optionOne 		= (RadioButton) rootView.findViewById(R.id.optionOne);
		this.optionTwo 		= (RadioButton) rootView.findViewById(R.id.optionTwo);
		this.optionThree	= (RadioButton) rootView.findViewById(R.id.optionThree);
		this.optionFour 	= (RadioButton) rootView.findViewById(R.id.optionFour);
		this.finishAction	= (Button) rootView.findViewById(R.id.action_finish);
		this.finishAction.setVisibility(this.getArguments().getBoolean("isLast")? View.VISIBLE:View.INVISIBLE);
		
		CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton view, boolean isChecked) {
				int position = getArguments().getInt("positon");
				if(optionOne.isChecked()) 	information.put(QuestionKeySet.OPTION_USER, 1);
				if(optionTwo.isChecked()) 	information.put(QuestionKeySet.OPTION_USER, 2);
				if(optionThree.isChecked()) information.put(QuestionKeySet.OPTION_USER, 3);
				if(optionFour.isChecked()) 	information.put(QuestionKeySet.OPTION_USER, 4);
				questionHandler.updateInformation(information, position);
			}
		};
		View.OnClickListener finishActionListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				
			}
		};
		this.finishAction.setOnClickListener(finishActionListener);
		this.question.setTypeface(AppInstanceProvider.regularFont);
		this.optionOne.setTypeface(AppInstanceProvider.lightFont);
		this.optionTwo.setTypeface(AppInstanceProvider.lightFont);
		this.optionThree.setTypeface(AppInstanceProvider.lightFont);
		this.optionFour.setTypeface(AppInstanceProvider.lightFont);
		this.optionOne.setOnCheckedChangeListener(checkedChangeListener);
		this.optionTwo.setOnCheckedChangeListener(checkedChangeListener);
		this.optionThree.setOnCheckedChangeListener(checkedChangeListener);
		this.optionFour.setOnCheckedChangeListener(checkedChangeListener);
		this.question.setText(information.get(QuestionKeySet.QUESTION).toString());
		this.optionOne.setText(information.get(QuestionKeySet.ANSWER_1).toString());
		this.optionTwo.setText(information.get(QuestionKeySet.ANSWER_2).toString());
		this.optionThree.setText(information.get(QuestionKeySet.ANSWER_3).toString());
		this.optionFour.setText(information.get(QuestionKeySet.ANSWER_4).toString());
		return rootView;
	}
	
	public void reloadView(HashMap<Object, Object> information){
		
	}


	public void setViewHandler(IQuestionViewHandler questionAdapter) {
		this.questionHandler = questionAdapter;
		
	}
}
