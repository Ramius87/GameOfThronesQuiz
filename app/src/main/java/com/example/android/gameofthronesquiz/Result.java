package com.example.android.gameofthronesquiz;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;

public class Result extends AppCompatActivity {

    private int wrongAnswers = 0;
    private int wrightAnswers = 0;

    private ArrayList<Entry> yvalues = new ArrayList<Entry>();
    private ArrayList<String> xvalues = new ArrayList<String>();

    // Widgets
    Button endButton = null;

    // Chart
    PieChart piechart = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();
        wrongAnswers = extras.getInt("wrongAnswers");
        wrightAnswers = extras.getInt("wrightAnswers");

        // Widgets
        endButton = (Button) findViewById(R.id.endButton);

        // Charts
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        // Creating Dataset
        yvalues.add(new Entry(wrongAnswers,1));
        yvalues.add(new Entry(wrightAnswers,2));

        PieDataSet dataSet = new PieDataSet(yvalues, "");
        dataSet.setSliceSpace(15f);
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(ContextCompat.getColor(Result.this, R.color.colorChartRed));
        colors.add(ContextCompat.getColor(Result.this, R.color.colorChartGreen));
        dataSet.setColors(colors);

        // Defining x-Axis Labels
        xvalues.add(getString(R.string.chart_false));
        xvalues.add(getString(R.string.chart_correct));

        PieData data = new PieData(xvalues, dataSet);

        // Set Value Formatter
        // In percentage Term
        data.setValueFormatter(new PercentFormatter());
        // Default value
        //data.setValueFormatter(new DefaultValueFormatter(0));

        // Formatting Pie Chart
        data.setValueTextSize(18f);
        Typeface got = Typeface.createFromAsset(getAssets(),"font/gameofthrones.ttf");
        data.setValueTypeface(got);

        // Set Data and create pieChart
        pieChart.setData(data);

        // Formatting Pie Chart
        pieChart.setTransparentCircleRadius(100);
        pieChart.setHoleColorTransparent(true);
        pieChart.setAlpha(0.9f);

        // Disable Chart Legend
        pieChart.getLegend().setEnabled(false);
        pieChart.setDescription(null);

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
