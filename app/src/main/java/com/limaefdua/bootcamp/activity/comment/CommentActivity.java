package com.limaefdua.bootcamp.activity.comment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.limaefdua.bootcamp.R;
import com.limaefdua.bootcamp.utils.DateUtils;
import com.limaefdua.bootcamp.utils.Preferences;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Button btnPost;
    FirebaseDatabase database;
    DatabaseReference comment;
    List<CommentModel> data = new ArrayList<>();
    CommentAdapter adapter;
    RecyclerView rvComment;
    EditText etComment;
    Preferences preference;
    String id = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        toolbar = findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preference = new Preferences(this);
        setTitle("COMMENT");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
        }

        etComment = findViewById(R.id.etComment);
        rvComment = findViewById(R.id.rvComment);
        rvComment.setLayoutManager(new LinearLayoutManager(this));
//        rvComment.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new CommentAdapter(this, data);
        rvComment.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
        comment = database.getReference("comment").child(id);

        comment.limitToLast(100).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.getValue() != null) {
                    try {
                        CommentModel cmd = dataSnapshot.getValue(CommentModel.class);
                        data.add(cmd);
                        adapter.notifyDataSetChanged();
                        rvComment.scrollToPosition(data.size() - 1);
                        setTitle("COMMENT (" + data.size() + ")");
                    } catch (Exception e) {
                        Log.w("asda", e.getMessage());
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnPost = findViewById(R.id.btnPost);
        btnPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnPost) {

            CommentModel cm = new CommentModel();
            cm.setUsername(preference.getUsername());
            cm.setComment(etComment.getText().toString());
            cm.setDate(new DateUtils().getDateNow());

            comment.push().setValue(cm);
            etComment.setText("");
        }
    }
}
