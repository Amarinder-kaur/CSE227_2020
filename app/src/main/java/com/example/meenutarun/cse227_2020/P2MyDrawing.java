package com.example.meenutarun.cse227_2020;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by MeenuTarun on 5/16/2020.
 */

public class P2MyDrawing extends View {
    Paint p;
    Path mpath;
    boolean b;
 Bitmap bitmap_light;
    Bitmap bitmap_dark;
    int draw_dark=R.drawable.ic_autorenew_black_24dp;
  int draw_light=R.drawable.ic_autorenew_black_22dp_light;

    public P2MyDrawing(Context context) {
        super(context);
        p = new Paint();
           bitmap_light=getBitFromVector(context,draw_light);
       bitmap_dark=getBitFromVector(context,draw_dark);
         mpath = new Path();
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        //.FILL);
        p.setStrokeWidth(20f);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.YELLOW);
     //canvas.drawLine(23,34,450,560,p);
    //canvas.drawCircle(450,560,100,p);

        //parameters Details of drawPath: GraphicPath,Paint
        //draw specified path using the specified paint
     canvas.drawPath(mpath,p);
        if(b)
        {
            canvas.drawBitmap(bitmap_dark,10,10,p);
    }        else {
            canvas.drawBitmap(bitmap_light,10,10,p);
        }        }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mpath.moveTo(event.getX(), event.getY());// beginning point// set the beginning of the next contour to the point(x,y)
                break;
            case MotionEvent.ACTION_MOVE:
                b = true;
                mpath.lineTo(event.getX(), event.getY());//add a line from last point to specified point
                break;
            case MotionEvent.ACTION_UP:
                int x = (int) event.getX();
                int y = (int) event.getY();
                if ((x >= 10 & x <= 110) & (y >= 10 & y <= 120)) {
                    b=false;
                    mpath.reset();
                }
        }
                invalidate();
                return true;
        }
    public  static Bitmap getBitFromVector(Context context, int drawableId)
    {        //ContextCompat is a class for replacing some work with base context.like inspite of getcontext we are writing ContextCompat
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        //wrap:need to wrap original drawable.,
        // Mutate: Since Android optimized performance, by default, all drawables instances
        // loaded from the same resource share a common state. When we modify the state of
        // one instance, all the other instances will receive the same modification. So if we
        // want not to affect other drawables, don’t forget to mutate the drawable via
        // Drawable.mutate() before applying the filter. A mutable drawable is guaranteed to
        // not share its state with any other drawables.
        drawable = (DrawableCompat.wrap(drawable)).mutate();
        //getIntrinsicWidth(),getIntrinsicHeight()give you the "real"
        // width and height, respectively of the image.
//  It's called intrinsic, because it depends ONLY on the image and on nothing else (such as your phone).
        //Bitmap.Config.ARGB_8888: its bit map configuration:Each pixel is stored on
        // 4 bytes. Each channel (RGB and alpha for translucency) is stored with 8
        // bits of precision (256 possible values.) This configuration is very flexible
        // and offers the best quality
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        //left,top,right,bottom
        drawable.setBounds(0, 0,80,80);//canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;

    }


}
