package com.example.finalhackatonproject100procent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

public class RegActivity2 extends AppCompatActivity {
    EditText regNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg2);
        regNum = findViewById(R.id.editTextPhone);
    }
    public void register(View view){
        int score = 0;
        DAOUser dao = new DAOUser();
        User user = new User(regNum.getText().toString(),score);
        dao.add(user);
//89281710514//834576
    }
    public void goProfile(View view){
        Intent i = new Intent(RegActivity2.this,ProfileActivity2.class);
        CheckInteresting.checkProfile=true;
        startActivity(i);
    }
}