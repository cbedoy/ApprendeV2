package com.cbedoy.apprende.widgets;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cbedoy.apprende.R;
import com.cbedoy.apprende.activity.ApplicationLoader;
import com.cbedoy.apprende.interfaces.IQuestionViewRepresentationDelegate;
import com.cbedoy.apprende.service.ImageService;

import java.util.HashMap;

/**
 * Created by Carlos on 26/10/2014.
 */
public class QuestionView
{
    private TextView questionView;
    private RadioButton answerOne;
    private RadioButton answerTwo;
    private RadioButton answerThree;
    private RadioButton answerFour;
    private RadioButton currentSelection;
    private Button actionFinish;
    private View view;
    private HashMap<String, Object> dataModel;
    private Context context;
    private RadioGroup radioGroup;
    private TextView feedback;
    private boolean discoverFeedback;
    private IQuestionViewRepresentationDelegate questionViewRepresentationDelegate;

    public QuestionView(Context context){
        this.context = context;
    }

    public void setQuestionViewRepresentationDelegate(IQuestionViewRepresentationDelegate questionViewRepresentationDelegate) {
        this.questionViewRepresentationDelegate = questionViewRepresentationDelegate;
    }

    public View getView(){
        if(view == null)
            view = init();
        return view;
    }

    private View init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.app_question_view,  null);
        questionView = (TextView) view.findViewById(R.id.question_question);
        answerOne = (RadioButton) view.findViewById(R.id.question_option_one);
        answerTwo = (RadioButton) view.findViewById(R.id.question_option_two);
        answerThree = (RadioButton) view.findViewById(R.id.question_option_three);
        answerFour = (RadioButton) view.findViewById(R.id.question_option_four);
        actionFinish = (Button) view.findViewById(R.id.question_finish);
        feedback = (TextView) view.findViewById(R.id.question_feedback);
        actionFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(!discoverFeedback)
                    questionViewRepresentationDelegate.didFinishExam();
                else
                    questionViewRepresentationDelegate.forceCloseApprende();
            }
        });
        radioGroup = (RadioGroup) view.findViewById(R.id.question_radio_group);
        final QuestionView self = this;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                HashMap<String, Object> fields = (HashMap<String, Object>) dataModel.get("fields");
                if(answerOne.isChecked())
                {
                   fields.put("selection", 1);
                    currentSelection = answerOne;
                }
                else if(answerTwo.isChecked())
                {
                    fields.put("selection", 2);
                    currentSelection = answerTwo;
                }
                else if(answerThree.isChecked())
                {
                    fields.put("selection", 3);
                    currentSelection = answerThree;
                }
                else if(answerFour.isChecked())
                {
                    fields.put("selection", 4);
                    currentSelection = answerFour;
                }
            }
        });
        questionView.setTypeface(ImageService.regularFont);
        answerOne.setTypeface(ImageService.lightFont);
        answerFour.setTypeface(ImageService.lightFont);
        answerThree.setTypeface(ImageService.lightFont);
        answerTwo.setTypeface(ImageService.lightFont);
        actionFinish.setTypeface(ImageService.boldFont);
        feedback.setTypeface(ImageService.regularFont);
        return view;
    }


    public void setDataModel(HashMap<String, Object> dataModel)
    {
        this.dataModel = dataModel;
    }

    public void reload()
    {
        if(this.dataModel != null)
        {
            HashMap<String, Object> fields = (HashMap<String, Object>) dataModel.get("fields");
            questionView.setText(fields.get("question").toString());
            answerOne.setText(fields.get("answer1").toString());
            answerTwo.setText(fields.get("answer2").toString());
            answerThree.setText(fields.get("answer3").toString());
            answerFour.setText(fields.get("answer4").toString());
            feedback.setText(fields.get("feedback").toString());
            feedback.setVisibility(!discoverFeedback ? View.GONE : View.VISIBLE);
        }
    }

    public void discoverFeedBack(){
        this.discoverFeedback = !discoverFeedback;
        if(this.dataModel != null){
            HashMap<String, Object> fields = (HashMap<String, Object>) dataModel.get("fields");
            int correct = Integer.parseInt(fields.get("correct").toString());
            if(fields.containsKey("selection"))
            {
                int selection = Integer.parseInt(fields.get("selection").toString());

                if (correct == selection)
                {
                    currentSelection.setBackgroundColor(context.getResources().getColor(R.color.app_blue));
                    currentSelection.setTextColor(Color.parseColor("#fafafa"));
                    currentSelection.setTypeface(ImageService.boldFont);
                }
                else
                {
                    currentSelection.setBackgroundColor(Color.parseColor("#a0a0a0"));
                    currentSelection.setTextColor(Color.parseColor("#fafafa"));
                    currentSelection.setTypeface(ImageService.thinFont);
                    setCorrectOption(   correct == 1 ? answerOne :
                                        correct == 2 ? answerTwo :
                                        correct == 3 ? answerThree : answerFour);
                }
            }
            else
            {
                setCorrectOption(   correct == 1 ? answerOne :
                                    correct == 2 ? answerTwo :
                                    correct == 3 ? answerThree : answerFour);
            }
        }
    }

    private void setCorrectOption(RadioButton radioButton)
    {
        radioButton.setBackgroundColor(context.getResources().getColor(R.color.app_pink));
        radioButton.setTextColor(Color.parseColor("#fafafa"));
        radioButton.setTypeface(ImageService.boldFont);
    }

    public HashMap<String, Object> getDataModel() {
        return dataModel;
    }
}
