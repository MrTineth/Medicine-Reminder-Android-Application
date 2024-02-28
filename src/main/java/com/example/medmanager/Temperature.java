package com.example.medmanager;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Temperature extends AppCompatActivity implements SensorEventListener {
    private TextView textView;
    private SensorManager sensorManager;

    private Sensor temperatureDetails;

    private boolean tempsensorAvailble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);


        textView = findViewById(R.id.textView23);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            temperatureDetails = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            tempsensorAvailble = true;
        } else {
            textView.setText("AMBIENT_TEMPERATURE is not Availble");
            tempsensorAvailble = false;
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        textView.setText("Now Temperature" + "\n" + (int) sensorEvent.values[0] + "°C");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (tempsensorAvailble) {
            sensorManager.registerListener(this, temperatureDetails, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (tempsensorAvailble) {
            sensorManager.unregisterListener(this);
        }
    }
}