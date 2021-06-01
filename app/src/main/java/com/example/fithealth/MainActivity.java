package com.example.fithealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewStepCounter, textViewStepDetector;
    private SensorManager sensorManager;
    private Sensor mStepCounter;
    private Boolean isSensorCounterPresent;
    int stepCount = 0;
    private ImageButton stepTrack_Button, profileButton, meaIntake_Button, calculateCalorie_Button,
            dietarySuggestion_Button, myGoal_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        setting image button for call other activity
         */

        // step tracking
        stepTrack_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call the open_StepTracking method
                open_StepTracking();
            }
        });
    }


    /*
    Method for opening the new activities
     */
    private void open_StepTracking() {
        Intent stepTracker = new Intent(this, StepTracking.class);
        startActivity(stepTracker);
    }
}