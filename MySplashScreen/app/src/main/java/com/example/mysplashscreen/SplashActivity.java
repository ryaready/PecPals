package com.example.mysplashscreen;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    //timer in miliseconds, 1000ms = 1s
    private static final int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Get reference to ImageView
        ImageView imageView = findViewById(R.id.animatedImageView);

        // Set animation to ImageView
        imageView.setBackgroundResource(R.drawable.animation_splash); // Use the scaled animation drawable

        // Get AnimationDrawable object
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();

        // Calculate scaling factor
        float scaleFactor = 5f; // Scale factor of 5

        // Set the duration of each frame according to the scaling factor
        for (int i = 0; i < animationDrawable.getNumberOfFrames(); i++) {
            animationDrawable.getFrame(i).setLevel((int) (animationDrawable.getFrame(i).getLevel() * scaleFactor));
        }

        // Start the animation
        animationDrawable.start();

        // make sure to hide this if the bottom portion is not commented

       new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              Intent intent = new Intent(SplashActivity.this, BottomNavActivity.class);
              startActivity(intent);
          }
      }, SPLASH_TIME);
    }
}

