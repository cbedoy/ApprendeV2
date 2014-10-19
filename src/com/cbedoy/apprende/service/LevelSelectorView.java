package com.cbedoy.apprende.service;

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

/**
 * Created by Carlos on 19/10/2014.
 */
public class LevelSelectorView
{
    private Activity activity;
    private Dialog dialog;
    private View view;
    private TextView title;
    private RadioButton optionOne;
    private RadioButton optionTwo;
    private RadioButton optionThree;
    private RadioGroup radioGroup;
    private Button actionSelected;
    private ILevelSelectorViewDelegate levelSelectorViewDelegate;

    public void setLevelSelectorViewDelegate(ILevelSelectorViewDelegate levelSelectorViewDelegate) {
        this.levelSelectorViewDelegate = levelSelectorViewDelegate;
    }

    public LevelSelectorView(Activity activity){
        this.activity = activity;
    }

    public void show()
    {
        final LevelSelectorView weakSelf = this;
        this.activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if(weakSelf.dialog == null)
                    weakSelf.createDialogView();

                weakSelf.dialog.setContentView(init());
                weakSelf.dialog.setCanceledOnTouchOutside(false);
                weakSelf.dialog.show();
            }
        });
    }

    public void createDialogView()
    {
        this.dialog = new Dialog(this.activity);
        this.dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.dialog.setContentView(init());
        this.dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        this.dialog.getWindow().setWindowAnimations(R.style.notification_animation);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    private View init() {
        if(view == null)
        {
            view = activity.getLayoutInflater().inflate(R.layout.app_level_selector, null);
            title = (TextView) view.findViewById(R.id.level_selector_title);
            actionSelected = (Button) view.findViewById(R.id.level_selector_action);
            optionOne = (RadioButton) view.findViewById(R.id.app_selector_optionOne);
            optionTwo = (RadioButton) view.findViewById(R.id.app_selector_optionTwo);
            optionThree = (RadioButton) view.findViewById(R.id.app_selector_optionThree);
            radioGroup = (RadioGroup) view.findViewById(R.id.level_radio_group);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if(optionOne.isChecked())
                    {
                        levelSelectorViewDelegate.userSelectedLevel(20);
                    }
                    else if(optionTwo.isChecked())
                    {
                        levelSelectorViewDelegate.userSelectedLevel(10);
                    }
                    else if(optionThree.isChecked())
                    {
                        levelSelectorViewDelegate.userSelectedLevel(5);
                    }
                }
            });
        }
        return view;
    }

    public void hide()
    {
        final LevelSelectorView weakSelf = this;
        this.activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if(weakSelf.dialog != null)
                    weakSelf.dialog.dismiss();
            }
        });
    }

    public interface ILevelSelectorViewDelegate{
        public void userSelectedLevel(int level);
    }
}
