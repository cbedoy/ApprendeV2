package com.cbedoy.apprende;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;

import com.cbedoy.apprende.adapters.CourseViewAdapter;
import com.cbedoy.apprende.adapters.ThemeViewAdapter;
import com.cbedoy.apprende.bussiness.MasterController;
import com.cbedoy.apprende.interfaces.viewdelegates.ICourseViewDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.IThemeCellDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.IThemeViewDelegate;
import com.cbedoy.apprende.keysets.CourseKeySet;
import com.cbedoy.apprende.keysets.ServiceKeySet;
import com.cbedoy.apprende.keysets.ThemeKeySet;
import com.cbedoy.apprende.services.AppInstanceProvider;

public class ThemeView extends Activity implements IThemeViewDelegate, ICourseViewDelegate, IThemeCellDelegate{

	private ListView 			themeList;
	private ListView 			courseList;
	private ThemeViewAdapter 	themeViewAdapter;
	private CourseViewAdapter 	courseViewAdapter;
	private MasterController	masterController;
	private List<Object> 		informationThemeAdapter;
	private List<Object> 		informationCourseAdapter;
	private DrawerLayout 		drawerLayout;
	private CharSequence 		levels[];
	private AlertDialog.Builder builder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_theme_view);
		this.courseViewAdapter 			= new CourseViewAdapter(this);
		this.themeViewAdapter 			= new ThemeViewAdapter(this);
		this.themeList 					= (ListView)findViewById(R.id.theme_listView);
		this.courseList	 				= (ListView)findViewById(R.id.course_view_list);
		this.drawerLayout 				= (DrawerLayout) findViewById(R.id.drawer_layout);
		this.informationCourseAdapter	= new ArrayList<Object>();
		this.informationThemeAdapter	= new ArrayList<Object>();
		this.informationCourseAdapter.clear();
		this.informationThemeAdapter.clear();
		this.levels 					= new CharSequence[] {	"(Easy)   Five questions", 
																"(Medium) Ten questions", 
																"(Hard)   Twenty questions", 
																"(Random) Twenty questions"
															};
		this.themeList.setAdapter(themeViewAdapter);
		this.courseList.setAdapter(courseViewAdapter);
		this.themeViewAdapter.setIThemeCellDelegate(this);
		this.masterController = AppInstanceProvider.getInstance().instanceServiceCourse(this, ServiceKeySet.GET_COURSE);
		this.masterController.getAnsycTask().execute();
		this.masterController = AppInstanceProvider.getInstance().instanceServiceTheme(this, ServiceKeySet.GET_THEME);
		this.masterController.getAnsycTask().execute();
		
	}
	

	@Override
	public void reloadDataWithCourse(JSONArray response) {
		this.informationCourseAdapter.clear();
		try {
			if(response!=null){
				for(int index = 0; index < response.length(); index++){
					JSONObject data 					= response.getJSONObject(index);
					JSONObject fields					= data.getJSONObject("fields");
					HashMap<Object, Object> information = new HashMap<Object, Object>();
					information.put(CourseKeySet.ID, 	data.get("pk"));
					information.put(CourseKeySet.NAME, 	fields.get("name"));
					this.informationCourseAdapter.add(information);
				}
				this.courseViewAdapter.reloadWithData(informationCourseAdapter);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void reloadDataWithTheme(JSONArray response) {
		this.informationThemeAdapter.clear();
		try {
			if(response!=null){
				for(int index = 0; index < response.length(); index++){
					JSONObject data 					= response.getJSONObject(index);
					JSONObject fields					= data.getJSONObject("fields");
					HashMap<Object, Object> information = new HashMap<Object, Object>();
					information.put(ThemeKeySet.ID, 			data.get("pk"));
					information.put(ThemeKeySet.NAME, 			fields.get("name"));
					information.put(ThemeKeySet.DESCRIPTION, 	fields.get("description"));
					information.put(ThemeKeySet.ID_COURSE, 		fields.get("course"));
					this.informationThemeAdapter.add(information);
				}
				this.themeViewAdapter.reloadWithData(informationThemeAdapter);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			finish();
		}
	}


	@Override
	public void userSelectedCell(final Object object) {
		if(builder==null)
			builder = new AlertDialog.Builder(this);
		builder.setTitle("Pick a level");
		builder.setItems(levels, new DialogInterface.OnClickListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void onClick(DialogInterface dialog, int position) {
				int level = position==0?5:position==1?10:position==2?20:0;
				requestView((HashMap<Object, Object>) object, level);
			}
		});
		builder.show();
	}
	
	public void requestView(HashMap<Object, Object> information, int level){
		Intent intent = new Intent(ThemeView.this, QuestionaryView.class);
		intent.putExtra("theme", information.get(ThemeKeySet.ID).toString());
		intent.putExtra("level", level+"");
		intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
}
