package com.example.android.gameofthronesquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestionTextfield extends AppCompatActivity {

    private boolean checked = false;
    private int questionNumber = 0;
    private int wrongAnswers = 0;
    private int wrightAnswers = 0;

    //Widgets
    ImageView questionImage = null;
    TextView question = null;
    TextView solution = null;
    EditText answer = null;
    Button checkButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_textfield);

        Bundle extras = getIntent().getExtras();
        questionNumber = extras.getInt("questionNumber");
        wrongAnswers = extras.getInt("wrongAnswers");
        wrightAnswers = extras.getInt("wrightAnswers");

        // Widgets
        questionImage = (ImageView) findViewById(R.id.questionImage);
        question = (TextView) findViewById(R.id.question);
        answer = (EditText) findViewById(R.id.txtAnswer);
        solution = (TextView) findViewById(R.id.solution);
        checkButton = (Button) findViewById(R.id.check_button);

        switch (questionNumber) {
            case 1:
                questionImage.setImageResource(R.drawable.question1);
                question.setText(R.string.question_1);
                break;
            case 4:
                questionImage.setImageResource(R.drawable.question4);
                question.setText(R.string.question_4);
                break;
            case 7:
                questionImage.setImageResource(R.drawable.question7);
                question.setText(R.string.question_7);
                answer.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case 9:
                questionImage.setImageResource(R.drawable.question9);
                question.setText(R.string.question_9);
                break;
        }

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to next Question if already checked
                if (checked) {
                    Intent nextQuestion = new Intent(QuestionTextfield.this, QuestionRadioButton.class);
                    questionNumber++;
                    nextQuestion.putExtra("questionNumber",questionNumber);
                    nextQuestion.putExtra("wrongAnswers", wrongAnswers);
                    nextQuestion.putExtra("wrightAnswers", wrightAnswers);
                    startActivity(nextQuestion);
                    finish();
                } else {
                    String correctAnswer = null;
                    switch (questionNumber) {
                        case 1:
                            correctAnswer = getString(R.string.question_1_answer);
                            break;
                        case 4:
                            correctAnswer = getString(R.string.question_4_answer);
                            break;
                        case 7:
                            correctAnswer = getString(R.string.question_7_answer);
                            break;
                        case 9:
                            correctAnswer = getString(R.string.question_9_answer);
                            break;
                    }
                    // Answer is correct
                    if (answer.getText().toString().equalsIgnoreCase(correctAnswer)) {
                        solution.setText(getString(R.string.correct));
                        wrightAnswers++;
                        // Answer is wrong
                    } else {
                        solution.setText(getString(R.string.wrong_solution) + " " + correctAnswer);
                        wrongAnswers++;
                    }
                    checkButton.setText(R.string.next);
                    checked = true;
                }
            }
        });


    }
}
