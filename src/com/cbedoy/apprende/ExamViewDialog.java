package com.cbedoy.apprende;

import com.cbedoy.apprende.services.AppInstanceProvider;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class ExamViewDialog {
	
	private Context 	context;
	private Dialog 		dialog;
	private View 		view;
	private TextView	title;
	private RadioButton radioEasy;
	private RadioButton radioMedium;
	private RadioButton radioHard;
	private Button		confirmButton;
	
	public ExamViewDialog(Context context){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context           	= context;
        this.view				= inflater.inflate(R.layout.exam_dialog_view, null);
        this.radioEasy			= (RadioButton) this.view.findViewById(R.id.dialog_level_easy);
        this.radioMedium		= (RadioButton) this.view.findViewById(R.id.dialog_level_medium);
        this.radioHard			= (RadioButton) this.view.findViewById(R.id.dialog_level_hard);
        this.confirmButton		= (Button) this.view.findViewById(R.id.dialog_action);
        this.title				= (TextView) this.view.findViewById(R.id.dialog_text);
        this.radioEasy.setTypeface(AppInstanceProvider.lightFont);
        this.radioHard.setTypeface(AppInstanceProvider.lightFont);
        this.radioMedium.setTypeface(AppInstanceProvider.lightFont);
        this.confirmButton.setTypeface(AppInstanceProvider.regularFont);
        this.title.setTypeface(AppInstanceProvider.regularFont);
        this.confirmButton.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
		
			}
		});
        
	}
	
    public void show()
    {
        final ExamViewDialog weakSelf = this;
        if(weakSelf.dialog == null)
        	weakSelf.createDialogView();
        weakSelf.dialog.show();
    }

    public void createDialogView()
    {
        this.dialog = new Dialog(this.context);
        this.dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.dialog.setContentView(this.view);
        this.dialog.setCanceledOnTouchOutside(true);
        this.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    public void hide()
    {
        final ExamViewDialog weakSelf = this;
        if(weakSelf.dialog != null)
        	weakSelf.dialog.dismiss();
    }
}
