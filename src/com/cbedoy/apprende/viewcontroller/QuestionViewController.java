package com.cbedoy.apprende.viewcontroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.artifacts.QuestionViewPager;
import com.cbedoy.apprende.business.question.interfaces.IQuestionRepresentationDelegate;
import com.cbedoy.apprende.business.question.interfaces.IQuestionRepresentationHandler;
import com.cbedoy.apprende.interfaces.IQuestionViewRepresentationDelegate;
import com.cbedoy.apprende.service.BlurService;
import com.cbedoy.apprende.service.LevelProviderService;
import com.cbedoy.apprende.service.Memento;
import com.cbedoy.apprende.service.MementoHandler;
import com.cbedoy.apprende.viewcontroller.abstracts.AbstractViewController;
import com.cbedoy.apprende.widgets.QuestionView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Carlos on 17/10/2014.
 */
public class QuestionViewController extends AbstractViewController implements IQuestionRepresentationHandler, IQuestionViewRepresentationDelegate
{
    private IQuestionRepresentationDelegate questionRepresentationDelegate;
    private QuestionViewPager questionViewPager;
    private ViewPager viewPager;
    private MementoHandler mementoHandler;
    private List<QuestionView> questionViewModel;
    private List<Object> questionDataModel;
    private ImageView background;

    public void setQuestionRepresentationDelegate(IQuestionRepresentationDelegate questionRepresentationDelegate) {
        this.questionRepresentationDelegate = questionRepresentationDelegate;
    }

    public void setMementoHandler(MementoHandler mementoHandler) {
        this.mementoHandler = mementoHandler;
    }

    @Override
    protected View init()
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = inflater.inflate(R.layout.app_aprende_viewcontroller,  null);
        this.background = (ImageView) view.findViewById(R.id.background_apprende);
        this.viewPager = (ViewPager) view.findViewById(R.id.pager);
        this.questionDataModel = new ArrayList<Object>();
        this.questionViewModel = new ArrayList<QuestionView>();
        return view;
    }

    @Override
    public void reload() {

    }

    @Override
    public void showQuestionary()
    {
        this.appViewManager.presentViewForTag(this.tag);
        this.appViewManager.statusByLeftMenu(false);
        this.questionDataModel.clear();
        this.questionViewModel.clear();
        Memento topMemento = mementoHandler.getTopMemento();
        HashMap<String, Object> mementoData = topMemento.getMementoData();
        HashMap<String, Object> subcategory_selected = (HashMap<String, Object>) mementoData.get("subcategory_selected");
        HashMap<String, Object> exam_response = (HashMap<String, Object>) mementoData.get("exam_response");

        HashMap<String, Object> fields = (HashMap<String, Object>) subcategory_selected.get("fields");
        String thumbnail = fields.get("thumbnail").toString();
        Bitmap user_avatar_blur = BlurService.generateBackgroundBlur(thumbnail);
        background.setImageBitmap(user_avatar_blur);
        for(Object key : exam_response.keySet())
        {
            QuestionView questionView = new QuestionView(context);
            questionDataModel.add(exam_response.get(key));
            questionViewModel.add(questionView);
            questionView.setQuestionViewRepresentationDelegate(this);
        }
        this.questionViewPager = new QuestionViewPager(context,questionDataModel, questionViewModel);
        this.viewPager.setAdapter(questionViewPager);
    }

    @Override
    public void showFeedback()
    {
        for(QuestionView questionView : questionViewModel)
        {
            questionView.discoverFeedBack();
            questionView.reload();
        }
        questionViewPager.notifyDataSetChanged();
    }

    @Override
    public void forceCloseApprende()
    {
        questionRepresentationDelegate.userRequieredProfileView();
    }

    @Override
    public void didFinishExam()
    {
        generateDataModel();
    }

    public void generateDataModel()
    {
        messageRepresentation.showLoading();
        HashMap<String, Object> currentDataModel = new HashMap<String, Object>();
        List<Object> questions = new ArrayList<Object>();
        int corrects = 0;
        int level = questionViewModel.size();
        for(Object questionModel : questionDataModel)
        {
            HashMap<String, Object> currentModel = ((HashMap)questionModel);
            HashMap<String, Object> fields = (HashMap<String, Object>) currentModel.get("fields");
            if(fields.containsKey("selection"))
            {
                String selection = fields.get("selection").toString();
                String correct = fields.get("correct").toString();
                fields.put("status", selection.equals(correct) ? true : false);
                corrects += selection.equals(correct) ? 1 : 0;
                questions.add(currentModel);
            }
            else
            {
                fields.put("status", false);
                questions.add(currentModel);
            }
        }
        int points = LevelProviderService.getInstance().getPointsByLevel(level);
        String title = LevelProviderService.getInstance().getTitleByLevel(level);
        int totalPoints = points * corrects;
        currentDataModel.put("questions", questions);
        currentDataModel.put("level_title", title);
        currentDataModel.put("level_points", totalPoints);
        currentDataModel.put("level_factor_level", points);
        currentDataModel.put("level_factor", level);
        currentDataModel.put("level_wrongs", level - corrects);
        currentDataModel.put("level_corrects", corrects);
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("exam_results", currentDataModel);
        mementoHandler.setStateForOwner(data, this);
        questionRepresentationDelegate.userFinishExam();
    }


    @Override
    public boolean onBackPressed() {
        return false;
    }
}
