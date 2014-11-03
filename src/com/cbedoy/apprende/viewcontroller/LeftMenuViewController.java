package com.cbedoy.apprende.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.business.feed.interfaces.IFeedRepresentationDelegate;
import com.cbedoy.apprende.business.feed.interfaces.IFeedRepresentationHandler;
import com.cbedoy.apprende.interfaces.IMementoHandler;
import com.cbedoy.apprende.service.TwitterService;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Carlos on 03/11/2014.
 */
public class LeftMenuViewController extends AbstractViewController implements IFeedRepresentationHandler
{
    private TextView titleView;
    private ListView listView;
    private Button actionView;
    private IFeedRepresentationDelegate feedRepresentationDelegate;
    private List<HashMap<String, Object>> dataModel;
    private IMementoHandler mementoHandler;

    public void setMementoHandler(IMementoHandler mementoHandler) {
        this.mementoHandler = mementoHandler;
    }

    public void setFeedRepresentationDelegate(IFeedRepresentationDelegate feedRepresentationDelegate) {
        this.feedRepresentationDelegate = feedRepresentationDelegate;
    }

    @Override
    protected View init()
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.app_left_menu,  null);
        titleView = (TextView) view.findViewById(R.id.app_left_menu_title);
        listView = (ListView) view.findViewById(R.id.app_left_list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                if(feedRepresentationDelegate!=null)
                    feedRepresentationDelegate.userSelectedFeedback(dataModel.get(index));
            }
        });
        actionView = (Button) view.findViewById(R.id.app_left_share_with_twitter);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TwitterService.getInstance().postTweetWithInfo(null);
            }
        });
        return view;
    }

    @Override
    public void reload() {

    }

    @Override
    public void showFeedbackWithData(HashMap<String, Object> data) {

    }
}
