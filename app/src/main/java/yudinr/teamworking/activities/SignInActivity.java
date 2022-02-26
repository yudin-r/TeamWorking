package yudinr.teamworking.activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import yudinr.teamworking.R;
import yudinr.teamworking.databinding.ActivitySignInBinding;

public class SignInActivity extends BaseActivity {

    private ActivitySignInBinding binding;
    private FirebaseAuth mAuth;

    private TextInputEditText inputEmail, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();

        mAuth = FirebaseAuth.getInstance();

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        Button buttonSignIn = findViewById(R.id.buttonSingIn);
        buttonSignIn.setEnabled(false);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (inputEmail.getText().length() > 0 && inputPassword.getText().length() > 0) {
                    buttonSignIn.setEnabled(true);
                }
            }
        };
        inputEmail.addTextChangedListener(textWatcher);
        inputPassword.addTextChangedListener(textWatcher);


    }
    private void setListeners() {
        binding.buttonCreateNewAcc.setOnClickListener(view ->
                startActivity(new Intent(this, SignUpActivity.class)));
        binding.buttonSingIn.setOnClickListener(view -> userSignIn());
    }

    private void userSignIn() {
        Intent intent = new Intent(this, MainActivity.class);
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmailAndPassword:success");
                            Toast.makeText(getApplicationContext(), "signInWithEmailAndPassword:successful.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(intent);
                            finish();
                        } else {
                            Log.w(TAG, "signInWithEmailAndPassword:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "signInWithEmailAndPassword:failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}