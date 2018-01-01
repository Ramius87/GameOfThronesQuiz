package com.example.android.gameofthronesquiz;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionRadioButton extends AppCompatActivity {

    private boolean checked = false;
    private int questionNumber = 0;
    private int wrongAnswers = 0;
    private int wrightAnswers = 0;

    //Widgets
    ImageView questionImage = null;
    TextView question = null;
    RadioGroup answers = null;
    RadioButton choice1 = null;
    RadioButton choice2 = null;
    RadioButton choice3 = null;
    Button checkButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_radiobutton);

        Bundle extras = getIntent().getExtras();
        questionNumber = extras.getInt("questionNumber");
        wrongAnswers = extras.getInt("wrongAnswers");
        wrightAnswers = extras.getInt("wrightAnswers");

        // Widgets
        questionImage = (ImageView) findViewById(R.id.questionImage);
        question = (TextView) findViewById(R.id.question);
        answers = (RadioGroup) findViewById(R.id.answers);
        choice1 = (RadioButton) findViewById(R.id.choice_1);
        choice2 = (RadioButton) findViewById(R.id.choice_2);
        choice3 = (RadioButton) findViewById(R.id.choice_3);

        switch (questionNumber) {
            case 2:
                questionImage.setImageResource(R.drawable.question2);
                question.setText(R.string.question_2);
                choice1.setText(getString(R.string.question_2_choice_1));
                choice2.setText(getString(R.string.question_2_choice_2));
                choice3.setText(getString(R.string.question_2_choice_3));
                break;
            case 5:
                questionImage.setImageResource(R.drawable.question5);
                question.setText(R.string.question_5);
                choice1.setText(getString(R.string.question_5_choice_1));
                choice2.setText(getString(R.string.question_5_choice_2));
                choice3.setText(getString(R.string.question_5_choice_3));
                break;
            case 8:
                questionImage.setImageResource(R.drawable.question8);
                question.setText(R.string.question_8);
                choice1.setText(getString(R.string.question_8_choice_1));
                choice2.setText(getString(R.string.question_8_choice_2));
                choice3.setText(getString(R.string.question_8_choice_3));
                break;
            case 10:
                questionImage.setImageResource(R.drawable.question10);
                question.setText(R.string.question_10);
                choice1.setText(getString(R.string.question_10_choice_1));
                choice2.setText(getString(R.string.question_10_choice_2));
                choice3.setText(getString(R.string.question_10_choice_3));
                break;
        }

        checkButton = (Button) findViewById(R.id.check_button);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to next Question if already checked
                if (checked) {
                    Intent nextQuestion = null;
                    switch (questionNumber) {
                        case 2:
                        case 5:
                            nextQuestion = new Intent(QuestionRadioButton.this, QuestionCheckBox.class);
                            break;
                        case 8:
                            nextQuestion = new Intent(QuestionRadioButton.this, QuestionTextfield.class);
                            break;
                        case 10:
                            nextQuestion = new Intent(QuestionRadioButton.this, Result.class);
                    }
                    questionNumber++;
                    nextQuestion.putExtra("questionNumber", questionNumber);
                    nextQuestion.putExtra("wrongAnswers", wrongAnswers);
                    nextQuestion.putExtra("wrightAnswers", wrightAnswers);
                    startActivity(nextQuestion);
                    finish();
                } else {
                    int selectedId = answers.getCheckedRadioButtonId();
                    if (selectedId == -1) {
                        //Toast.makeText(QuestionRadioButton.this, getString(R.string.no_radiobutton_selected), Toast.LENGTH_SHORT).show();
                        Toast ToastMessage = Toast.makeText(QuestionRadioButton.this, getString(R.string.no_radiobutton_selected), Toast.LENGTH_SHORT);
                        View toastView = ToastMessage.getView();
                        toastView.setBackgroundColor(ContextCompat.getColor(QuestionRadioButton.this, R.color.colorPrimary));
                        toastView.setPadding(10,5,10,5);
                        toastView.setAlpha(0.4f);
                        ToastMessage.show();
                        return;
                    }
                    RadioButton selectedAnswer = (RadioButton) findViewById(selectedId);
                    String correctChoice = null;
                    boolean answeredCorrectly = true;
                    switch (questionNumber) {
                        case 2:
                            correctChoice = getString(R.string.question_2_correct_choice);
                            break;
                        case 5:
                            correctChoice = getString(R.string.question_5_correct_choice);
                            break;
                        case 8:
                            correctChoice = getString(R.string.question_8_correct_choice);
                            break;
                        case 10:
                            correctChoice = getString(R.string.question_10_correct_choice);
                            break;
                    }
                    boolean answer1Correct = correctChoice.equals("1");
                    boolean answer2Correct = correctChoice.equals("2");
                    boolean answer3Correct = correctChoice.equals("3");

                    // Change TextColor of correct answer into green
                    if (answer1Correct)
                        choice1.setTextColor(ContextCompat.getColor(QuestionRadioButton.this, R.color.colorGreen));
                    if (answer2Correct)
                        choice2.setTextColor(ContextCompat.getColor(QuestionRadioButton.this, R.color.colorGreen));
                    if (answer3Correct)
                        choice3.setTextColor(ContextCompat.getColor(QuestionRadioButton.this, R.color.colorGreen));

                    // Change TextColor of wrong checked answers into red
                    if (!answer1Correct && choice1.isChecked()) {
                        choice1.setTextColor(ContextCompat.getColor(QuestionRadioButton.this, R.color.colorRed));
                        answeredCorrectly = false;
                    }
                    if (!answer2Correct && choice2.isChecked()) {
                        choice2.setTextColor(ContextCompat.getColor(QuestionRadioButton.this, R.color.colorRed));
                        answeredCorrectly = false;
                    }
                    if (!answer3Correct && choice3.isChecked()) {
                        choice3.setTextColor(ContextCompat.getColor(QuestionRadioButton.this, R.color.colorRed));
                        answeredCorrectly = false;
                    }

                    if (answeredCorrectly) {
                        wrightAnswers++;
                    } else {
                        wrongAnswers++;
                    }

                    if (questionNumber != 10) {
                        checkButton.setText(R.string.next);
                    } else {
                        checkButton.setText(R.string.result);
                    }

                    checked = true;
                }
            }
        });


    }
}
