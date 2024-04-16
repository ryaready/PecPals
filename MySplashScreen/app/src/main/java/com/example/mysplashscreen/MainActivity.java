package com.example.mysplashscreen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private User user = User.getInstance();
    String email = user.getEmail();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pecpals-84281-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = database.getReference().child("users");

        loadUserData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (email != null) {
            user.saveUserData(user);
        }
    }


    private void loadUserData() {
        String email = user.getEmail();
        databaseReference.child(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User userFromDatabase = dataSnapshot.getValue(User.class);
                    if (userFromDatabase != null) {
                        user.setCoins(userFromDatabase.getCoins());
                        user.setXp(userFromDatabase.getXp());
                        user.setLoginStreak(userFromDatabase.getLoginStreak());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors
            }
        });
    }

}