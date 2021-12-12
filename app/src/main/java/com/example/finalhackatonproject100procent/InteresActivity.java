package com.example.finalhackatonproject100procent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InteresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interes);
    }

    public void goHome(View view){
        Intent in = new Intent(InteresActivity.this,MainActivity.class);
        startActivity(in);
    }

    public void goProfile(View view){
        if(CheckInteresting.checkProfile==false) {
            Intent intent = new Intent(InteresActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(InteresActivity.this, ProfileActivity2.class);
            startActivity(intent);
        }
    }
}