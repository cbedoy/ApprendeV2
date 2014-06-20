package com.cbedoy.apprende;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cbedoy.apprende.adapters.GuyViewAdapter;
import com.cbedoy.apprende.bussiness.MasterController;
import com.cbedoy.apprende.interfaces.representationDelegates.IProfileRepresentacionDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.IGuyViewDelegate;
import com.cbedoy.apprende.keysets.ServiceKeySet;
import com.cbedoy.apprende.keysets.UserKeySet;
import com.cbedoy.apprende.services.AppInstanceProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ProfileView extends Activity implements IProfileRepresentacionDelegate, IGuyViewDelegate{

	private TextView 			profileName;
	private TextView 			profileAge;
	private TextView 			profileBestOfYou;
	private TextView 			profileBellowYou;
	private TextView 			profilePoints;
	private TextView 			profileUniversity;
	private TextView 			profileUserName;
	private TextView 			profilePlays;
	private TextView 			profileFacebook;
	private TextView 			profileTwitter;
	private ImageView 			profileImage;
	private GuyViewAdapter 		guyViewAdapter;
	private List<Object>		dataModel;
	private MasterController 	masterController;
	private AlertDialog.Builder builder;
	private ListView			guysList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_view);
		this.profileImage       = (ImageView)findViewById(R.id.user_image);
		this.profileName 		= (TextView) findViewById(R.id.profile_name);
		this.profileAge 		= (TextView) findViewById(R.id.profile_age);
		this.profileBellowYou 	= (TextView) findViewById(R.id.profile_BellowYou);
		this.profileBestOfYou 	= (TextView) findViewById(R.id.profile_BestOfYou);
		this.profilePoints 		= (TextView) findViewById(R.id.profile_pints);
		this.profileUniversity 	= (TextView) findViewById(R.id.profile_university);
		this.profileUserName 	= (TextView) findViewById(R.id.profile_username);
		this.profileFacebook 	= (TextView) findViewById(R.id.profile_facebook);
		this.profileTwitter		= (TextView) findViewById(R.id.profile_twitter);
		this.profilePlays		= (TextView) findViewById(R.id.profile_plays);
		this.guysList			= (ListView) findViewById(R.id.guys_view_list);
		this.guyViewAdapter		= new GuyViewAdapter(this);
		this.dataModel			= new ArrayList<Object>();
		this.profileName.setTypeface(AppInstanceProvider.lightFont);
		this.profileAge.setTypeface(AppInstanceProvider.lightFont);
		this.profileBellowYou.setTypeface(AppInstanceProvider.lightFont);
		this.profileBestOfYou.setTypeface(AppInstanceProvider.lightFont);
		this.profilePoints.setTypeface(AppInstanceProvider.lightFont);
		this.profileUniversity.setTypeface(AppInstanceProvider.lightFont);
		this.profileUserName.setTypeface(AppInstanceProvider.lightFont);
		this.profileFacebook.setTypeface(AppInstanceProvider.lightFont);
		this.profilePlays.setTypeface(AppInstanceProvider.lightFont);;
		this.profileTwitter.setTypeface(AppInstanceProvider.lightFont);
		this.reloadViewWithData(MasterController.getInstance().getUserInformation());
		
		this.masterController = AppInstanceProvider.getInstance().instanceServiceGuys(this, ServiceKeySet.GET_GUYS);
		this.masterController.getAnsycTask().execute();
	}

	@Override
	public void reloadViewWithData(HashMap<Object, Object> dataModel) {
		
		this.profileAge.setText(dataModel.get(UserKeySet.AGE).toString()+" years");
		this.profileName.setText(dataModel.get(UserKeySet.FIRST_NAME)+" "+dataModel.get(UserKeySet.LAST_NAME));
		this.profileUserName.setText(dataModel.get(UserKeySet.USERNAME).toString());
		//this.profileBellowYou.setText(dataModel.get(UserKeySet.BELLOWYOU).toString());
		//this.profileBestOfYou.setText(dataModel.get(UserKeySet.BESTOFYOU).toString());
		//this.profileCountry.setText(dataModel.get(UserKeySet.COUNTRY).toString());
		//this.profileUniversity.setText(dataModel.get(UserKeySet.UNIVERSITY).toString());
		this.profilePoints.setText(dataModel.get(UserKeySet.POINTS).toString()+" points");
		this.profileFacebook.setText("facebook.com/"+dataModel.get(UserKeySet.FACEBOOK).toString());
		this.profileTwitter.setText(dataModel.get(UserKeySet.TWITTER).toString());
		this.profilePlays.setText(dataModel.get(UserKeySet.PLAYS).toString()+" plays");
		//this.profileImage.setImageBitmap(AppInstanceProvider.getInstance().getImageFromURL(dataModel.get(UserKeySet.FACEBOOK).toString()));
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.profile_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_guys:
	            
	            return true;
	        case R.id.action_course:
	            Intent intent = new Intent(this, ThemeView.class);
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	public void reloadDataWithGuys(JSONArray response) {
		
		if(response == null || response.length() == 0){
			showErrorView();
		}else{
			this.dataModel.clear();
			for(int i = 0; i< response.length(); i++){
				try {
					JSONObject object 						= response.getJSONObject(i);
					HashMap<Object, Object> information 	= new HashMap<Object, Object>();
					information.put(UserKeySet.ID, 			object.get("pk"));
					JSONObject fields 						= object.getJSONObject("fields");
					information.put(UserKeySet.USERNAME, 	fields.get("username"));
					information.put(UserKeySet.POINTS, 		fields.get("points"));
					this.dataModel.add(information);
					} catch (JSONException e) {
						e.printStackTrace();
				}
				
			}
			this.guyViewAdapter.reloadViewWithData(dataModel);
			this.guysList.setAdapter(guyViewAdapter);
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
