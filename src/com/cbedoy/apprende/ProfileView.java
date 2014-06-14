package com.cbedoy.apprende;

import com.cbedoy.apprende.services.AppInstanceProvider;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfileView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_view);
		
		((TextView) findViewById(R.id.profile_name)).setTypeface(AppInstanceProvider.lightFont);
		((TextView) findViewById(R.id.profile_age)).setTypeface(AppInstanceProvider.lightFont);
		((TextView) findViewById(R.id.profile_BellowYou)).setTypeface(AppInstanceProvider.lightFont);
		((TextView) findViewById(R.id.profile_BestOfYou)).setTypeface(AppInstanceProvider.lightFont);
		((TextView) findViewById(R.id.profile_country)).setTypeface(AppInstanceProvider.lightFont);
		((TextView) findViewById(R.id.profile_pints)).setTypeface(AppInstanceProvider.lightFont);
		((TextView) findViewById(R.id.profile_university)).setTypeface(AppInstanceProvider.lightFont);
		((TextView) findViewById(R.id.profile_username)).setTypeface(AppInstanceProvider.lightFont);
		
		
		
	}
}
