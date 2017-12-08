package com.example.a32150.a2017110902;

import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {

    private int nowPicPos = 0;
    private int[] imgRes = {R.drawable.car};
    public ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        iv=(ImageView)findViewById(R.id.imgCar);
        //fadeOutAndHideImage(iv);
        fadeInAndShowImage(iv);
    }

    private void fadeOutAndHideImage(final ImageView img){
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(5000);

        fadeOut.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation)
            {
                //nowPicPos %= 2;
                img.setImageResource(imgRes[0]);
                //nowPicPos++;
                fadeInAndShowImage(img);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });
        img.startAnimation(fadeOut);
    }
    private void fadeInAndShowImage(final ImageView img){
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(2000);
        fadeIn.setRepeatCount(1);

        fadeIn.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation)
            {
                fadeOutAndHideImage(img);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });
        img.startAnimation(fadeIn);
    }

    public void onClick(View v) {
        Intent it = new Intent(StartActivity.this, MainActivity.class);
        startActivity(it);
    }
}
