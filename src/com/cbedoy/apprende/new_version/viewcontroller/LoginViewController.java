package com.cbedoy.apprende.new_version.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cbedoy.apprende.R;

/**
 * Created by Carlos on 14/10/2014.
 */
public class LoginViewController extends AbstractViewController {

    private EditText username;
    private EditText password;
    private Button actionSingup;
    private Button actionLogin;
    private TextView title;

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
}
