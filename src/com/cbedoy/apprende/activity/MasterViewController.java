package com.cbedoy.apprende.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.business.timeout.interfaces.ITimeOutTransactionDelegate;
import com.cbedoy.apprende.interfaces.IActivityResultListener;
import com.cbedoy.apprende.interfaces.IAppViewManager;
import com.cbedoy.apprende.interfaces.IMessageRepresentationHandler;
import com.cbedoy.apprende.service.ImageService;
import com.cbedoy.apprende.viewcontroller.abstracts.AbstractViewController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Carlos on 14/10/2014.
 */
public  class MasterViewController extends Activity implements IAppViewManager{

    public static int AndroidInAppCode = 109506 / 4;

    private ITimeOutTransactionDelegate timeOutTransactionDelegate;
    private IMessageRepresentationHandler messageRepresentationHandler;
    private ViewFlipper viewFlipper;
    private SlidingPaneLayout slidingPaneLayout;
    private LinearLayout leftMenu;
    private HashMap<Object, Object> informationService;
    private LinearLayout mainLayout;
    private int view_controller_width;
    private int view_controller_height;
    private HashMap<AbstractViewController.CONTROLLER, AbstractViewController> viewModel;
    private ArrayList<IActivityResultListener> resultListeners;

    public void setTimeOutTransactionDelegate(ITimeOutTransactionDelegate timeOutTransactionDelegate) {
        this.timeOutTransactionDelegate = timeOutTransactionDelegate;
    }

    public void setMessageRepresentationHandler(IMessageRepresentationHandler messageRepresentationHandler) {
        this.messageRepresentationHandler = messageRepresentationHandler;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getActionBar().hide();
        ImageService.init(this);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.viewModel = new HashMap<AbstractViewController.CONTROLLER, AbstractViewController>();
        this.resultListeners = new ArrayList<IActivityResultListener>();
        this.view_controller_height = ImageService.getScreenHeight();
        this.view_controller_width = ImageService.getScreenWidth();
        this.setContentView(R.layout.master_view_controller);

        this.viewFlipper = (ViewFlipper) findViewById(R.id.main_view_controller_view_flipper);
        this.slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.slidingPane);
        this.slidingPaneLayout.setCoveredFadeColor(Color.parseColor("#00000000"));
        this.slidingPaneLayout.setSliderFadeColor(Color.parseColor("#00000000"));
        this.leftMenu = (LinearLayout) findViewById(R.id.main_view_controller_left_menu);

        TranslateAnimation in = new TranslateAnimation(ImageService.getScreenWidth(), 0, 0, 0);
        in.setDuration(3000);
        in.setZAdjustment(Animation.ZORDER_TOP);
        this.viewFlipper.setInAnimation(in);
        TranslateAnimation out = new TranslateAnimation(0, -ImageService.getScreenWidth(), 0, 0);
        out.setDuration(3000);
        out.setZAdjustment(Animation.ZORDER_TOP);
        this.viewFlipper.setOutAnimation(out);
        this.overridePendingTransition(R.anim.enter_in_anim, R.anim.enter_out_anim);
    }





    @Override
    public void finish() {
        long delay = 200;
        final MasterViewController self = this;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.timeOutTransactionDelegate.onResume();
        if(this.timeOutTransactionDelegate.hasStarted()) {
            for(IActivityResultListener listener : this.resultListeners) {
                int code = listener.getRequestCode();
                if(code == requestCode) {
                    listener.onActivityResult(resultCode, data);
                    break;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        this.timeOutTransactionDelegate.onPause();

        super.onPause();
    }

    @Override
    protected void onResume() {
        this.timeOutTransactionDelegate.onResume();

        super.onResume();
    }

    @Override
    public void onBackPressed() {
        boolean allowBack = true;
        int displayed_child = this.viewFlipper.getDisplayedChild();
        View view = this.viewFlipper.getChildAt(displayed_child);
        for(Map.Entry<AbstractViewController.CONTROLLER, AbstractViewController> entry : this.viewModel.entrySet()) {
            AbstractViewController child = entry.getValue();

            if(child.getView() == view) {
                allowBack = child.onBackPressed();
                break;
            }
        }

        if(allowBack)
            super.onBackPressed();
    }


    @Override
    public void presentViewForTag(AbstractViewController.CONTROLLER tag) {
        final MasterViewController self = this;
        final AbstractViewController.CONTROLLER final_tag = tag;

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AbstractViewController child = self.viewModel.get(final_tag);
                View view = child.getView();

                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);

                int child_index = self.viewFlipper.indexOfChild(view);
                if (child_index < 0) {
                    self.viewFlipper.addView(view);
                    child_index = self.viewFlipper.indexOfChild(view);
                }

                int displayed_child = self.viewFlipper.getDisplayedChild();
                if (child_index != displayed_child) {
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
    public void addViewWithTag(AbstractViewController controller, AbstractViewController.CONTROLLER tag) {
        this.viewModel.put(tag, controller);
    }

    @Override
    public void setLeftMenuView(AbstractViewController controller, AbstractViewController.CONTROLLER tag) {
        this.leftMenu.addView(controller.getView());
    }

    @Override
    public void presentLeftMenu() {
        if(slidingPaneLayout!=null){
            if(slidingPaneLayout.isOpen())
                slidingPaneLayout.closePane();
            else
                slidingPaneLayout.openPane();
        }
    }

    @Override
    public void statusByLeftMenu(boolean status) {
        View childAt = this.leftMenu.getChildAt(0);
        childAt.setVisibility(status ? View.VISIBLE : View.GONE);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

}
