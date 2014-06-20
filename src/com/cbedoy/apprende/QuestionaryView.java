package com.cbedoy.apprende;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.cbedoy.apprende.adapters.QuestionAdapter;
import com.cbedoy.apprende.bussiness.MasterController;
import com.cbedoy.apprende.interfaces.viewdelegates.IQuestionaryRepresentationDelegate;
import com.cbedoy.apprende.keysets.CourseKeySet;
import com.cbedoy.apprende.keysets.QuestionKeySet;
import com.cbedoy.apprende.keysets.ServiceKeySet;
import com.cbedoy.apprende.keysets.ThemeKeySet;
import com.cbedoy.apprende.services.AppInstanceProvider;

public class QuestionaryView extends FragmentActivity  implements IQuestionaryRepresentationDelegate{

	private QuestionAdapter 		questionAdapter;
	private ViewPager 				mViewPager;
	private MasterController		masterController;
	private List<Object>			dataModel;
	private AlertDialog.Builder		builder;
	public static QuestionaryView 	INSTANCE;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionary_view);
		Object level 				= getIntent().getExtras().get("level");
		Object theme 				= getIntent().getExtras().get("theme");
		
		if(level.equals("0"))
			this.masterController 	= AppInstanceProvider.getInstance().instanceServiceQuestionary(this, ServiceKeySet.GET_EXAM_RANDOM);
		else{
			HashMap<Object, Object> information = new HashMap<Object, Object>();
			information.put(ThemeKeySet.LEVEL, level);
			information.put(ThemeKeySet.ID, theme);
			this.masterController = AppInstanceProvider.getInstance().instanceServiceQuestionary(this, information, ServiceKeySet.GET_EXAM);
		}
		this.dataModel 				= new ArrayList<Object>();
		this.masterController.getAnsycTask().execute();
		INSTANCE = this;
	}

	@Override
	public void reloadViewWithQuestions(JSONArray response) {
		if(response==null || response.length() == 0)
			showErrorView();
		else{
			try {
				for(int index = 0; index < response.length(); index++){
					JSONObject data 							= response.getJSONObject(index);
					JSONObject fields							= data.getJSONObject("fields");
					HashMap<Object, Object> information 		= new HashMap<Object, Object>();
					information.put(QuestionKeySet.ID, 			data.get("pk"));
					information.put(QuestionKeySet.FEEDBACK, 	fields.get("feedback"));
					information.put(QuestionKeySet.ANSWER_1, 	fields.get("answer1"));
					information.put(QuestionKeySet.ANSWER_2, 	fields.get("answer2"));
					information.put(QuestionKeySet.ANSWER_3, 	fields.get("answer3"));
					information.put(QuestionKeySet.ANSWER_4, 	fields.get("answer4"));
					information.put(QuestionKeySet.CORRECT, 	fields.get("correct"));
					information.put(QuestionKeySet.QUESTION, 	fields.get("question"));
					this.dataModel.add(information);
				}
				questionAdapter 	= new QuestionAdapter(getSupportFragmentManager(), dataModel);
				mViewPager 			= (ViewPager) findViewById(R.id.pager);
				mViewPager.setAdapter(questionAdapter);
			} catch (JSONException e) {
				e.printStackTrace();
				showErrorView();
			}
		}
	}

	private void showErrorView() {
		if(builder==null)
			builder = new AlertDialog.Builder(this);
		builder.setTitle("Oh no!");
		builder.setMessage("Ups no information available, Possibly no questions available, try later :)");
		builder.setCancelable(false);
		builder.setNegativeButton("Ok", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}} 
		);
		builder.show();
		
	}


}
