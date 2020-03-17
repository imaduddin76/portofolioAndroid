package com.limaefdua.bootcamp.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.limaefdua.bootcamp.R;
import com.limaefdua.bootcamp.auth.LoginActivity;
import com.limaefdua.bootcamp.dialog.InfoDialog;
import com.limaefdua.bootcamp.main.model.Film;
import com.limaefdua.bootcamp.network.ApiClient;
import com.limaefdua.bootcamp.utils.Preferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    MovieAdapter adapter;
    FloatingActionButton fabLogout;
    Preferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preference = new Preferences(this);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String username = extra.getString("username");
            Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
        }

        rv = findViewById(R.id.recycleView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        fabLogout = findViewById(R.id.fabLogout);
        fabLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preference.setIsLogin(false);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://limaefdua.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient api = retrofit.create(ApiClient.class);

        api.movieList().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                List<Film> data = response.body();
                adapter = new MovieAdapter(MainActivity.this, data);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                InfoDialog dialog = new InfoDialog(MainActivity.this);
                dialog.show();
                dialog.setTitle("ERROR");
                dialog.setMesssage(t.getMessage() + "");
            }
        });
    }
}
