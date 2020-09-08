package com.example.meenutarun.cse227_2020;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//Creating custom views. By extending the View class or one of its
// subclasses you can create your custom view. For drawing view use the
// onDraw() method. In this method you receive a Canvas object which allows
// you to perform drawing operations on it, e.g. draw lines, circle, text
//or bitmap
public class P2CanvasDrawing extends AppCompatActivity {
P2MyDrawing cv;
    P2FanSurfaceView cv1;
//P2CustomViewWithBitamp CV1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_p2_canvas_drawing);

        //cv= new P2MyDrawing(this);
        cv1= new P2FanSurfaceView(this);
        setContentView(cv1);
    }
}
