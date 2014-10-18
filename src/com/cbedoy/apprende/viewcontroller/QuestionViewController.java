package com.cbedoy.apprende.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.business.question.interfaces.IQuestionRepresentationDelegate;
import com.cbedoy.apprende.business.question.interfaces.IQuestionRepresentationHandler;

/**
 * Created by Carlos on 17/10/2014.
 */
public class QuestionViewController extends AbstractViewController implements IQuestionRepresentationHandler
{
    private TextView questionView;
    private RadioButton answerOne;
    private RadioButton answerTwo;
    private RadioButton answerThree;
    private RadioButton answerFour;
    private Button actionFinish;

    private IQuestionRepresentationDelegate questionRepresentationDelegate;

    public void setQuestionRepresentationDelegate(IQuestionRepresentationDelegate questionRepresentationDelegate) {
        this.questionRepresentationDelegate = questionRepresentationDelegate;
    }

    @Override
    protected View init()
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.app_question_viewcontroller,  null);
        questionView = (TextView) view.findViewById(R.id.question_question);
        answerOne = (RadioButton) view.findViewById(R.id.question_question_option_one);
        answerTwo = (RadioButton) view.findViewById(R.id.question_question_option_two);
        answerThree = (RadioButton) view.findViewById(R.id.question_question_option_three);
        answerFour = (RadioButton) view.findViewById(R.id.question_question_option_four);
        actionFinish = (Button) view.findViewById(R.id.question_finish);
        actionFinish.setOnClickListener(new View.OnClickListener() {
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
    public void showQuestionary() {
        this.appViewManager.presentViewForTag(this.tag);
    }
}
