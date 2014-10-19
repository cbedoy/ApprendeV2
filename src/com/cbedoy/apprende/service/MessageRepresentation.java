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
import android.widget.RelativeLayout;
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
    private RelativeLayout notificationContent;
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
            this.notificationContent = (RelativeLayout) this.view.findViewById(R.id.notification_background);
            this.notificationAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hide();
                }
            });
            this.notificationAccept.setTypeface(ImageService.regularFont);
            this.notificationTitle.setTypeface(ImageService.regularFont);
            this.notificationMessage.setTypeface(ImageService.thinFont);
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
                loaderText.setTypeface(ImageService.thinFont);
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

    private void showDialog(String title, String message, int resource, int color, final IMessageRepresentationCallback callback){
        this.isLoading = false;
        this.show();
        this.notificationTitle.setText(title);
        this.notificationMessage.setText(message);
        this.notificationIcon.setImageResource(resource);
        this.notificationAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callback != null)
                    callback.run();
                else
                    hide();
            }
        });
        this.notificationAccept.setBackgroundColor(color);
        this.notificationContent.setBackgroundColor(color);

    }

    @Override
    public void showCode(NOTIFICATION_CODE code) {
        String title = getTitleFromCode(code);
        String message = getMessageFromCode(code);
        int resourceId = getIconFromCode(code);
        int color = getColorFromCode(code);
        showDialog(title, message, resourceId, color, null);

    }

    @Override
    public void showCodeMessage(NOTIFICATION_CODE code, String message) {
        String title = getTitleFromCode(code);
        int resourceId = getIconFromCode(code);
        int color = getColorFromCode(code);
        showDialog(title, message, resourceId, color, null);
    }

    @Override
    public void showCodeTitleMessage(NOTIFICATION_CODE code, String title, String message) {
        int resourceId = getIconFromCode(code);
        int color = getColorFromCode(code);
        showDialog(title, message, resourceId, color, null);
    }

    @Override
    public void showCodeTitleMessageWithCallback(NOTIFICATION_CODE code, String title, String message, IMessageRepresentationCallback callback) {
        int resourceId = getIconFromCode(code);
        int color = getColorFromCode(code);
        showDialog(title, message, resourceId, color, callback);
    }

    private String getTitleFromCode(NOTIFICATION_CODE code){
        switch (code) {
            case K_INVALID_LOGIN:
                return "Ooops!";
            case K_INVALID_COMMON_FIELDS:
                return "Ooops!";
            case K_INVALID_EMAIL:
                return "Ooops!";
            case K_INVALID_PASSWORD_FORMAT:
                return "Ooops!";
            case K_NETWORK_INTERNET:
                return "Ooops!";
            case K_SINGUP_DATA:
                return "Ooops!";
            default:
                return "XXX";
        }
    }

    private String getMessageFromCode(NOTIFICATION_CODE code){
        switch (code) {
            case K_INVALID_LOGIN:
                return "Invalid login";
            case K_INVALID_COMMON_FIELDS:
                return "The common fields are invalid";
            case K_INVALID_EMAIL:
                return "The format by email is invalid";
            case K_INVALID_PASSWORD_FORMAT:
                return "The format by password is invalid";
            case K_NETWORK_INTERNET:
                return "Network issues";
            case K_SINGUP_DATA:
                return "Great! You has registred on Apprende, let go to learn";
            default:
                return "XXX";
        }
    }

    private int getIconFromCode(NOTIFICATION_CODE code){
        switch (code) {
            case K_INVALID_LOGIN:
                return R.drawable.ic_launcher;
            case K_INVALID_COMMON_FIELDS:
                return R.drawable.ic_launcher;
            case K_INVALID_EMAIL:
                return R.drawable.ic_launcher;
            case K_INVALID_PASSWORD_FORMAT:
                return R.drawable.ic_launcher;
            case K_NETWORK_INTERNET:
                return R.drawable.ic_launcher;
            case K_SINGUP_DATA:
                return R.drawable.ic_launcher;
            default:
                return R.drawable.ic_launcher;
        }
    }

    private int getColorFromCode(NOTIFICATION_CODE code){
        switch (code) {
            case K_INVALID_LOGIN:
                return activity.getResources().getColor(R.color.rank1);
            case K_INVALID_COMMON_FIELDS:
                return activity.getResources().getColor(R.color.rank2);
            case K_INVALID_EMAIL:
                return activity.getResources().getColor(R.color.rank3);
            case K_INVALID_PASSWORD_FORMAT:
                return activity.getResources().getColor(R.color.rank4);
            case K_NETWORK_INTERNET:
                return activity.getResources().getColor(R.color.rank5);
            case K_SINGUP_DATA:
                return activity.getResources().getColor(R.color.rank4);
            default:
                return activity.getResources().getColor(R.color.rank2);
        }
    }

}
