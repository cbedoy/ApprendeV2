package com.cbedoy.apprende.service;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.interfaces.IMessageRepresentationHandler;

public class MessageRepresentation implements IMessageRepresentationHandler {

    private Activity activity;
    private Dialog dialog;
    private View view;
    private ImageView notificationIcon;
    private TextView notificationMessage;
    private Button notificationAccept;
    private Button notificationCancel;
    private TextView notificationTitle;

    private View viewLoading;
    private TextView loaderText;
    private ImageView loaderImage;

    public MessageRepresentation(Activity activity)
    {
        this.activity = activity;
    }

    private boolean isLoading;

    public View init(){
        if(view == null){
            this.view = activity.getLayoutInflater().inflate(R.layout.app_notification_view, null);
            this.notificationIcon = (ImageView) this.view.findViewById(R.id.notification_image);
            this.notificationAccept = (Button) this.view.findViewById(R.id.notification_accept);
            this.notificationMessage = (TextView) this.view.findViewById(R.id.notification_message);
            this.notificationTitle = (TextView) this.view.findViewById(R.id.notification_title);
            this.notificationCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hide();
                }
            });
            this.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hide();
                }
            });
        }
        return view;
    }


    public View initLoading()
    {
        if(this.viewLoading == null)
        {
            this.viewLoading = activity.getLayoutInflater().inflate(R.layout.loader, null);
            loaderImage = (ImageView)viewLoading.findViewById(R.id.notification_view_icon);
            loaderText = (TextView)viewLoading.findViewById(R.id.txtLoading);
        }
        return viewLoading;
    }

    @Override
    public void showLoading() {
        this.isLoading = true;
        initLoading();

        this.show();
    }

    @Override
    public void hideLoading() {
        final MessageRepresentation weakSelf = this;

        loaderImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_launcher));
        loaderText.setText(activity.getString(R.string.success));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                weakSelf.dialog.dismiss();
                loaderImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_launcher));
                loaderText.setText(activity.getString(R.string.loading));
            }
        }, 400);
    }

    @Override
    public void hideMessage() {

    }

    public void show()
    {
        final MessageRepresentation weakSelf = this;
        this.activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if(weakSelf.dialog == null)
                    weakSelf.createDialogView();

                weakSelf.dialog.setContentView(isLoading ? initLoading() : init());
                weakSelf.dialog.setCanceledOnTouchOutside(isLoading ? false : true);
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

    public void hide()
    {
        final MessageRepresentation weakSelf = this;
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


    @Override
    public void showCode(NOTIFICATION_CODE code) {

    }

    @Override
    public void showCodeMessage(NOTIFICATION_CODE code, String message) {

    }

    @Override
    public void showCodeTitleMessage(NOTIFICATION_CODE code, String title, String message) {

    }

    @Override
    public void showCodeTitleMessageWithCallback(NOTIFICATION_CODE code, String title, String message, IMessageRepresentationCallback callback) {

    }

}
