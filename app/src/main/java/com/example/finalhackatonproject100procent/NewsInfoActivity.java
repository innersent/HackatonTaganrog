package com.example.finalhackatonproject100procent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsInfoActivity extends AppCompatActivity {
    TextView title,info;
    ImageView image,imageLike;
    boolean checkLike =  false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_info);
        init();
        getIntent1();


    }
    public void init(){
        title = findViewById(R.id.title1);
        info = findViewById(R.id.info);
        image = findViewById(R.id.image);
        imageLike = findViewById(R.id.imageLike);
    }
    public void getIntent1(){
        Intent intent= getIntent();
        title.setText(intent.getStringExtra("title"));
        info.setText(intent.getStringExtra("info"));
        Picasso.get().load(intent.getStringExtra("image")).into(image);
    }
    public void goExit(View view){

            Intent i = new Intent(NewsInfoActivity.this, MainActivity.class);
            startActivity(i);

    }
    public void interesting(View view){
        Intent intent= getIntent();
        if(checkLike==false){
            checkLike=true;
            imageLike.setImageResource(R.drawable.likes);
            CheckInteresting.check[(intent.getIntExtra("id", 0)-1)]=true;
        }
        else if(checkLike==true){
            checkLike=false;
            imageLike.setImageResource(R.drawable.like);
        }
    }
}