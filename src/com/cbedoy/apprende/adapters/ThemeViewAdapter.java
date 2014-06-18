package com.cbedoy.apprende.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cbedoy.apprende.ExamViewDialog;
import com.cbedoy.apprende.R;
import com.cbedoy.apprende.R.id;
import com.cbedoy.apprende.R.layout;
import com.cbedoy.apprende.interfaces.representationDelegates.IThemeRepresentationDelegate;
import com.cbedoy.apprende.interfaces.viewdelegates.IThemeCellDelegate;
import com.cbedoy.apprende.keysets.CourseKeySet;
import com.cbedoy.apprende.keysets.ThemeKeySet;
import com.cbedoy.apprende.services.AppInstanceProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ThemeViewAdapter extends BaseAdapter implements IThemeRepresentationDelegate{

	private Context 			context;
	private List<Object> 		dataModel;
	private IThemeCellDelegate 	themeCellDelegate;
	
	public ThemeViewAdapter(Context context){
		this.context 		= context;
		this.dataModel 		= new ArrayList<Object>();
	}
	
	public void setIThemeCellDelegate(IThemeCellDelegate themeCellDelegate){
		this.themeCellDelegate = themeCellDelegate;
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

		if (view == null)
			view = LayoutInflater.from(context).inflate(R.layout.theme_view, null, false);
		if(view != null){
			
			final HashMap<ThemeKeySet, Object>  information 	= (HashMap<ThemeKeySet, Object>) dataModel.get(position);
			TextView   themeName 								= (TextView) view.findViewById(R.id.theme_name);
			TextView   themeDescription							= (TextView) view.findViewById(R.id.theme_description);
			themeName.setText(information.get(ThemeKeySet.NAME).toString());
			themeDescription.setText(information.get(ThemeKeySet.DESCRIPTION).toString());
			themeName.setTypeface(AppInstanceProvider.regularFont);
			themeDescription.setTypeface(AppInstanceProvider.lightFont);
			
			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					showDialogView(information);
				}
			});
		}
		return view;
	}


	protected void showDialogView(Object information) {
		this.themeCellDelegate.userSelectedCell(information);
	}
 
	@Override
	public void reloadWithData(List<Object> dataModel) {
		this.dataModel = dataModel;
		this.notifyDataSetChanged();
		
	}

}
