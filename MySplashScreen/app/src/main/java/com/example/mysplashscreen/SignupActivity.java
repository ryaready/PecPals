package com.example.mysplashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mysplashscreen.databinding.ActivitySignupBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pecpals-84281-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = database.getReference().child("users");

        binding.signupButton.setOnClickListener(view -> {
            String email = binding.signupEmail.getText().toString();
            String password = binding.signupPassword.getText().toString();
            String confirmPassword = binding.signupConfirm.getText().toString();

            if (email.equals("") || password.equals("") || confirmPassword.equals(""))
                Toast.makeText(SignupActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
            else {
                if (password.equals(confirmPassword)) {
                    createUser(email, password);
                } else {
                    Toast.makeText(SignupActivity.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.loginRedirectText.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void createUser(String email, String password) {
        databaseReference.child(email).get().addOnSuccessListener(dataSnapshot -> {
            if (dataSnapshot.exists()) {
                Toast.makeText(SignupActivity.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
            } else {
                User user = User.getInstance();
                user.setEmail(email);
                user.setPassword(password);
                user.setCoins(0);
                user.setXp(0);
                user.setLoginStreak(0);

                databaseReference.child(email).setValue(user)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(SignupActivity.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        })
                        .addOnFailureListener(e -> Toast.makeText(SignupActivity.this, "Signup Failed!", Toast.LENGTH_SHORT).show());
            }
        }).addOnFailureListener(e -> Toast.makeText(SignupActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}