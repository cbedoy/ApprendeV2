package com.cbedoy.apprende.viewcontroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.business.singup.interfaces.ISingupRepresentationDelegate;
import com.cbedoy.apprende.business.singup.interfaces.ISingupRepresentationHandler;
import com.cbedoy.apprende.interfaces.ICameraInformationDelegate;
import com.cbedoy.apprende.interfaces.ICameraInformationHandler;
import com.cbedoy.apprende.service.BlurService;
import com.cbedoy.apprende.widgets.NavigationBar;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Carlos on 14/10/2014.
 */
public class SignUpViewController extends AbstractViewController implements ISingupRepresentationHandler, ICameraInformationDelegate, NavigationBar.INavigationBarDelegate
{
    private CircleImageView circleImageView;
    private ImageView backgroundImage;
    private Button actionConfirm;
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private EditText firstName;
    private EditText lastName;
    private EditText facebook;
    private EditText twitter;
    private EditText age;

    private ISingupRepresentationDelegate singupRepresentationDelegate;
    private ICameraInformationHandler cameraInformationHandler;

    public void setSingupRepresentationDelegate(ISingupRepresentationDelegate singupRepresentationDelegate) {
        this.singupRepresentationDelegate = singupRepresentationDelegate;
    }

    public void setCameraInformationHandler(ICameraInformationHandler cameraInformationHandler) {
        this.cameraInformationHandler = cameraInformationHandler;
    }

    @Override
    protected View init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.app_singup_viewcontroller,  null);
        navigationBar.initWithView(view);
        navigationBar.setNavigationBarDelegate(this);
        circleImageView = (CircleImageView) view.findViewById(R.id.app_singup_viewcontroller_avatar);
        actionConfirm = (Button) view.findViewById(R.id.app_singup_viewcontroller_confirm);
        username = (EditText) view.findViewById(R.id.app_singup_viewcontroller_username);
        email = (EditText) view.findViewById(R.id.app_singup_viewcontroller_email);
        password = (EditText) view.findViewById(R.id.app_singup_viewcontroller_password);
        confirmPassword = (EditText) view.findViewById(R.id.app_singup_viewcontroller_confirm_password);
        firstName = (EditText) view.findViewById(R.id.app_singup_viewcontroller_first_name);
        lastName = (EditText) view.findViewById(R.id.app_singup_viewcontroller_last_name);
        facebook = (EditText) view.findViewById(R.id.app_singup_viewcontroller_facebook);
        twitter = (EditText) view.findViewById(R.id.app_singup_viewcontroller_twitter);
        age = (EditText) view.findViewById(R.id.app_singup_viewcontroller_age);
        backgroundImage = (ImageView) view.findViewById(R.id.app_singup_viewcontroller_background);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraInformationHandler.userWantsShowCamera();
            }
        });
        actionConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _username =username.getText().toString();
                String _email = username.getText().toString();
                String _password = password.getText().toString();
                String _confirmPassword = confirmPassword.getText().toString();
                String _firstName = firstName.getText().toString();
                String _lastName = lastName.getText().toString();
                String _facebook = facebook.getText().toString();
                String _twitter = twitter.getText().toString();
                Integer _age = Integer.parseInt(age.getText().toString());

                HashMap<String, Object> currentInformation = new HashMap<String, Object>();
                currentInformation.put("username", _username);
                currentInformation.put("email", _email);
                currentInformation.put("password", _password);
                currentInformation.put("confirm_password", _confirmPassword);
                currentInformation.put("first_name", _firstName);
                currentInformation.put("last_name", _lastName);
                currentInformation.put("facebook", _facebook);
                currentInformation.put("twitter", _twitter);
                currentInformation.put("age", _age);
                singupRepresentationDelegate.singupWithData(currentInformation);
            }
        });
        return view;
    }

    @Override
    public void reload() {

    }

    @Override
    public void showSingupView() {
        this.appViewManager.presentViewForTag(this.tag);
    }

    @Override
    public void clearInputs() {
        username.setText(null);
        email.setText(null);
        lastName.setText(null);
        firstName.setText(null);
        age.setText(null);
        facebook.setText(null);
        twitter.setText(null);
        password.setText(null);
        confirmPassword.setText(null);
    }

    @Override
    public void setPreviusImage(Bitmap bitmap) {
        Bitmap blur_bitmap = BlurService.getInstance().blurRenderScript(bitmap);
        circleImageView.setImageBitmap(bitmap);
        backgroundImage.setImageBitmap(blur_bitmap);

    }

    @Override
    public void showPreviewViewController() {
        this.appViewManager.presentViewForTag(CONTROLLER.LOGIN);
    }
}
