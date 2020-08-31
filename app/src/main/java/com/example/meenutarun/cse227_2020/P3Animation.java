package com.example.meenutarun.cse227_2020;
//Only four animations: rotation,alpha,translate and scale

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class P3Animation extends AppCompatActivity {
    ImageView imageView;
     AnimationDrawable animationdrawable;
    Animation ani1, ani7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p3_animation);
        ani7 = AnimationUtils.loadAnimation(this, R.anim.ani);
        imageView = (ImageView) findViewById(R.id.i1);
    //    imageView.setImageResource(R.drawable.b);

         imageView.setBackgroundResource(R.drawable.mylist);
        animationdrawable=(AnimationDrawable)imageView.getBackground();
    }
    public void Sequenceofimages(View v) {
    animationdrawable.start();
    }
    public void StopSequence(View v) {animationdrawable.stop();
    }
    public void RotateScale(View view) {

        imageView.startAnimation(ani7);
    }
    public void MovetheImage(View view) {
        Animation ani2 = AnimationUtils.loadAnimation
                (getApplicationContext(), R.anim.translate);
        imageView.startAnimation(ani2);
    }
    public void fade(View view) {
        Animation ani2 = AnimationUtils.loadAnimation
                (getApplicationContext(), R.anim.anim3);
        imageView.startAnimation(ani2);
    }
    public void blink(View view) {
        Animation ani2 = AnimationUtils.loadAnimation
                (getApplicationContext(), R.anim.anim4);
        imageView.startAnimation(ani2);
    }

    public void slide(View view) {
        Animation ani2 = AnimationUtils.loadAnimation
                (getApplicationContext(), R.anim.anim6);
        imageView.startAnimation(ani2);
    }
}






