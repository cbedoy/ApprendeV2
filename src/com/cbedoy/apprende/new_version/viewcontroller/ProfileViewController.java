package com.cbedoy.apprende.new_version.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.new_version.business.profile.interfaces.IProfileRepresentationDelegate;
import com.cbedoy.apprende.new_version.business.profile.interfaces.IProfileRepresentationHandler;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Carlos on 14/10/2014.
 */
public class ProfileViewController extends AbstractViewController implements IProfileRepresentationHandler{
    private CircleImageView userAvatar;
    private ImageView backgroundView;

    private TextView place;
    private TextView username;
    private TextView firstName;
    private TextView lastName;
    private TextView points;
    private TextView email;
    private View overlay;
    private Button actionShare;
    private Button actionStart;

    private IProfileRepresentationDelegate profileRepresentationDelegate;

    public void setProfileRepresentationDelegate(IProfileRepresentationDelegate profileRepresentationDelegate) {
        this.profileRepresentationDelegate = profileRepresentationDelegate;
    }

    @Override
    protected View init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.app_profile_viewcontroller,  null);
        userAvatar = (CircleImageView) view.findViewById(R.id.app_profile_viewcontroller_avatar);
        backgroundView = (ImageView) view.findViewById(R.id.app_profile_viewcontroller_background);
        place = (TextView) view.findViewById(R.id.app_profile_viewcontroller_place);
        username = (TextView) view.findViewById(R.id.app_profile_viewcontroller_username);
        firstName = (TextView) view.findViewById(R.id.app_profile_viewcontroller_first_name);
        lastName = (TextView) view.findViewById(R.id.app_profile_viewcontroller_last_name);
        points = (TextView) view.findViewById(R.id.app_profile_viewcontroller_points);
        email = (TextView) view.findViewById(R.id.app_profile_viewcontroller_email);
        overlay = view.findViewById(R.id.app_profile_viewcontroller_overlay);
        actionShare = (Button) view.findViewById(R.id.app_profile_viewcontroller_share);
        actionStart = (Button) view.findViewById(R.id.app_profile_viewcontroller_start);
        actionShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        actionStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    @Override
    public void reload() {

    }

    @Override
    public void showPreviewViewWithData(HashMap<String, Object> userInformation) {

    }

    @Override
    public void showAvatarView(String avatarBase64) {

    }

    @Override
    public void animateBlurBackground() {

    }
}
