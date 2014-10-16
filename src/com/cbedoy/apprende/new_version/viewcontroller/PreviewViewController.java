package com.cbedoy.apprende.new_version.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.new_version.business.preview.interfaces.IPreviewRepresentationDelegate;
import com.cbedoy.apprende.new_version.business.preview.interfaces.IPreviewRepresentationHandler;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Carlos on 14/10/2014.
 */
public class PreviewViewController extends AbstractViewController implements IPreviewRepresentationHandler{

    private View overlay;
    private Button start;
    private Button choseLevel;
    private CircleImageView avatar;
    private TextView category;
    private TextView subcategory;
    private TextView description;
    private ImageView background;

    private IPreviewRepresentationDelegate previewRepresentationDelegate;

    public void setPreviewRepresentationDelegate(IPreviewRepresentationDelegate previewRepresentationDelegate) {
        this.previewRepresentationDelegate = previewRepresentationDelegate;
    }

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

    @Override
    public void showPreviewWithData(HashMap<String, Object> previewInformation) {

    }

    @Override
    public void showBackground(String base64image) {

    }
}
