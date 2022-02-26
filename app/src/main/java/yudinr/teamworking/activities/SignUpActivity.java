package yudinr.teamworking.activities;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import yudinr.teamworking.R;
import yudinr.teamworking.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private FirebaseAuth mAuth;

    private TextInputEditText inputName, inputEmail, inputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();

        mAuth = FirebaseAuth.getInstance();

        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        TextInputEditText inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        Button buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setEnabled(false);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (inputName.getText().length() > 0 && inputEmail.getText().length() > 0 &&
                inputPassword.getText().length() > 0 && inputConfirmPassword.getText().length() > 0) {
                    buttonSignUp.setEnabled(true);
                }
            }
        };
        inputName.addTextChangedListener(textWatcher);
        inputEmail.addTextChangedListener(textWatcher);
        inputPassword.addTextChangedListener(textWatcher);
        inputConfirmPassword.addTextChangedListener(textWatcher);

    }
    private void setListeners() {
        binding.buttonSignIn.setOnClickListener(view -> onBackPressed());
        binding.buttonSignUp.setOnClickListener(view -> userSignUp());
    }

    private void userSignUp() {
        String name = inputName.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmailAndPassword:success");
                            Toast.makeText(getApplicationContext(), "createUserWithEmailAndPassword:success",
                                    Toast.LENGTH_SHORT).show();
                            mAuth.signOut();
                            finish();
                        } else {
                            Log.w(TAG, "signUpWithEmailAndPassword:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "createUserWithEmailAndPassword:failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}