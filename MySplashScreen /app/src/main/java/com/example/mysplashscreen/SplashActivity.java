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
        imageView.setBackgroundResource(R.drawable.animation_splash);

        // Get AnimationDrawable object
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();

        // Start the animation
        animationDrawable.start();
    }
}
