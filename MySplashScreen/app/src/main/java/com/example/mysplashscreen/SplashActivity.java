package com.example.mysplashscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    // Timer duration in milliseconds
    private static final int SPLASH_TIME = 2000; // 2 seconds

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

        // Using a Handler to delay the transition
        new Handler().postDelayed(() -> {
            // Start BottomNavActivity after SPLASH_TIME
//            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            Intent intent = new Intent(SplashActivity.this, BottomNavActivity.class);
            startActivity(intent);
            finish(); // Finish SplashActivity
        }, SPLASH_TIME);
    }
}
