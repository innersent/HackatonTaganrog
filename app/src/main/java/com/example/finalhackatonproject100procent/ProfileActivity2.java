package com.example.finalhackatonproject100procent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
    }
    public void goInt(View view){
        Intent in = new Intent(ProfileActivity2.this,InteresActivity.class);
        startActivity(in);
    }
    public void goHome(View view){
        Intent in = new Intent(ProfileActivity2.this,MainActivity.class);
        startActivity(in);
    }
    public void goAchiv(View view){
        Intent in = new Intent(ProfileActivity2.this,AchievementsActivity.class);
        startActivity(in);
    }
}