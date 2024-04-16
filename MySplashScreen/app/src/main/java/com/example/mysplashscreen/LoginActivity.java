package com.example.mysplashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mysplashscreen.databinding.ActivityLoginBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pecpals-84281-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = database.getReference().child("users");

        binding.loginButton.setOnClickListener(view -> {
            String email = binding.loginEmail.getText().toString();
            String password = binding.loginPassword.getText().toString();

            if (email.equals("") || password.equals(""))
                Toast.makeText(LoginActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
            else {
                authenticateUser(email, password);
            }
        });

        binding.signupRedirectText.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    private void authenticateUser(String email, String password) {
        databaseReference.child(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user != null && user.getPassword() != null && user.getPassword().equals(password)) {
                        User.getInstance().setEmail(email);
                        User.getInstance().setCoins(user.getCoins());
                        User.getInstance().setXp(user.getXp());
                        User.getInstance().setLoginStreak(user.getLoginStreak());
                        User.getInstance().setPassword(user.getPassword());
                        Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), BottomNavActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}