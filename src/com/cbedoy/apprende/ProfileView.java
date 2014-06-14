package com.cbedoy.apprende;

import java.util.HashMap;

import com.cbedoy.apprende.interfaces.IProfileRepresentacionDelegate;
import com.cbedoy.apprende.keysets.UserKeySet;
import com.cbedoy.apprende.services.AppInstanceProvider;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileView extends Activity implements IProfileRepresentacionDelegate{

	private TextView profileName;
	private TextView profileAge;
	private TextView profileBestOfYou;
	private TextView profileBellowYou;
	private TextView profileCountry;
	private TextView profilePoints;
	private TextView profileUniversity;
	private TextView profileUserName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_view);
		
		this.profileName 		= (TextView) findViewById(R.id.profile_name);
		this.profileAge 		= (TextView) findViewById(R.id.profile_age);
		this.profileBellowYou 	= (TextView) findViewById(R.id.profile_BellowYou);
		this.profileBestOfYou 	= (TextView) findViewById(R.id.profile_BestOfYou);
		this.profileCountry 	= (TextView) findViewById(R.id.profile_country);
		this.profilePoints 		= (TextView) findViewById(R.id.profile_pints);
		this.profileUniversity 	= (TextView) findViewById(R.id.profile_university);
		this.profileUserName 	= (TextView) findViewById(R.id.profile_username);
		this.profileName.setTypeface(AppInstanceProvider.lightFont);
		this.profileAge.setTypeface(AppInstanceProvider.lightFont);
		this.profileBellowYou.setTypeface(AppInstanceProvider.lightFont);
		this.profileBestOfYou.setTypeface(AppInstanceProvider.lightFont);
		this.profileCountry.setTypeface(AppInstanceProvider.lightFont);
		this.profilePoints.setTypeface(AppInstanceProvider.lightFont);
		this.profileUniversity.setTypeface(AppInstanceProvider.lightFont);
		this.profileUserName.setTypeface(AppInstanceProvider.lightFont);
		
	}

	@Override
	public void reloadViewWithData(HashMap<UserKeySet, Object> dataModel) {
		
		this.profileAge.setText(dataModel.get(UserKeySet.AGE).toString());
		this.profileName.setText(dataModel.get(UserKeySet.FIRST_NAME)+" "+dataModel.get(UserKeySet.LAST_NAME));
		this.profileUserName.setText(dataModel.get(UserKeySet.USERNAME).toString());
		this.profileBellowYou.setText(dataModel.get(UserKeySet.BELLOWYOU).toString());
		this.profileBestOfYou.setText(dataModel.get(UserKeySet.BESTOFYOU).toString());
		this.profileCountry.setText(dataModel.get(UserKeySet.COUNTRY).toString());
		this.profileUniversity.setText(dataModel.get(UserKeySet.UNIVERSITY).toString());
		this.profilePoints.setText(dataModel.get(UserKeySet.POINTS).toString());
		
	}
}
