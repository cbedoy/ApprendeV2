package com.cbedoy.apprende.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.viewcontroller.LoginViewController;
import com.cbedoy.apprende.viewcontroller.PreviewViewController;
import com.cbedoy.apprende.viewcontroller.ProfileViewController;
import com.cbedoy.apprende.viewcontroller.QuestionViewController;

/**
 * Created by Carlos on 19/10/2014.
 */
public class TestAdapter extends Activity
{
    private ViewPager viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.test___adapter);

        //viewFlipper = (ViewPager) findViewById(R.id.myfivepanelpager);

        QuestionViewController questionViewController1 = new QuestionViewController();
        questionViewController1.setContext(getApplicationContext());

        QuestionViewController questionViewController2 = new QuestionViewController();
        questionViewController2.setContext(getApplicationContext());

        QuestionViewController questionViewController3 = new QuestionViewController();
        questionViewController3.setContext(getApplicationContext());

        QuestionViewController questionViewController4 = new QuestionViewController();
        questionViewController4.setContext(getApplicationContext());

        QuestionViewController questionViewController5 = new QuestionViewController();
        questionViewController5.setContext(getApplicationContext());


        View[] images = {
                questionViewController1.getView(),
                questionViewController2.getView(),
                questionViewController3.getView(),
                questionViewController4.getView(),
                questionViewController5.getView(),

        };
        viewFlipper.setAdapter(new MyAdapter(this, images));


    }


    private class MyAdapter extends PagerAdapter{

        Activity activity;
        View imageArray[];

        public MyAdapter(Activity act, View[] imgArra) {
            imageArray = imgArra;
            activity = act;
        }

        public int getCount() {
            return imageArray.length;
        }

        public Object instantiateItem(View collection, int position) {
            View view = imageArray[position];
            ((ViewPager) collection).addView(view, 0);
            return view;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == ((View) arg1);
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }
}
