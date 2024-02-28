package com.example.medmanager;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AvailbleSensors extends AppCompatActivity {
    private TextView textView;
    private SensorManager sensorManager;
    private List<Sensor> deviceSensors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_availble_sensors);
        textView = findViewById(R.id.textView28);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        printofListofsensors();

    }
    private void printofListofsensors(){
        for(Sensor sensor :deviceSensors)
        {
            textView.setText(textView.getText()+"\n"+"$__"+sensor.getName());
        }

    }
}