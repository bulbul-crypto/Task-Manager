

// This file handles Splash Screen


package com.example.try_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progress,lastPosition=-1;
    private ImageView logo;
    private View vi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        progressBar = findViewById(R.id.proGREssBar);
        logo = findViewById(R.id.lg);
        vi = (View) logo;

        setAnimation(vi,0);//--

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                doWork();
                moveOnwards();
            }
        });

        thread.start();

    }

    public void setAnimation(View viewToAnimate, int position)
    {
        if (position>lastPosition)
        {
            ScaleAnimation scaleAnimation=new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

            scaleAnimation.setDuration(1000);
            viewToAnimate.startAnimation(scaleAnimation);
            lastPosition=position;
        }
    }

    public void doWork(){

        for(progress=1;progress<=123;progress+=10){

            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void moveOnwards(){

        Intent intent = new Intent(MainActivity.this,HomePageND.class);
        startActivity(intent);

    }

}
