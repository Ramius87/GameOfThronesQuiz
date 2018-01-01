package com.example.android.gameofthronesquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int questionNumber = 1;

    // Widgets
    Button startButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Widgets
        startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent question1 = new Intent(MainActivity.this,QuestionTextfield.class);
                question1.putExtra("questionNumber",1);
                startActivity(question1);
            }
        });


    }
}
