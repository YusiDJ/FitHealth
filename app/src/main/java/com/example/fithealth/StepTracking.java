package com.example.fithealth;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class StepTracking extends AppCompatActivity implements SensorEventListener {

    private TextView textViewStepCounter;
    private ImageView imgView;
    private SensorManager sensorManager;
    private Sensor mStepCounter;
    private Boolean isSensorCounterPresent;
    int stepCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_tracking);

        // paste
        getSupportActionBar().setTitle("Step Tracking");

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        textViewStepCounter = findViewById(R.id.stepTracking_muna); // no initial design
        imgView = findViewById(R.id.step_tracking_id); // no initial design

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isSensorCounterPresent = true;
        } else {
            textViewStepCounter.setText("counter sensor is not present");
            isSensorCounterPresent = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == mStepCounter) {
            stepCount = (int) event.values[0];
            textViewStepCounter.setText(String.valueOf(stepCount));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensorManager.registerListener(this, mStepCounter, sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensorManager.unregisterListener(this, mStepCounter);
        }
    }
}