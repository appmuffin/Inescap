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

public class SignUpEmail extends AppCompatActivity {

    EditText enteredEmail;
    Button next;
    FirebaseAuth mAuth;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_email);

        mAuth = FirebaseAuth.getInstance();
        progressbar = (ProgressBar) findViewById(R.id.progressBar1);
        enteredEmail = (EditText)findViewById(R.id.editTextEmail);
        next = findViewById(R.id.butSignUp1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEmail();
            }
        });
    }

    public void checkEmail()
    {
        final String email = enteredEmail.getText().toString().trim();
        if(email.isEmpty())
        {
            enteredEmail.setError("Email is required");
            enteredEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            enteredEmail.setError("Please enter a valid email");
            enteredEmail.requestFocus();
            return;
        }
        progressbar.setVisibility(View.VISIBLE);
        mAuth.fetchSignInMethodsForEmail(enteredEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                boolean check = !task.getResult().getSignInMethods().isEmpty();
                progressbar.setVisibility(View.GONE);
                if(check)
                {
                    Toast.makeText(getApplicationContext(),"Email is already registered",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(SignUpEmail.this, SignUpPassword.class);
                    intent.putExtra("EMAIL",email);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }

            }
        });
    }

}

