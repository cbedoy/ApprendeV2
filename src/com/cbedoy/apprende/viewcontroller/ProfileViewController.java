package com.cbedoy.apprende.viewcontroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.business.profile.interfaces.IProfileRepresentationDelegate;
import com.cbedoy.apprende.business.profile.interfaces.IProfileRepresentationHandler;
import com.cbedoy.apprende.service.BlurService;
import com.cbedoy.apprende.service.ImageService;
import com.cbedoy.apprende.service.TwitterService;

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
    private HashMap<String, Object> userInformation;

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
                TwitterService.getInstance().postTweetWithInfo(userInformation);
            }
        });
        actionStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileRepresentationDelegate.userSelectedStartToLearn();
            }
        });
        return view;
    }

    @Override
    public void reload() {

    }

    @Override
    public void showProfileViewWithData(HashMap<String, Object> userInformation) {
        this.appViewManager.presentViewForTag(this.tag);
        this.userInformation = userInformation;
        this.username.setText(userInformation.get("username").toString());
        this.firstName.setText(userInformation.get("first_name").toString());
        this.lastName.setText(userInformation.get("last_name").toString());
        this.points.setText(userInformation.get("points")+" points");
        this.place.setText(userInformation.get("plays")+" th Place");
        Bitmap user_avatar_image =  BlurService.getInstance().performRequestByImage(userInformation.get("thumbnail").toString());
        Bitmap user_avatar_blur = BlurService.getInstance().blurRenderScript(user_avatar_image, context);
        this.userAvatar.setImageBitmap(user_avatar_image);
        this.backgroundView.setImageBitmap(user_avatar_blur);
    }

    @Override
    public void showAvatarView(String avatarBase64) {

    }

    @Override
    public void animateBlurBackground() {

    }
}
