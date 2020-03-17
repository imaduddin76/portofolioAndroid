package com.limaefdua.bootcamp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.limaefdua.bootcamp.R;
import com.limaefdua.bootcamp.activity.comment.CommentActivity;
import com.limaefdua.bootcamp.main.model.Film;

public class DetailActivity extends AppCompatActivity {
    Toolbar appBar;
    ImageView img;
    TextView tvDetail;
    Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            film = new Gson().fromJson(extras.getString("data"), Film.class);
        }
        initView();
    }

    private void initView() {
        appBar = findViewById(R.id.appBar);
        setSupportActionBar(appBar);
        setTitle(film.getTitle());
//        getSupportActionBar().setSubtitle(film.getCategoryName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        img = findViewById(R.id.img);
        tvDetail = findViewById(R.id.tvDetail);

        Glide.with(this)
                .load(film.getImage())
                .placeholder(R.drawable.ic_video)
                .error(R.drawable.ic_video)
                .centerCrop()
                .into(img);

        tvDetail.setText(film.getSynopsis());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_comment:
                Intent intent = new Intent(DetailActivity.this, CommentActivity.class);
                intent.putExtra("id", String.valueOf(film.getMovie_id()));
                startActivity(intent);
                break;
            case R.id.action_share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, tvDetail.getText().toString());
                share.putExtra(Intent.EXTRA_SUBJECT, film.getTitle());
                startActivity(Intent.createChooser(share, "Share To"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
