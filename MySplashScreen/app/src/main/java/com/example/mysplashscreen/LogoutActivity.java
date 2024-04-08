package com.example.mysplashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.mysplashscreen.databinding.FragmentProfileBinding;


public class LogoutActivity extends ProfileFragment {

    private FragmentProfileBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        getContext();

        // Initialize SharedPreferences
        sharedPreferences = (SharedPreferences) getSharedElementEnterTransition();

        // Set click listener for the logout button
        binding.logoutButton.setOnClickListener(view -> {
            try {
                onClick(view);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (java.lang.InstantiationException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void onClick(View view) throws IllegalAccessException, java.lang.InstantiationException {
        // Mark user as logged out
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply(); // Ensure changes are saved

        // Redirect to Login Activity
        Intent intent = new Intent(LoginActivity.class.newInstance(), LogoutActivity.this.getClass());
        startActivity(intent);

        // Close current activity (alternative to finishAffinity())
        try {
            finalize();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
