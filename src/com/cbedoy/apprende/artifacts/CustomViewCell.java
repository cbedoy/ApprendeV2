package com.cbedoy.apprende.artifacts;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.activity.ApplicationLoader;
import com.cbedoy.apprende.service.BlurService;
import com.cbedoy.apprende.service.ImageService;
import com.cbedoy.apprende.service.InjectionManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Carlos on 19/10/2014.
 */
public class CustomViewCell extends BaseAdapter
{
    private List<Object> viewModel;
    private Context context;
    private LayoutInflater layoutInflater;


    public CustomViewCell(Context context, LayoutInflater layoutInflater, List<Object> viewModel)
    {
        this.layoutInflater = layoutInflater;
        this.context = context;
        this.viewModel = viewModel;
    }

    @Override
    public int getCount() {
        return viewModel.size();
    }

    @Override
    public Object getItem(int i) {
        return viewModel.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.app_category_cell, null);
            viewHolder = new ViewHolder();
            viewHolder.imageCell = (ImageView) convertView.findViewById(R.id.app_category_cell_image);
            viewHolder.textCell = (TextView) convertView.findViewById(R.id.app_category_cell_name);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        HashMap<String, Object> currentModel = (HashMap<String, Object>) viewModel.get(position);
        String string = InjectionManager.MEDIA_URL + ((HashMap) (currentModel.get("fields"))).get("thumbnail").toString();
        ImageLoader.getInstance().displayImage(
                string,
                viewHolder.imageCell,
                ApplicationLoader.options,
                new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view, loadedImage);
                        //Bitmap bitmap = BlurService.getInstance().performRequestBlurByImage(loadedImage);
                        //viewHolder.imageCell.setImageBitmap(bitmap);
                    }
                });
        viewHolder.textCell.setText(((HashMap) currentModel.get("fields")).get("name").toString());
        viewHolder.textCell.setTypeface(ImageService.boldFont);
        return convertView;
    }

    class ViewHolder{
        ImageView imageCell;
        TextView textCell;
    }
}
