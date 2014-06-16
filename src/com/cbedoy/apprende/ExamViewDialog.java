package com.cbedoy.apprende;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;

public class ExamViewDialog {

	
	private Context context;
	private Dialog dialog;
	
	public ExamViewDialog(Context context){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context           = context;
	}
	
}
