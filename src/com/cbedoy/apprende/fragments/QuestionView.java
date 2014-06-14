package com.cbedoy.apprende.fragments;

import java.util.ArrayList;

import android.app.Application;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;


import com.cbedoy.apprende.viewcontrollers.StatisticsActivity;
import com.cbedoy.apprende.viewcontrollers.ExamActivity;


public class QuestionView extends Fragment {



	private int cantidad;
	


	public QuestionView() {
		// TODO Auto-generated constructor stub
		
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.cuestionario, container,false);
		

		return rootView;

	}
	
    private void showNewActivity() {
		Intent i = new Intent(getActivity(),StatisticsActivity.class);
		i.putExtra("cantidad", cantidad);
		startActivity(i);
		
	}
}