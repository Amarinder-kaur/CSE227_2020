package com.example.meenutarun.cse227_2020;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class P2CustomViewWithBitamp extends View
{
    Bitmap bg,mario;
    Paint p;
    int x=10,q=240,t=10;
    public P2CustomViewWithBitamp(Context context) {
        super(context);
        init();
    }
    void init()
    {
        p=new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        bg = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        Rect r1 = new Rect(0, 0, bg.getWidth(), bg.getHeight());
        Rect r2 = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(bg, r1, r2, null);
        mario=BitmapFactory.decodeResource(getResources(),R.drawable.c);
        canvas.drawBitmap(mario,x,canvas.getHeight()-mario.getHeight()-q,null);
       // canvas.drawArc(500,350,800,800,t,30,true,p);
        //canvas.drawArc(500,350,800,800,t+120,30,true,p);
        //canvas.drawArc(500,350,800,800, t+240,30,true,p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                moveAhead();
                break;
            case MotionEvent.ACTION_UP:
                getBAck();
                break;
        }
        return true;
    }
    public void moveAhead()
    {
        x=x+20;
        q=q+ 300;
        //t ia for fan which i already commented
        // t=t+5;
        invalidate();
    }
    public void getBAck()
    {
        q=q- 300;
        invalidate();
    }
}

