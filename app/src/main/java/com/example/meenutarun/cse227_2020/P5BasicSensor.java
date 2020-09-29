package com.example.meenutarun.cse227_2020;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
/*An accelerometer is an electronic sensor that measures the acceleration forces
 acting on an object, in order to determine the object's position in space and
 monitor the object's movement
  */
/*
How does accelerometer sensor work?
An accelerometer is a device that measures the vibration, or acceleration of motion of a structure. The force caused by vibration or a change in motion (acceleration) causes the mass to "squeeze" the piezoelectric material which produces an electrical charge that is proportional to the force exerted upon it.

 */
public class P5BasicSensor extends AppCompatActivity {
    TextView t1 = null;
    TextView t2;

    SensorManager sm = null;
    List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5_basic_sensor);
        t1 = (TextView) findViewById(R.id.txtSensor);
        t2=(TextView) findViewById(R.id.txtSensor1);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        //parameters: registerListener(ISensorEventListener, Sensor, SensorDelay)
        if(list.size()>0){
            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(getBaseContext(), "Error: No Accelerometer.", Toast.LENGTH_LONG).show();
        }
    }
    //these are two methods to override for SensorEventListener interface
    SensorEventListener sel = new SensorEventListener(){
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            t1.setText("x: "+values[0]+"\ny: "+values[1]+"\nz: "+values[2]);
        }
    };

    @Override
    protected void onStop() {
        if(list.size()>0){
            sm.unregisterListener(sel);//   SensorManager sm; sel: sensorListener
        }
        super.onStop();
    }
    public void sensorlist(View view)
    {
//1. Get the list of all sensors from the sensor manager.
// Store the list in a List object whose values are of type Sensor:
        List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ALL);

        StringBuilder sb = new StringBuilder();

  /*Iterate over the list of sensors. For each sensor, get that sensor's
   official name with the getName() method, and append that name to the
    StringBuilder string. Each line of the sensor list is separated by the
     value of the line.separator property, typically a newline character:
    */
        for(Sensor s : sensorList)
        {
            String s1 = s.getName()+" RANGE:- "+s.getMaximumRange();
            //+ " vendor: "+ s.getVendor()+ "Version" + s.getVersion();
            sb.append(s1+"\n");
        }
        t2.setText(sb);
    }

}