package com.cbedoy.apprende;

import org.json.JSONObject;

import com.cbedoy.apprende.adapters.CourseViewAdapter;
import com.cbedoy.apprende.adapters.ThemeViewAdapter;
import com.cbedoy.apprende.bussiness.MasterController;
import com.cbedoy.apprende.interfaces.IAsyncServiceDelegate;
import com.cbedoy.apprende.keysets.ServiceKeySet;
import com.cbedoy.apprende.services.AppInstanceProvider;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ThemeView extends Activity implements IAsyncServiceDelegate{

	private ListView 			themeList;
	private ListView 			courseList;
	private ThemeViewAdapter 	themeViewAdapter;
	private CourseViewAdapter 	courseViewAdapter;
	private MasterController	masterController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_theme_view);
		this.courseViewAdapter 	= new CourseViewAdapter(getApplicationContext());
		this.themeViewAdapter 	= new ThemeViewAdapter(getApplicationContext());
		this.themeList 			= (ListView)findViewById(R.id.theme_listView);
		this.courseList	 		= (ListView)findViewById(R.id.course_view_list);
		this.themeList.setAdapter(themeViewAdapter);
		this.courseList.setAdapter(courseViewAdapter);
		
		
		//AppInstanceProvider.getInstance().setAnsycServiceDelegate(this);
		//this.masterController 	= AppInstanceProvider.getInstance().instanceServiceTheme(this, ServiceKeySet.GET_THEME);
		//this.masterController.getAsyncServiceController().execute();
	}
	
	@Override
	public void reloadData(JSONObject jsonObject) {
		
		
	}
}
