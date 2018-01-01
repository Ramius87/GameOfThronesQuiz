package com.example.android.gameofthronesquiz;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class QuestionCheckBox extends AppCompatActivity {

    private boolean checked = false;
    private int questionNumber = 0;
    private int wrongAnswers = 0;
    private int wrightAnswers = 0;

    //Widgets
    ImageView questionImage = null;
    TextView question = null;
    CheckBox choice1 = null;
    CheckBox choice2 = null;
    CheckBox choice3 = null;
    CheckBox choice4 = null;
    Button checkButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_checkbox);

        Bundle extras = getIntent().getExtras();
        questionNumber = extras.getInt("questionNumber");
        wrongAnswers = extras.getInt("wrongAnswers");
        wrightAnswers = extras.getInt("wrightAnswers");

        // Widgets
        questionImage = (ImageView) findViewById(R.id.questionImage);
        question = (TextView) findViewById(R.id.question);
        choice1 = (CheckBox) findViewById(R.id.choice_1);
        choice2 = (CheckBox) findViewById(R.id.choice_2);
        choice3 = (CheckBox) findViewById(R.id.choice_3);
        choice4 = (CheckBox) findViewById(R.id.choice_4);

        switch (questionNumber) {
            case 3:
                question.setText(R.string.question_3);
                questionImage.setImageResource(R.drawable.question3);
                choice1.setText(getString(R.string.question_3_choice_1));
                choice2.setText(getString(R.string.question_3_choice_2));
                choice3.setText(getString(R.string.question_3_choice_3));
                choice4.setText(getString(R.string.question_3_choice_4));
                break;
            case 6:
                question.setText(R.string.question_6);
                questionImage.setImageResource(R.drawable.question6);
                choice1.setText(getString(R.string.question_6_choice_1));
                choice2.setText(getString(R.string.question_6_choice_2));
                choice3.setText(getString(R.string.question_6_choice_3));
                choice4.setText(getString(R.string.question_6_choice_4));
                break;
        }

        checkButton = (Button) findViewById(R.id.check_button);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to next Question if already checked
                if (checked) {
                    Intent nextQuestion = new Intent(QuestionCheckBox.this, QuestionTextfield.class);
                    questionNumber++;
                    nextQuestion.putExtra("questionNumber", questionNumber);
                    nextQuestion.putExtra("wrongAnswers", wrongAnswers);
                    nextQuestion.putExtra("wrightAnswers", wrightAnswers);
                    startActivity(nextQuestion);
                    finish();
                } else {
                    String correctChoices = null;
                    boolean answeredCorrectly = true;
                    switch (questionNumber) {
                        case 3:
                            correctChoices = getString(R.string.question_3_correct_ones);
                            break;
                        case 6:
                            correctChoices = getString(R.string.question_6_correct_ones);
                            break;
                    }
                    boolean answer1Correct = (correctChoices.indexOf("1") != -1);
                    boolean answer2Correct = (correctChoices.indexOf("2") != -1);
                    boolean answer3Correct = (correctChoices.indexOf("3") != -1);
                    boolean answer4Correct = (correctChoices.indexOf("4") != -1);

                    // Change TextColor of correct answers into green
                    if (answer1Correct)
                        choice1.setTextColor(ContextCompat.getColor(QuestionCheckBox.this, R.color.colorGreen));
                    if (answer2Correct)
                        choice2.setTextColor(ContextCompat.getColor(QuestionCheckBox.this, R.color.colorGreen));
                    if (answer3Correct)
                        choice3.setTextColor(ContextCompat.getColor(QuestionCheckBox.this, R.color.colorGreen));
                    if (answer4Correct)
                        choice4.setTextColor(ContextCompat.getColor(QuestionCheckBox.this, R.color.colorGreen));

                    // Check if correct answers were selected
                    if(answer1Correct && !choice1.isChecked()) answeredCorrectly = false;
                    if(answer2Correct && !choice2.isChecked()) answeredCorrectly = false;
                    if(answer3Correct && !choice3.isChecked()) answeredCorrectly = false;
                    if(answer4Correct && !choice4.isChecked()) answeredCorrectly = false;

                    // Change TextColor of wrong checked answers into red
                    if (!answer1Correct && choice1.isChecked()) {
                        choice1.setTextColor(ContextCompat.getColor(QuestionCheckBox.this, R.color.colorRed));
                        answeredCorrectly = false;
                    }
                    if (!answer2Correct && choice2.isChecked()) {
                        choice2.setTextColor(ContextCompat.getColor(QuestionCheckBox.this, R.color.colorRed));
                        answeredCorrectly = false;
                    }
                    if (!answer3Correct && choice3.isChecked()) {
                        choice3.setTextColor(ContextCompat.getColor(QuestionCheckBox.this, R.color.colorRed));
                        answeredCorrectly = false;
                    }
                    if (!answer4Correct && choice4.isChecked()) {
                        choice4.setTextColor(ContextCompat.getColor(QuestionCheckBox.this, R.color.colorRed));
                        answeredCorrectly = false;
                    }

                    if (answeredCorrectly) {
                        wrightAnswers++;
                    } else {
                        wrongAnswers++;
                    }

                    checkButton.setText(R.string.next);
                    checked = true;
                }
            }
        });
    }
}
