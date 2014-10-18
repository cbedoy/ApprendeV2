package com.cbedoy.apprende.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.business.login.interfaces.ILoginRepresentationDelegate;
import com.cbedoy.apprende.business.login.interfaces.ILoginRepresentationHandler;

/**
 * Created by Carlos on 14/10/2014.
 */
public class LoginViewController extends AbstractViewController implements ILoginRepresentationHandler{

    private EditText username;
    private EditText password;
    private Button actionSingup;
    private Button actionLogin;
    private TextView title;

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
        return view;
    }

    @Override
    public void reload() {

    }

    @Override
    public void showLoginView() {

    }

    @Override
    public void cleanLoginViewFields() {

    }

    @Override
    public void animateEasterEgg() {

    }
}
