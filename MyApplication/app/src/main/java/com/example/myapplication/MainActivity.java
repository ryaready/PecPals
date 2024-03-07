import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_add_activity:
                        // Handle add activity button click
                        // For example, start an activity to add a new activity
                        break;
                    case R.id.navigation_progress:
                        // Handle progress button click
                        break;
                    case R.id.navigation_daily_challenge:
                        // Handle daily challenge button click
                        break;
                    case R.id.navigation_resources:
                        // Handle resources button click
                        break;
                    case R.id.navigation_mini_games:
                        // Handle mini games button click
                        break;
                }
                return true;
            }
        });
    }
}
