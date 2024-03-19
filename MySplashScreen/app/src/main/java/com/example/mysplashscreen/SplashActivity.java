package com.example.mysplashscreen;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

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
    }
}

