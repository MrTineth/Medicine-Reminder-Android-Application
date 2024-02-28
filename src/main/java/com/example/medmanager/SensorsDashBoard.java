package com.example.medmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SensorsDashBoard extends AppCompatActivity {


    ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_dash_board);


        // SensorDashBoard to Temperature
        imageButton = (ImageButton) findViewById(R.id.imageButton18);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SensorsDashBoard.this,Temperature.class);
                startActivity(intent);

            }
        });


        // SensorDashBoard to AvailbleSensors
        imageButton = (ImageButton) findViewById(R.id.imageButton17);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SensorsDashBoard.this,AvailbleSensors.class);
                startActivity(intent);

            }
        });

        // SensorDashBoard to Accelerometer
        imageButton = (ImageButton) findViewById(R.id.imageButton16);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SensorsDashBoard.this,Accelerometer.class);
                startActivity(intent);

            }
        });

        // SensorDashBoard to MageticFieldSensor
        imageButton = (ImageButton) findViewById(R.id.imageButton15);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SensorsDashBoard.this,MageticField.class);
                startActivity(intent);

            }
        });
    }
}