package com.example.meenutarun.cse227_2020;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

//import androidx.annotation.Nullable;

//To read more: https://codelabs.developers.google.com/codelabs/advanced-android-training-animations/index.html?index=..%2F..advanced-android-training#0

/*P11PropertyAnimation allows you to animate almost any property of an object. You can define an animation to change
 any object property (a field in an object) over time, regardless of whether the change draws
  to the screen or not. A property animation changes a property's value over a specified length
  of time.For example, you can animate a circle to grow bigger by increasing its radius.*/

/*
Create a custom View that includes a radius property.
Create simple radius-based animations using ObjectAnimator objects.
Combine and sequence animations using an AnimatorSet object.

 */

/*
1. Create an app with a single activity(P11JAvaAnimation) and a P11PropertyAnimation custom view.
2. Add an mradius property to the P11PropertyAnimation class and a setRadius() "setter" method for that custom property. The Animator instance will call the setRadius() method to change the size of mradius during the animation.
3. Override the onDraw() method to draw a circle of size mradius. The circle is created at the place where the user taps.
4. Create two Animator instances for the radius property.
5. Use an AnimatorSet to play the animations in sequence after the user taps the screen.
 */
public class P2CircleAnimation extends View
{
    final int AnimationDuration = 4000;// specify the duration of an animation
    final int AnimationDelay = 10;//delay between Grow and shriak animation
    final int ColorAdjust = 5;
    float x,y,mradius;
    Paint paint;
    AnimatorSet animatorSet;//you can group animations into logical sets that play together or sequentially or after specified delays.

    public P2CircleAnimation(Context context) {
        super(context);
        paint = new Paint();
        animatorSet = new AnimatorSet();
    }
    //this method name should be same as mentioned in ObjectAnimator.ofFloat(this,"radius1",0,getWidth());
// if in thi parameter we mentioned Radius2 then automaticallt setRadius2 method will be created
//The property animator needs to be able to change the property that will be animated.
// It does this through a "setter" method for the property (setradius1 in our case).
    void setRadius1(float r)
    {
        mradius = r;
        paint.setColor(Color.GREEN + (int) mradius / ColorAdjust);
        invalidate();
    }

    //Inside the onSizeChanged() method, you will create two ObjectAnimator objects and one AnimatorSet object.
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

//Create simple radius-based animations using ObjectAnimator objects.

// Four parameters deatils of ofFloat()
//1.You need to pass in a reference to the object that is being animated (this),
// 2.the name of the property that is to be animated ("radius"),
// 3.the starting value (0),
// 4.the ending value (getWidth()). In this case, the ending value is the width of the P2CircleAnimation.
        ObjectAnimator growAnimator = ObjectAnimator.ofFloat(this,"radius1",0,getWidth());
        growAnimator.setDuration(AnimationDuration);

        //setInterpolator: The interpolator affects the rate of change; that is, the interpolator affects how the animated property changes from its starting value to its ending value
//LinearInterpolator:the rate of change is constant; that is, the value changes by the same amount for every animation step.
        growAnimator.setInterpolator(new LinearInterpolator());


        ObjectAnimator shrinkAnimator = ObjectAnimator.ofFloat(this,
                "radius1", getWidth(), 0);
        shrinkAnimator.setDuration(AnimationDuration);
// AccelerateDecelerateInterpolator, the rate of change starts and ends slowly but accelerates through the middle. The animation starts with increasingly larger steps and then ends with decreasingly smaller steps
        shrinkAnimator.setInterpolator(new DecelerateInterpolator());
        shrinkAnimator.setStartDelay(AnimationDelay);

//Combine and sequence animations using an AnimatorSet object.
        animatorSet.play(growAnimator).before(shrinkAnimator);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                if(animatorSet != null && animatorSet.isRunning())
                    animatorSet.cancel();

                animatorSet.start();
                break;
        }
        return super.onTouchEvent(event);
    }
    //Create a custom View that includes a radius property.
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x,y,mradius,paint);
    }

}
