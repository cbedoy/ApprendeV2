package com.cbedoy.apprende.service;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cbedoy.apprende.R;


/**
 * Created by admin on 10/28/14.
 */
public abstract class AbstractDialog
{
    protected Activity activity;
    protected View view;
    protected Dialog dialog;

    public AbstractDialog(Activity activity){
        this.activity = activity;
    }

    public void show()
    {
        final AbstractDialog weakSelf = this;
        this.activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if(weakSelf.dialog == null)
                    weakSelf.createDialogView();
                weakSelf.takeCurrentScreamShot();
                weakSelf.dialog.show();
            }
        });
    }

    private void createDialogView()
    {
        this.dialog = new Dialog(this.activity);
        this.dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.dialog.setContentView(init());
        this.dialog.setCanceledOnTouchOutside(true);
        this.dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        this.dialog.getWindow().setWindowAnimations(R.style.notification_animation);
    }

    public void hide()
    {
        final AbstractDialog weakSelf = this;
        this.activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if(weakSelf.dialog != null)
                    weakSelf.dialog.hide();
            }
        });
    }



    private void takeCurrentScreamShot()
    {
        Bitmap bitmap = Utils.takeScreenShot(activity);
        Bitmap bitmap1 = BlurService.getInstance().performRequestBlurByImageWithRadius(bitmap, 25);
        Drawable drawable = new BitmapDrawable(bitmap1);
        this.dialog.getWindow().setBackgroundDrawable(drawable);
    }

    private void takeCurrentScreamShotWithRadius(int radius)
    {
        Bitmap bitmap = Utils.takeScreenShot(activity);
        Bitmap bitmap1 = BlurService.getInstance().performRequestBlurByImageWithRadius(bitmap, radius);
        Drawable drawable = new BitmapDrawable(bitmap1);
        this.dialog.getWindow().setBackgroundDrawable(drawable);
    }

    public abstract View init();
}
