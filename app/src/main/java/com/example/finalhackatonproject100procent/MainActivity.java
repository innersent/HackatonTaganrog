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

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

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

                MainActivity.RVAdapter adapter1 = new MainActivity.RVAdapter(sort, getApplication());
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
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
            PersonViewHolder pvh = new PersonViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(@NonNull RVAdapter.PersonViewHolder holder, int position) {
            holder.title.setText(persons.get(position).getTitle());
            holder.time.setText(persons.get(position).getTime());
            holder.comment.setText(String.valueOf(persons.get(position).getComment()));
            holder.like.setText(String.valueOf(persons.get(position).getLike()));
            Picasso.get().load(persons.get(position).getImage()).into(holder.image);
        }

        @Override
        public int getItemCount() {
            return persons.size();
        }
        class PersonViewHolder extends RecyclerView.ViewHolder{
            TextView title,time,comment,like;
            ImageView image;


            PersonViewHolder(View itemView){
                super(itemView);
                title = itemView.findViewById(R.id.info);
                time = itemView.findViewById(R.id.miniInfo);
                image = itemView.findViewById(R.id.image);
                comment = itemView.findViewById(R.id.dislike);
                like = itemView.findViewById(R.id.like);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int positionIndex = getAdapterPosition();

                        NewsView newsView = persons.get(positionIndex);
                        Intent intent = new Intent(MainActivity.this,NewsInfoActivity.class);
                        intent.putExtra("title",newsView.getTitle());
                        intent.putExtra("id",newsView.getId());
                        intent.putExtra("time",newsView.getTime());
                        intent.putExtra("image",newsView.getImage());
                        intent.putExtra("comment",newsView.getComment());
                        intent.putExtra("info",newsView.getInfo());
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
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(MainActivity.this, ProfileActivity2.class);
            startActivity(intent);

        }
    }
    public void goInt(View view){
        Intent in = new Intent(MainActivity.this,InteresActivity.class);
        startActivity(in);
    }
}