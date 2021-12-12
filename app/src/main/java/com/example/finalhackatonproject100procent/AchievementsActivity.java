package com.example.finalhackatonproject100procent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AchievementsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    int count=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        recyclerView = findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        proverka();
        loadProduct();
    }
    public void loadProduct() {
        ApiService api = RetroClient.getApiService();

        Call<List<NewsView>> call = api.getNews();


        call.enqueue(new Callback<List<NewsView>>() {
            @Override
            public void onResponse(Call<List<NewsView>> call, Response<List<NewsView>> response) {
                List<NewsView> sort = response.body();
                Iterator<NewsView> it = sort.iterator();
                while (it.hasNext()){
                    if(it.next().getId()==proverka()){
                        continue;
                    }
                    else {
                        it.remove();
                    }
                }

                RVAdapter adapter1 = new RVAdapter(sort, getApplication());
                recyclerView.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call<List<NewsView>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{
        private Context context;

        List<NewsView> persons;

        RVAdapter(List<NewsView> newsView, Context context){
            this.persons = newsView;
            this.context = context;
        }

        @NonNull
        @Override
        public RVAdapter.PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1,parent,false);
            RVAdapter.PersonViewHolder pvh = new PersonViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(@NonNull RVAdapter.PersonViewHolder holder, int position) {
            Picasso.get().load(persons.get(position).getImage()).into(holder.image);
        }

        @Override
        public int getItemCount() {
            return persons.size();
        }
        class PersonViewHolder extends RecyclerView.ViewHolder{
            ImageView image;

            PersonViewHolder(View itemView){
                super(itemView);
                image = itemView.findViewById(R.id.imageView24);


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AchievementsActivity.this,ScannerActivity.class);
                        startActivity(intent);
                    }
                });

            }
        }
        @Override
        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView){
            super.onAttachedToRecyclerView(recyclerView);
        }

    }
    public void goProfile(View view) {
        if(CheckInteresting.checkProfile==false) {
            Intent intent = new Intent(AchievementsActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(AchievementsActivity.this, ProfileActivity2.class);
            startActivity(intent);

        }
    }
    public void goInt(View view){
        Intent in = new Intent(AchievementsActivity.this,InteresActivity.class);
        startActivity(in);
    }
    public void goHome(View view){
        Intent in = new Intent(AchievementsActivity.this,MainActivity.class);
        startActivity(in);
    }
    public  void goBack(View view){
        Intent intent = new Intent(AchievementsActivity.this,ProfileActivity2.class);
        startActivity(intent);
    }
    public int proverka(){
        for (int i = 0; i <10 ; i++) {
            if(CheckInteresting.check[i]==true){
                break;
            }
            else {count++;}
        }
        return count;
    }
}