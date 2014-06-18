package com.cbedoy.apprende;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.cbedoy.apprende.adapters.QuestionAdapter;
import com.cbedoy.apprende.bussiness.MasterController;
import com.cbedoy.apprende.interfaces.viewdelegates.IQuestionaryRepresentationDelegate;
import com.cbedoy.apprende.keysets.CourseKeySet;
import com.cbedoy.apprende.keysets.QuestionKeySet;
import com.cbedoy.apprende.keysets.ServiceKeySet;
import com.cbedoy.apprende.services.AppInstanceProvider;

public class QuestionaryView extends FragmentActivity  implements IQuestionaryRepresentationDelegate{

	private QuestionAdapter 		questionAdapter;
	private ViewPager 				mViewPager;
	private MasterController		masterController;
	private List<Object>			dataModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionary_view);
		this.dataModel 				= new ArrayList<Object>();
		this.masterController = AppInstanceProvider.getInstance().instanceServiceQuestionary(this, ServiceKeySet.GET_EXAM_RANDOM);
		this.masterController.getAnsycTask().execute();
	}

	@Override
	public void reloadViewWithQuestions(JSONArray response) {
		if(response!=null){
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
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		questionAdapter 	= new QuestionAdapter(getSupportFragmentManager(), dataModel);
		mViewPager 			= (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(questionAdapter);
		
		
	}


}
