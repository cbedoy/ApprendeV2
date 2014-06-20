package com.cbedoy.apprende;

import java.util.HashMap;
import java.util.List;

import com.cbedoy.apprende.adapters.FeedViewAdapter;
import com.cbedoy.apprende.bussiness.MasterController;
import com.cbedoy.apprende.interfaces.representationDelegates.IFeedResultRepresentationDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.IFeedViewDelegate;
import com.cbedoy.apprende.keysets.FeedKeySet;
import com.cbedoy.apprende.keysets.QuestionKeySet;
import com.cbedoy.apprende.keysets.ServiceKeySet;
import com.cbedoy.apprende.keysets.UserKeySet;
import com.cbedoy.apprende.services.AppInstanceProvider;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

public class FeedView extends Activity{

	private TextView 		feedTitle;
	private TextView 		feedLevel;
	private TextView 		feedQuestions;
	private TextView 		feedErrors;
	private TextView 		feedPoints;
	private TextView 		feedBestYou;
	private TextView 		feedBellowYou;
	private TextView 		feedPosition;
	private ListView 		feedList;
	private TextView 		feedText;
	private FeedViewAdapter feedViewAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed_view);
		
		this.feedBellowYou 			= (TextView)findViewById(R.id.feed_bellow);
		this.feedLevel 				= (TextView)findViewById(R.id.feed_level);
		this.feedQuestions 			= (TextView)findViewById(R.id.feed_questions);
		this.feedErrors 			= (TextView)findViewById(R.id.feed_error);
		this.feedPoints 			= (TextView)findViewById(R.id.feed_points);
		this.feedBestYou 			= (TextView)findViewById(R.id.feed_best);
		this.feedText 				= (TextView)findViewById(R.id.text_feed);
		this.feedPosition 			= (TextView)findViewById(R.id.feed_position);
		this.feedList				= (ListView)findViewById(R.id.feed_list);
		this.feedTitle				= (TextView)findViewById(R.id.feed_title);
		this.feedViewAdapter		= new FeedViewAdapter(getApplicationContext());
		this.feedBellowYou.setTypeface(AppInstanceProvider.lightFont);
		this.feedLevel.setTypeface(AppInstanceProvider.lightFont);
		this.feedQuestions.setTypeface(AppInstanceProvider.lightFont);
		this.feedErrors.setTypeface(AppInstanceProvider.lightFont);
		this.feedPoints.setTypeface(AppInstanceProvider.lightFont);
		this.feedBestYou.setTypeface(AppInstanceProvider.lightFont);
		this.feedTitle.setTypeface(AppInstanceProvider.lightFont);
		this.feedPosition.setTypeface(AppInstanceProvider.lightFont);
		this.feedText.setTypeface(AppInstanceProvider.regularFont);
		this.feedList.setAdapter(feedViewAdapter);
		this.reloadData(MasterController.getInstance().getQuestionaryInfo());
		
	}

    
    public void reloadData(List<Object> dataModel) {
    	int size 		= dataModel.size();
    	int errors 		= 0;
    	float factor 	= 0;
    	this.feedLevel.setText(size>=20?"Hard.":size>=10?"Medium.":"Ea sy.");
    	for(Object object : dataModel){
    		@SuppressWarnings("unchecked")
			HashMap<Object, Object> information = (HashMap<Object, Object>) object;
    		boolean contains = information.containsKey(QuestionKeySet.OPTION_USER);
    		if(contains){
    			errors+= ((information.containsKey(QuestionKeySet.OPTION_USER) != information.containsKey(QuestionKeySet.CORRECT)))?1:0;
    		}else{
    			errors+=1;
    		}
    	}
    	factor = (float)(size-errors)/(float)size;
    	this.feedQuestions.setText(size+" questions.");
    	this.feedErrors.setText(errors+" errors.");
    	this.feedPoints.setText(String.format("%.2f", factor*100)+" points.");
    	this.feedTitle.setText("@"+MasterController.getInstance().getUserInformation().get(UserKeySet.USERNAME));
    	this.feedViewAdapter.reloadWithData(dataModel);
    }
}
