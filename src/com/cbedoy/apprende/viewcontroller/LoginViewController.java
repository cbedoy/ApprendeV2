package com.cbedoy.apprende.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.activity.ApplicationLoader;
import com.cbedoy.apprende.business.login.interfaces.ILoginRepresentationDelegate;
import com.cbedoy.apprende.business.login.interfaces.ILoginRepresentationHandler;
import com.cbedoy.apprende.service.ImageService;

/**
 * Created by Carlos on 14/10/2014.
 */
public class LoginViewController extends AbstractViewController implements ILoginRepresentationHandler{

    private EditText username;
    private EditText password;
    private Button actionSingup;
    private Button actionLogin;
    private TextView title;
    private TextView version;
    private ILoginRepresentationDelegate loginRepresentationDelegate;

    public void setLoginRepresentationDelegate(ILoginRepresentationDelegate loginRepresentationDelegate) {
        this.loginRepresentationDelegate = loginRepresentationDelegate;
    }

    @Override
    protected View init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.app_login_viewcontroller,  null);
        username = (EditText) view.findViewById(R.id.app_login_viewcontroller_username);
        password = (EditText) view.findViewById(R.id.app_login_viewcontroller_password);
        actionLogin = (Button) view.findViewById(R.id.app_login_viewcontroller_login);
        actionSingup = (Button) view.findViewById(R.id.app_login_viewcontroller_singup);
        title = (TextView) view.findViewById(R.id.app_login_viewcontroller_tittle);
        version = (TextView) view.findViewById(R.id.currentVersion);
        actionSingup.setTypeface(ImageService.regularFont);
        actionLogin.setTypeface(ImageService.regularFont);
        title.setTypeface(ImageService.thinFont);
        username.setTypeface(ImageService.thinFont);
        password.setTypeface(ImageService.thinFont);
        actionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _username = username.getText().toString();
                String _password = password.getText().toString();
                loginRepresentationDelegate.loginWithData(_username, _password);
            }
        });
        actionSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginRepresentationDelegate.userNeedSingup();
            }
        });
        version.setText("Current Version "+ApplicationLoader.getAppVersion());
        username.setText("carlosbedoy");
        password.setText("nomeacuerdo");
        return view;
    }

    @Override
    public void reload() {

    }

    @Override
    public void showLoginView() {
        this.appViewManager.presentViewForTag(this.tag);
        this.appViewManager.statusByLeftMenu(false);
    }

    @Override
    public void cleanLoginViewFields() {
        this.username.setText(null);
        this.password.setText(null);
    }

    @Override
    public void animateEasterEgg()
    {

    }
}
