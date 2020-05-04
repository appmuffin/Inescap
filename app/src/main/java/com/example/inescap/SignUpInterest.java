package com.example.inescap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpInterest extends AppCompatActivity implements View.OnClickListener{

    String email, password, firstname, lastname, username, birthday, gender, userID, interest;
    Button finalSignUp;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseAuth mAuth1;
    UserInfo user;
    FirebaseUser currentUser;
    GridLayout mainGrid;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_interest);

        Intent intent = getIntent();
        email = intent.getStringExtra("EMAIL");
        password = intent.getStringExtra("PASSWORD");
        firstname = intent.getStringExtra("FIRSTNAME");
        lastname = intent.getStringExtra("LASTNAME");
        username = intent.getStringExtra("USERNAME");
        birthday = intent.getStringExtra("BIRTHDAY");
        gender = intent.getStringExtra("GENDER");
        finalSignUp = findViewById(R.id.butSignUp4);

        count= -1;
        interest="";
        mainGrid = (GridLayout)findViewById(R.id.mainGrid);

        for(int i=0;i<mainGrid.getChildCount();i++)
        {
            mainGrid.getChildAt(i).setOnClickListener(this);
        }



        mAuth1 = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("USER");

        finalSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    public void registerUser()
    {
        if(interest.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please select an interest",Toast.LENGTH_SHORT).show();
            return;
        }
        user = new UserInfo(firstname,lastname,username,birthday,gender,email,password,interest);


        mAuth1.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()  {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                    {
                        writeDatabase();

                    }
                else
                    {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
            }

        } );
    }

    public void writeDatabase()
    {
        currentUser = mAuth1.getCurrentUser();
        userID = currentUser.getUid();
        myRef.child(userID).setValue(user);
        //myRef.push().setValue(user);
        Toast.makeText(getApplicationContext(), "User Registered Successfully", Toast.LENGTH_SHORT).show();

        Intent intent1 = new Intent(SignUpInterest.this, MainActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent1);
    }

    View view1;
    @Override
    public void onClick(View view) {
        if(count<0)
        {
            view.setBackgroundColor(Color.parseColor("#F8FCA9"));
            view1 = view;
            count++;
        }
        else
        {
            view.setBackgroundColor(Color.parseColor("#F8FCA9"));
            view1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            view1=view;
            count++;
        }
        switch(view1.getId()) {
            case R.id.PHOTOGRAPHY:
                interest = "PHOTOGRAPHY";
                break;
            case R.id.FINE_ARTS:
                interest = "FINE ARTS";
                break;
            case R.id.DANCE:
                interest = "DANCE";
                break;
            case R.id.MUSIC:
                interest = "MUSIC";
                break;
            case R.id.READING:
                interest = "READING";
                break;
            case R.id.WRITING:
                interest = "WRITING";
                break;
            case R.id.SPORTS:
                interest = "SPORTS";
                break;
        }


    }
}

