package com.cbedoy.apprende.widgets;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.service.AbstractDialog;

/**
 * Created by Carlos on 19/10/2014.
 */
public class LevelSelectorView extends AbstractDialog
{
    private TextView title;
    private RadioButton optionOne;
    private RadioButton optionTwo;
    private RadioButton optionThree;
    private RadioButton optionFour;
    private RadioButton optionFive;
    private RadioGroup radioGroup;
    private Button actionSelected;
    private ILevelSelectorViewDelegate levelSelectorViewDelegate;

    public void setLevelSelectorViewDelegate(ILevelSelectorViewDelegate levelSelectorViewDelegate) {
        this.levelSelectorViewDelegate = levelSelectorViewDelegate;
    }

    public LevelSelectorView(Activity activity){
        super(activity);

    }

    public View init() {
        if(view == null)
        {
            view = activity.getLayoutInflater().inflate(R.layout.app_level_selector, null);
            title = (TextView) view.findViewById(R.id.level_selector_title);
            actionSelected = (Button) view.findViewById(R.id.level_selector_action);
            optionOne = (RadioButton) view.findViewById(R.id.app_selector_optionOne);
            optionTwo = (RadioButton) view.findViewById(R.id.app_selector_optionTwo);
            optionThree = (RadioButton) view.findViewById(R.id.app_selector_optionThree);
            optionFour = (RadioButton) view.findViewById(R.id.app_selector_optionFour);
            optionFive = (RadioButton) view.findViewById(R.id.app_selector_optionFive);
            radioGroup = (RadioGroup) view.findViewById(R.id.level_selector_radio_group);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if(optionOne.isChecked())
                    {
                        levelSelectorViewDelegate.userSelectedLevel(25);
                        hide();
                    }
                    else if(optionTwo.isChecked())
                    {
                        levelSelectorViewDelegate.userSelectedLevel(20);
                        hide();
                    }
                    else if(optionThree.isChecked())
                    {
                        levelSelectorViewDelegate.userSelectedLevel(15);
                        hide();
                    }
                    else if(optionFour.isChecked())
                    {
                        levelSelectorViewDelegate.userSelectedLevel(10);
                        hide();
                    }
                    else  if(optionFive.isChecked())
                    {
                        levelSelectorViewDelegate.userSelectedLevel(5);
                        hide();
                    }
                }
            });
            actionSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hide();
                }
            });
        }
        return view;
    }

    public interface ILevelSelectorViewDelegate{
        public void userSelectedLevel(int level);
    }
}
