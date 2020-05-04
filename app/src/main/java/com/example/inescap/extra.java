package com.example.inescap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class extra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

        Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");
        String gender = intent.getStringExtra("GENDER");
        String password = intent.getStringExtra("PASSWORD");
        String firstname = intent.getStringExtra("FIRSTNAME");
        String lastname = intent.getStringExtra("LASTNAME");
        String username = intent.getStringExtra("USERNAME");
        String birthday = intent.getStringExtra("BIRTHDAY");
        TextView text = findViewById(R.id.etextView);
        TextView t = findViewById(R.id.textView9);
        TextView twt = findViewById(R.id.textView10);
        TextView tte = findViewById(R.id.textView11);
        TextView ttg = findViewById(R.id.textView12);
        TextView tts = findViewById(R.id.textView13);
        TextView ter = findViewById(R.id.textView14);


        twt.setText(firstname);
        ter.setText(gender);
        tte.setText(lastname);
        ttg.setText(username);
        tts.setText(birthday);
        t.setText(password);
        text.setText(email);
    }
}
