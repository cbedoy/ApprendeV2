package com.cbedoy.apprende.new_version.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbedoy.apprende.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Carlos on 14/10/2014.
 */
public class PreviewViewController extends AbstractViewController {

    private View overlay;
    private Button start;
    private Button choseLevel;
    private CircleImageView avatar;
    private TextView category;
    private TextView subcategory;
    private TextView description;
    private ImageView background;

    @Override
    protected View init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.app_preview_viewcontroller,  null);
        overlay = view.findViewById(R.id.app_preview_viewcontroller_overlay);
        start = (Button) view.findViewById(R.id.app_preview_viewcontroller_aprende);
        choseLevel = (Button) view.findViewById(R.id.app_preview_viewcontroller_level);
        avatar = (CircleImageView) view.findViewById(R.id.app_preview_viewcontroller_avatar);
        category = (TextView) view.findViewById(R.id.app_preview_viewcontroller_category);
        subcategory = (TextView) view.findViewById(R.id.app_preview_viewcontroller_subcategory);
        description = (TextView) view.findViewById(R.id.app_preview_viewcontroller_detail);
        background = (ImageView) view.findViewById(R.id.app_preview_viewcontroller_background);
        return view;
    }

    @Override
    public void reload() {

    }
}
