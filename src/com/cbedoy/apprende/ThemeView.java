package com.cbedoy.apprende;

import com.cbedoy.apprende.adapters.CourseViewAdapter;
import com.cbedoy.apprende.adapters.ThemeViewAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class ThemeView extends Activity {

	private ListView 			themeList;
	private ListView 			courseList;
	private ThemeViewAdapter 	themeViewAdapter;
	private CourseViewAdapter 	courseViewAdapter;
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
		
	}
}
