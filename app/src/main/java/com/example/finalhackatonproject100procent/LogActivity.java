package com.example.finalhackatonproject100procent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogActivity extends AppCompatActivity {

    EditText regNum;
    DatabaseReference mDataBase;
    String USER_TAG = "User";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        regNum = findViewById(R.id.editTextPhone);
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_TAG);
    }

    public void goProfile(View view){
        CheckInteresting.checkProfile = true;
        Intent i = new Intent(LogActivity.this,ProfileActivity2.class);
        startActivity(i);
    }

    public void register(View view) {
        Intent intent = new Intent(LogActivity.this,RegActivity.class);
        startActivity(intent);
    }
}