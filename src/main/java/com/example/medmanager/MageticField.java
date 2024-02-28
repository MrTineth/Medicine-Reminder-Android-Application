package com.example.medmanager;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MageticField extends AppCompatActivity implements SensorEventListener {
    private TextView textView;
    private SensorManager sensorManager;

    private Sensor mageticfieldDetails;

    private boolean mageticsensorAvailble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magetic_field);


        textView = findViewById(R.id.textView31);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            mageticfieldDetails = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            mageticsensorAvailble = true;
        } else {
            textView.setText("MAGNETIC_FIELD is not Availble");
            mageticsensorAvailble = false;
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        textView.setText("Now MegeticField" + "\n" + (int) sensorEvent.values[0] );
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mageticsensorAvailble) {
            sensorManager.registerListener(this, mageticfieldDetails, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mageticsensorAvailble) {
            sensorManager.unregisterListener(this);
        }
    }
}