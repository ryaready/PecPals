package com.example.mysplashscreen;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.mysplashscreen.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("loginPrefs", MODE_PRIVATE);

        // Set click listener for the logout button
        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mark user as logged out
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();

                // Redirect to Login Activity
                Intent intent = new Intent(requireActivity(), LoginActivity.class);
                startActivity(intent);

                // Finish all activities and exit the app
                requireActivity().finishAffinity();
            }
        });

        return view;
    }

}