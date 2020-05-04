package com.example.inescap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class SignUpPassword extends AppCompatActivity {

    EditText enteredPassword;
    Button next;
    String emailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_password);

        enteredPassword = (EditText)findViewById(R.id.editTextPassword);
        next = findViewById(R.id.butSignUp2);

        Intent intent = getIntent();
        emailid = intent.getStringExtra("EMAIL");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPassword();
            }
        });
    }

    public void checkPassword()
    {
        String password = enteredPassword.getText().toString().trim();
        if(password.isEmpty())
        {
            enteredPassword.setError("Password is required");
            enteredPassword.requestFocus();
            return;
        }

        if(password.length()<6)
        {
            enteredPassword.setError("Minimum length of password should be 6");
            enteredPassword.requestFocus();
            return;
        }


        Intent intent = new Intent(SignUpPassword.this, SignUpDetails.class);
        intent.putExtra("EMAIL",emailid);
        intent.putExtra("PASSWORD",password);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
