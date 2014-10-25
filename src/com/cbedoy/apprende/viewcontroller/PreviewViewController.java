package com.cbedoy.apprende.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.business.preview.interfaces.IPreviewRepresentationDelegate;
import com.cbedoy.apprende.business.preview.interfaces.IPreviewRepresentationHandler;
import com.cbedoy.apprende.interfaces.IMessageRepresentationHandler;
import com.cbedoy.apprende.service.ImageService;
import com.cbedoy.apprende.service.InjectionManager;
import com.cbedoy.apprende.widgets.LevelSelectorView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Carlos on 14/10/2014.
 */
public class PreviewViewController extends AbstractViewController implements IPreviewRepresentationHandler, LevelSelectorView.ILevelSelectorViewDelegate{

    private View overlay;
    private Button start;
    private Button choseLevel;
    private CircleImageView avatar;
    private TextView category;
    private TextView subcategory;
    private TextView description;
    private ImageView background;
    private HashMap<String, Object> previewInformation;
    private IPreviewRepresentationDelegate previewRepresentationDelegate;
    private LevelSelectorView levelSelectorView;

    public void setLevelSelectorView(LevelSelectorView levelSelectorView) {
        this.levelSelectorView = levelSelectorView;
    }

    public void setPreviewRepresentationDelegate(IPreviewRepresentationDelegate previewRepresentationDelegate) {
        this.previewRepresentationDelegate = previewRepresentationDelegate;
    }

    @Override
    protected View init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.app_preview_viewcontroller,  null);
        navigationBar.initWithView(view);
        previewInformation = new HashMap<String, Object>();
        overlay = view.findViewById(R.id.app_preview_viewcontroller_overlay);
        start = (Button) view.findViewById(R.id.app_preview_viewcontroller_aprende);
        choseLevel = (Button) view.findViewById(R.id.app_preview_viewcontroller_level);
        avatar = (CircleImageView) view.findViewById(R.id.app_preview_viewcontroller_avatar);
        category = (TextView) view.findViewById(R.id.app_preview_viewcontroller_category);
        subcategory = (TextView) view.findViewById(R.id.app_preview_viewcontroller_subcategory);
        description = (TextView) view.findViewById(R.id.app_preview_viewcontroller_detail);
        background = (ImageView) view.findViewById(R.id.app_preview_viewcontroller_background);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(previewInformation.containsKey("level"))
                    previewRepresentationDelegate.userSelectedStartApprendeWithData(previewInformation);
                else
                    messageRepresentation.showCode(IMessageRepresentationHandler.NOTIFICATION_CODE.K_INVALID_COMMON_FIELDS);
            }
        });
        choseLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                levelSelectorView.show();
            }
        });
        return view;
    }

    @Override
    public void reload() {

    }

    @Override
    public void showPreviewWithData(HashMap<String, Object> previewInformation) {
        this.appViewManager.presentViewForTag(this.tag);
        this.previewInformation = previewInformation;
        HashMap<String, Object> category_selected = (HashMap<String, Object>) previewInformation.get("category_selected");
        HashMap<String, Object> subcategory_selected = (HashMap<String, Object>) previewInformation.get("subcategory_selected");
        String name_category = (String) ((HashMap)category_selected.get("fields")).get("name");
        String image_category = (String)((HashMap)category_selected.get("fields")).get("thumbnail");

        String image_subcategory = (String) ((HashMap)subcategory_selected.get("fields")).get("thumbnail");
        String name_subcategory = (String) ((HashMap)subcategory_selected.get("fields")).get("name");
        String description_subcategory = (String) ((HashMap)subcategory_selected.get("fields")).get("description");
        Double popularity = (Double) ((HashMap)subcategory_selected.get("fields")).get("popularity");
        Integer views = (Integer) ((HashMap)subcategory_selected.get("fields")).get("views");

        ImageLoader.getInstance().displayImage(InjectionManager.MEDIA_URL + image_subcategory, avatar);
        ImageLoader.getInstance().displayImage(InjectionManager.MEDIA_URL + image_category, background);
        category.setText(name_category);
        subcategory.setText(name_subcategory);
        description.setText(description_subcategory);

        category.setTypeface(ImageService.regularFont);
        subcategory.setTypeface(ImageService.regularFont);
        description.setTypeface(ImageService.lightFont);
        choseLevel.setTypeface(ImageService.lightFont);
        start.setTypeface(ImageService.lightFont);
        previewInformation.put("theme", subcategory_selected.get("pk"));
    }

    @Override
    public void showBackground(String base64image) {

    }

    @Override
    public void userSelectedLevel(int level) {
        previewInformation.put("level", level);
    }
}
