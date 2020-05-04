package com.example.inescap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.os.Handler;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class SignUpDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    EditText editTextFirstName, editTextLastName, editTextUsername, editTextBirthday;
    TextView info;
    Spinner spinnerGender;
    Button next,check;
    public String dateString, firstname, lastname, username, birthday, emailid, password, gender;
    DatabaseReference myRef;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    UserInfo user1;
    Boolean flag;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_details);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextBirthday = findViewById(R.id.editTextBirthday);
        next = findViewById(R.id.butSignUp3);
        check = findViewById(R.id.check);
        spinnerGender = findViewById(R.id.spinnerGender);
        info = findViewById(R.id.usernameAvailable);

        Intent intent =getIntent();
        emailid = intent.getStringExtra("EMAIL");
        password = intent.getStringExtra("PASSWORD");

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Gender,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);
        spinnerGender.setOnItemSelectedListener(this);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUsername();
            }
        });

        editTextBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUsername();
                DialogFragment datePicker = new com.example.inescap.DatePicker();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUsername();

                checkDetails();
            }
        });
    }

    public void checkDetails()
    {
        firstname = editTextFirstName.getText().toString().trim();
        lastname = editTextLastName.getText().toString().trim();
        username = editTextUsername.getText().toString().trim();
        birthday = editTextBirthday.getText().toString().trim();



        if(firstname.isEmpty())
        {
            editTextFirstName.setError("First name is required");
            editTextFirstName.requestFocus();
            return;
        }
        if(lastname.isEmpty())
        {
            editTextLastName.setError("Last name is required");
            editTextLastName.requestFocus();
            return;
        }
        if(username.isEmpty())
        {
            editTextUsername.setError("Username is required");
            editTextUsername.requestFocus();
            return;
        }
        if(birthday.isEmpty())
        {
            editTextBirthday.setError("Birthday is required");
            editTextBirthday.requestFocus();
            return;
        }

        if(!flag)
        {
            editTextUsername.setError("Username has already been taken");
            editTextUsername.requestFocus();
            return;
        }



            Intent intent = new Intent(SignUpDetails.this, SignUpInterest.class);
            intent.putExtra("EMAIL", emailid);
            intent.putExtra("PASSWORD", password);
            intent.putExtra("FIRSTNAME", firstname);
            intent.putExtra("LASTNAME", lastname);
            intent.putExtra("USERNAME", username);
            intent.putExtra("BIRTHDAY", birthday);
            intent.putExtra("GENDER", gender);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            gender = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    String date;

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
        editTextBirthday.setText(currentDateString);
        dateString = Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth);
    }

    public void checkUsername()
    {
        username = editTextUsername.getText().toString().trim();
        if(username.isEmpty())
        {
            editTextUsername.setError("Username is required");
            editTextUsername.requestFocus();
            return;
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference().child("USER");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(checkIfUsernameExist(username,dataSnapshot))
                {

                    editTextUsername.requestFocus();
                    info.setVisibility(View.VISIBLE);
                    info.setText("Sorry The Username Has Already Been Taken");
                    flag=false;

                }
                else
                {
                    info.setVisibility(View.VISIBLE);
                    info.setText("Congratulation The Username Is Available");
                    flag=true;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public boolean checkIfUsernameExist(String USERNAME, DataSnapshot dataSnapshot)
    {
        UserInfo user1 = new UserInfo();
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            user1.setUsername(ds.getValue(UserInfo.class).getUsername());
            if(user1.getUsername().equals(USERNAME))
            {
                return true;
            }
        }
        return false;
    }
}
