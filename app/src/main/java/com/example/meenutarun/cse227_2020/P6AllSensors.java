package com.example.meenutarun.cse227_2020;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class P6AllSensors extends AppCompatActivity    implements SensorEventListener {
    TextView tvsensor,tvsensor1,tvsensor2;

    //variable which hold an instance of SensorManager:
    SensorManager sensorManager;

    Sensor lightSensor, proxySensor,tempSensor, accSensor, magSensor;

    float [] accarr = new float[3];
    float [] magarr = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p6_all_sensors);
        tvsensor = (TextView) findViewById(R.id.tvsensor);
        tvsensor1=(TextView)findViewById(R.id.tvsensor1);
        tvsensor2=(TextView)findViewById(R.id.tvsensor2);
//1. get an instance of the sensor manager from system services,
// and assign it to the sensorManager variable:
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        proxySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        magSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (lightSensor != null) {

            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (proxySensor != null) {
            sensorManager.registerListener(this, proxySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (tempSensor != null) {
            sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(accSensor != null)
        {
            sensorManager.registerListener(this,accSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(magSensor != null)
        {
            sensorManager.registerListener(this,magSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }


    }

    @Override
    protected void onPause() {
        super.onPause();

        //sensorManager.unregisterListener(this,lightSensor);
        //sensorManager.unregisterListener(this,proxySensor);

        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        int sensorType = sensorEvent.sensor.getType();
        switch (sensorType)
        {
            case Sensor.TYPE_LIGHT:
                break;
            case Sensor.TYPE_PROXIMITY:
                float[] value  = sensorEvent.values;
                Toast.makeText(this, "Distance is "+ value[0], Toast.LENGTH_SHORT).show();

                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                break;
            case Sensor.TYPE_ACCELEROMETER:
                float[] values = sensorEvent.values;

                tvsensor1.setText("x: "+values[0]+"\ny: "+values[1]+"\nz: "+values[2]);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                float[] values1 = sensorEvent.values;

                tvsensor2.setText("x: "+values1[0]+"\ny: "+values1[1]+"\nz: "+values1[2]);
                break;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //display all the sensors available in system
    public void dothis(View view)
    {
//1. Get the list of all sensors from the sensor manager.
// Store the list in a List object whose values are of type Sensor:
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        StringBuilder sb = new StringBuilder();

  /*Iterate over the list of sensors. For each sensor, get that sensor's
   official name with the getName() method, and append that name to the
    StringBuilder string. Each line of the sensor list is separated by the
     value of the line.separator property, typically a newline character:
    */
        for(Sensor s : sensorList)
        {
            String s1 = s.getName();
            //+ " vendor: "+ s.getVendor()+ "Version" + s.getVersion();
            sb.append(s1+"\n");
        }
        tvsensor.setText(sb);
    }
}
