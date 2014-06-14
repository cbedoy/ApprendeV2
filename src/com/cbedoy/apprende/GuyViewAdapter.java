package com.cbedoy.apprende;

import java.util.HashMap;
import java.util.List;

import com.cbedoy.apprende.interfaces.IGuyRepresentationDelegate;
import com.cbedoy.apprende.keysets.UserKeySet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GuyViewAdapter extends BaseAdapter implements IGuyRepresentationDelegate{

	private List<Object> 	dataModel;
	private ImageView		guyPhoto;
	private TextView		guyName;
	private Context 		context;
	
	public GuyViewAdapter(Context context){
		this.context = context;
	}
	
	@Override
	public int getCount() {

		return 0;
	}

	@Override
	public Object getItem(int arg0) {

		return dataModel.size();
	}

	@Override
	public long getItemId(int arg0) {

		return 0;
	}

	@SuppressWarnings("unused")
	@Override
	public View getView(int position, View view, ViewGroup groupView) {		
		if (view == null) {
			
			view = LayoutInflater.from(context).inflate(R.layout.guy_view, null, false);
			
		}else{
			
			@SuppressWarnings("unchecked")
			HashMap<UserKeySet, Object>  information 	= (HashMap<UserKeySet, Object>) dataModel.get(position);
			ImageView   guyImage 						= (ImageView) view.findViewById(R.id.guy_view_image);
			TextView	guyName 						= (TextView) view.findViewById(R.id.guy_view_username);
			guyName.setText(information.get(UserKeySet.USERNAME).toString());
		
		}
		return view;
	}

	@Override
	public void reloadViewWithData(List<Object> dataModel) {
		this.dataModel = dataModel;
		this.notifyDataSetChanged();
	}

}
