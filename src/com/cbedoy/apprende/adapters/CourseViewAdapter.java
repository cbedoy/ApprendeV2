package com.cbedoy.apprende.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.R.id;
import com.cbedoy.apprende.R.layout;
import com.cbedoy.apprende.interfaces.representationDelegates.ICourseRepresentationDelegate;
import com.cbedoy.apprende.keysets.CourseKeySet;

public class CourseViewAdapter extends BaseAdapter implements ICourseRepresentationDelegate{

	private Context 		context;
	private List<Object> 	dataModel;
	
	public CourseViewAdapter(Context context){
		this.context 	= context;
		this.dataModel 	= new ArrayList<Object>();
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
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		
		if (view == null) {
			
			view = LayoutInflater.from(context).inflate(R.layout.course_view, null, false);
			
		}else{
			
			@SuppressWarnings("unchecked")
			HashMap<CourseKeySet, Object>  information 	= (HashMap<CourseKeySet, Object>) dataModel.get(position);
			TextView   courseName 						= (TextView) view.findViewById(R.id.course_name);
			TextView   courseQuantity					= (TextView) view.findViewById(R.id.course_theme_qunatity);
			courseName.setText(information.get(CourseKeySet.NAME).toString());
			courseQuantity.setText(information.get(CourseKeySet.QUANTITY).toString());
		
		}
		return view;
	}

	@Override
	public void reloadWithData(List<Object> dataModel) {
		this.dataModel = dataModel;
		this.notifyDataSetChanged();
		
	}

}
