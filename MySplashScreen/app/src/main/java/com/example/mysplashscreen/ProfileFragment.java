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

                user.saveUserData(user);
                Intent intent = new Intent(requireActivity(), LoginActivity.class);
                startActivity(intent);

                requireActivity().finishAffinity();
            }
        });
    }

    private void updateUI() {
        String coins = String.valueOf(user.getCoins());
        TextView coinTextView = binding.textView2;
        coinTextView.setText(coins);

        String xp = String.valueOf(user.getXp());
        TextView xpTextView = binding.textView3;
        xpTextView.setText(xp);

        String ls = String.valueOf(user.getLoginStreak());
        TextView lsTextView = binding.textView4;
        lsTextView.setText(ls);

        String level = String.valueOf(user.getLevelState());
        TextView lvlTextView = binding.level_value;
        lvlTextView.setText(level);


    }


    @Override
    public void onUserUpdated(User user) {

        updateUI();
    }

    @Override
    public void onDestroyView() {
        user.saveUserData(user);
        super.onDestroyView();
        User.getInstance().removeObserver(this);
    }
}
