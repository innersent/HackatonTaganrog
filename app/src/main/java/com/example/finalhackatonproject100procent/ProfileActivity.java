package com.example.finalhackatonproject100procent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
    public void goInt(View view){
        Intent in = new Intent(ProfileActivity.this,InteresActivity.class);
        startActivity(in);
    }
    public void goHome(View view){
        Intent in = new Intent(ProfileActivity.this,MainActivity.class);
        startActivity(in);
    }
    public void login(View view){
        Intent in = new Intent(ProfileActivity.this,LogActivity.class);
        startActivity(in);
    }
}