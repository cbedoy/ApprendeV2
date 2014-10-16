package com.cbedoy.apprende.new_version;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.new_version.interfaces.IMessageRepresentationHandler;

/**
 * Created by Carlos on 14/10/2014.
 */
public class NotificationMessages implements IMessageRepresentationHandler {

    private Activity activity;
    private Dialog dialog;
    private View view;
    private ImageView notificationIcon;
    private Button notificationAccept;
    private TextView notificationMessage;
    private TextView notificationTitle;
    private TextView notificationAuthor;
    private RelativeLayout notificationBackground;

    public NotificationMessages(Activity activity) {
        this.activity = activity;
    }

    public View init(){
        if(view == null){
            this.view = activity.getLayoutInflater().inflate(R.layout.app_notification_view, null);
            this.notificationIcon = (ImageView) this.view.findViewById(R.id.notification_image);
            this.notificationAccept = (Button) this.view.findViewById(R.id.notification_accept);
            this.notificationMessage = (TextView) this.view.findViewById(R.id.notification_message);
            this.notificationTitle = (TextView) this.view.findViewById(R.id.notification_title);
            this.notificationBackground = (RelativeLayout) this.view.findViewById(R.id.notification_background);
            this.notificationAuthor = (TextView) this.view.findViewById(R.id.notification_background);
            this.notificationAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        return view;
    }

    public void show()
    {
        final NotificationMessages weakSelf = this;
        this.activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if(weakSelf.dialog == null)
                    weakSelf.createDialogView();
                weakSelf.dialog.show();
            }
        });
    }

    public void createDialogView()
    {
        this.dialog = new Dialog(this.activity);
        this.dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.dialog.setContentView(init());
        this.dialog.setCanceledOnTouchOutside(true);
        this.dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        this.dialog.getWindow().setWindowAnimations(R.style.notification_animation);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    public void hide()
    {
        final NotificationMessages weakSelf = this;
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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void hideMessage() {

    }

    @Override
    public void showCode(MESSAGE_REPRESENTATION_CODE code) {
        String title = getTitleFromCode(code);
        String message = getMessageFromCode(code);
        int resource = getResourceFromCode(code);
        int color = getColorFromCode(code);
        showWithData(title, message, resource, color, null);
    }

    @Override
    public void showCodeMessage(MESSAGE_REPRESENTATION_CODE code, String message) {
        String title = getTitleFromCode(code);
        int resource = getResourceFromCode(code);
        int color = getColorFromCode(code);
        showWithData(title, message, resource, color, null);
    }

    @Override
    public void showCodeTitleMessage(MESSAGE_REPRESENTATION_CODE code, String title, String message) {
        int resource = getResourceFromCode(code);
        int color = getColorFromCode(code);
        showWithData(title, message, resource, color, null);
    }

    @Override
    public void showCodeTitleMessageWithCallback(MESSAGE_REPRESENTATION_CODE code, String title, String message, IMessageRepresentationCallback callback) {
        int resource = getResourceFromCode(code);
        int color = getColorFromCode(code);
        showWithData(title, message, resource, color, callback);
    }

    private void showWithData(String title, String message, int resource, int color, final IMessageRepresentationCallback callback){
        this.init();
        this.notificationTitle.setText(title);
        this.notificationMessage.setText(message);
        this.notificationIcon.setImageResource(resource);
        this.notificationBackground.setBackgroundResource(color);
        this.notificationAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callback!=null)
                    callback.run();
            }
        });
        this.show();
    }

    private String getMessageFromCode(MESSAGE_REPRESENTATION_CODE code){
        switch (code) {
            case K_INVALID_LOGIN:
                return "Parece que hay algo mal con tu usuario, intenta de nuevo";
            default:
                return "No description";
        }
    }

    private String getTitleFromCode(MESSAGE_REPRESENTATION_CODE code){
        switch (code) {
            case K_INVALID_LOGIN:
                return "Ooops!";
            default:
                return "Ooops!";
        }
    }

    private int getResourceFromCode(MESSAGE_REPRESENTATION_CODE code){
        switch (code) {
            case K_INVALID_LOGIN:
                return R.drawable.ic_launcher;
            default:
                return R.drawable.ic_launcher;
        }
    }

    private int getColorFromCode(MESSAGE_REPRESENTATION_CODE code){
        switch (code) {
            case K_INVALID_LOGIN:
                return R.color.blue_hight;
            default:
                return R.color.green;
        }
    }
}
