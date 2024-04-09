package com.example.mysplashscreen;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mysplashscreen.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment implements UserObserver {

    private FragmentProfileBinding binding;
    private SharedPreferences sharedPreferences;

    User user = User.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateUI();

        String username = user.getEmail();
        TextView usernameTextView = binding.textView;
        usernameTextView.setText("@" + username);


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
    }

    // Update UI with current user data
    private void updateUI() {

        String coins = String.valueOf(user.getCoins());
        TextView coinTextView = binding.textView2;
        coinTextView.setText("coins: " + coins);
    }

    // Implement observer methods to handle updates
    @Override
    public void onUserUpdated(User user) {
        updateUI();
    }

    // Don't forget to unregister the observer when the fragment is destroyed
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Unregister this fragment as an observer of the User class
        User.getInstance().removeObserver(this);
    }
}
