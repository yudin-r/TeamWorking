package yudinr.teamworking.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import yudinr.teamworking.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
    String getCurrentUserID() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }



}