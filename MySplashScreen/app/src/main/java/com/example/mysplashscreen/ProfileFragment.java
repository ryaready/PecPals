package com.example.mysplashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        TextView usernameTextView = view.findViewById(R.id.textView);

        User user = User.getInstance();

        String username = user.getInstance().getEmail();

        usernameTextView.setText("@" + username);

        TextView coinTextView = view.findViewById(R.id.textView2);

        String coins = String.valueOf(user.getInstance().getCoins());

        coinTextView.setText("coins: "+ coins);



        // Set click listener for the logout button
        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mark user as logged out

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