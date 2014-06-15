package com.cbedoy.apprende;

import java.util.HashMap;

import com.cbedoy.apprende.interfaces.representationDelegates.IFeedResultRepresentationDelegate;
import com.cbedoy.apprende.keysets.FeedKeySet;
import com.cbedoy.apprende.keysets.UserKeySet;
import com.cbedoy.apprende.services.AppInstanceProvider;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class FeedView extends Activity implements IFeedResultRepresentationDelegate{

	private TextView feedTitle;
	private TextView feedLevel;
	private TextView feedQuestions;
	private TextView feedErrors;
	private TextView feedPoints;
	private TextView feedBestYou;
	private TextView feedBellowYou;
	private TextView feedPosition;
	private ListView feedList;
	private TextView feedText;
	
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
		this.feedBellowYou.setTypeface(AppInstanceProvider.lightFont);
		this.feedLevel.setTypeface(AppInstanceProvider.lightFont);
		this.feedQuestions.setTypeface(AppInstanceProvider.lightFont);
		this.feedErrors.setTypeface(AppInstanceProvider.lightFont);
		this.feedPoints.setTypeface(AppInstanceProvider.lightFont);
		this.feedBestYou.setTypeface(AppInstanceProvider.lightFont);
		this.feedTitle.setTypeface(AppInstanceProvider.lightFont);
		this.feedPosition.setTypeface(AppInstanceProvider.lightFont);
		this.feedText.setTypeface(AppInstanceProvider.regularFont);
		
	}

	@Override
	public void reloadViewWithData(HashMap<Object, Object> response) {
		
		this.feedBellowYou.setText(response.get(UserKeySet.BELLOWYOU).toString());
		this.feedLevel.setText(response.get(FeedKeySet.LEVEL).toString());
		this.feedQuestions.setText(response.get(FeedKeySet.QUESTIONS).toString());
		this.feedErrors.setText(response.get(FeedKeySet.WRONGS).toString());
		this.feedPoints.setText(response.get(FeedKeySet.POINTS).toString());
		this.feedBestYou.setText(response.get(UserKeySet.BESTOFYOU).toString());
		this.feedPosition.setText(response.get(UserKeySet.POSITION).toString());
		
		
	}
}
