package com.cbedoy.apprende.new_version;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.new_version.interfaces.IActivityResultListener;
import com.cbedoy.apprende.new_version.interfaces.IAppViewManager;
import com.cbedoy.apprende.new_version.viewcontroller.AbstractViewController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Carlos on 14/10/2014.
 */
public class InAppActivity extends Activity implements IAppViewManager{

    public static int AndroidInAppCode = 109506 / 4;

    private ViewFlipper viewFlipper;
    private HashMap<Object, Object> informationService;
    private LinearLayout mainLayout;
    private int view_controller_width;
    private int view_controller_height;
    private HashMap<String, AbstractViewController> viewModel;
    private ArrayList<IActivityResultListener> resultListeners;

    public InAppActivity() {
    }

    @Override
    public void finish() {
        long delay = 200;
        final InAppActivity self = this;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                self.terminateInApp();
            }
        }, delay);
    }

    private void terminateInApp() {
        super.finish();

        this.overridePendingTransition(R.anim.exit_in_anim, R.anim.exit_out_anim);
    }


    @Override
    protected void onPause() {


        super.onPause();
    }

    @Override
    protected void onResume() {


        super.onResume();
    }

    @Override
    public void onBackPressed() {
        boolean allowBack = true;
        int displayed_child = this.viewFlipper.getDisplayedChild();
        View view = this.viewFlipper.getChildAt(displayed_child);

        for(Map.Entry<String, AbstractViewController> entry : this.viewModel.entrySet()) {
            AbstractViewController child = entry.getValue();

            if(child.getView() == view) {
                allowBack = child.onBackPressed();
                break;
            }
        }

        if(allowBack)
            super.onBackPressed();
    }

    private LinearLayout createMainLayout() {
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setBackgroundColor(Color.TRANSPARENT);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mainLayout.setLayoutParams(mainParams);

        this.viewFlipper = this.createViewFlipper();
        mainLayout.addView(this.viewFlipper);

        return mainLayout;
    }

    private ViewFlipper createViewFlipper() {
        ViewFlipper flipper = new ViewFlipper(this);
        flipper.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams flipper_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        flipper_params.gravity = Gravity.CENTER;
        flipper.setLayoutParams(flipper_params);

        TranslateAnimation in = new TranslateAnimation(view_controller_width, 0, 0, 0);
        in.setDuration(1000);
        in.setZAdjustment(Animation.ZORDER_TOP);
        flipper.setInAnimation(in);

        TranslateAnimation out = new TranslateAnimation(0, -view_controller_width, 0, 0);
        out.setDuration(1000);
        out.setZAdjustment(Animation.ZORDER_TOP);
        flipper.setOutAnimation(out);

        return flipper;
    }

    @Override
    public int getViewControllerWidth() {
        return this.view_controller_width;
    }

    @Override
    public int getViewControllerHeight() {
        return this.view_controller_height;
    }

    @Override
    public void reActivateCurrentView() {
        final InAppActivity self = this;

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int displayed_child = self.viewFlipper.getDisplayedChild();
                View view = self.viewFlipper.getChildAt(displayed_child);

                for(Map.Entry<String, AbstractViewController> entry : self.viewModel.entrySet()) {
                    AbstractViewController child = entry.getValue();

                    if(child.getView() == view) {
                        child.toogleButtons(true);
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void presentViewForTag(String tag) {
        final InAppActivity self = this;
        final String final_tag = tag;

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AbstractViewController child = self.viewModel.get(final_tag);
                View view = child.getView();

                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);

                int child_index = self.viewFlipper.indexOfChild(view);
                if(child_index < 0) {
                    self.viewFlipper.addView(view);
                    child_index = self.viewFlipper.indexOfChild(view);
                }

                int displayed_child = self.viewFlipper.getDisplayedChild();
                if(child_index != displayed_child) {
                    int width = self.view_controller_width;
                    int ltr = child_index > displayed_child ? 1 : -1;

                    TranslateAnimation in = new TranslateAnimation(width * ltr, 0, 0, 0);
                    in.setDuration(400);
                    in.setZAdjustment(Animation.ZORDER_TOP);
                    self.viewFlipper.setInAnimation(in);

                    TranslateAnimation out = new TranslateAnimation(0, -width * ltr, 0, 0);
                    out.setDuration(400);
                    out.setZAdjustment(Animation.ZORDER_TOP);
                    self.viewFlipper.setOutAnimation(out);

                    self.viewFlipper.setDisplayedChild(child_index);
                }

                child.reload();
            }
        });
    }

    @Override
    public void finishWithResult(String result) {
        Intent intent = new Intent();
        intent.putExtra("result", result);

        this.setResult(RESULT_OK, intent);
        this.finish();
    }

    @Override
    public void addViewWithTag(AbstractViewController controller, String tag) {
        this.viewModel.put(tag, controller);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void addActivityResultListener(IActivityResultListener listener) {
        this.resultListeners.add(listener);
    }

}
