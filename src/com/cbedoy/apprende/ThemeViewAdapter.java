package com.cbedoy.apprende;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cbedoy.apprende.interfaces.IThemeRepresentationDelegate;
import com.cbedoy.apprende.keysets.CourseKeySet;
import com.cbedoy.apprende.keysets.ThemeKeySet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ThemeViewAdapter extends BaseAdapter implements IThemeRepresentationDelegate{

	private Context context;
	private List<Object> dataModel;
	
	public ThemeViewAdapter(Context context){
		this.context 		= context;
		this.dataModel 		= new ArrayList<Object>();
	}
	
	
	@Override
	public int getCount() {

		return dataModel.size();
	}

	@Override
	public Object getItem(int position) {
		
		return dataModel.get(position);
		
	}

	@Override
	public long getItemId(int position) {
		
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		if (view == null) {
			
			view = LayoutInflater.from(context).inflate(R.layout.course_view, null, false);
			
		}else{
			
			@SuppressWarnings("unchecked")
			HashMap<ThemeKeySet, Object>  information 	= (HashMap<ThemeKeySet, Object>) dataModel.get(position);
			TextView   themeName 						= (TextView) view.findViewById(R.id.theme_name);
			TextView   themeDescription					= (TextView) view.findViewById(R.id.theme_description);
			themeName.setText(information.get(ThemeKeySet.NAME).toString());
			themeDescription.setText(information.get(ThemeKeySet.DESCRIPTION).toString());
		
		}
		return view;
	}


	@Override
	public void reloadWithData(List<Object> dataModel) {
		this.dataModel = dataModel;
		this.notifyDataSetChanged();
		
	}

}