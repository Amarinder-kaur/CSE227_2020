package com.example.meenutarun.cse227_2020;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
import android.view.View;


public class P2FanCanvas extends View {
    Paint p;
    int x=100;
    public P2FanCanvas(Context context) {
        super(context);
        p = new Paint();
      //  init();
    }
     @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.RED);
        p.setColor(Color.YELLOW);
        //left,top,right,bottom
        canvas.drawRect(100,100,500,500,p);
        p.setColor(Color.GREEN);
        //  canvas.drawCircle(x,600,50,p);
        // canvas.drawCircle(x,700,50,p);
        //canvas.drawCircle(x,800,50,p);
//public void drawArc(float left, float top, float right, float bottom,
// float startAngle, float sweepAngle, boolean useCenter, Paint paint)
        canvas.drawArc(500,500,800,800,x,30,true,p);
        canvas.drawArc(500,500,800,800,x+120,30,true,p);
        canvas.drawArc(500,500,800,800, x+240,30,true,p);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //this for loop to increase the speed of fan
        for (int i = 1; i <= 50000; i++) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_UP:
                    startFan();
                    break;
            }
        }
        return true;
    }
    void startFan()
    {
        x = x+5;
        // invalidate() means redraw on screen  adn results to a call of the view's onDraw() method
        invalidate();
    }

}


