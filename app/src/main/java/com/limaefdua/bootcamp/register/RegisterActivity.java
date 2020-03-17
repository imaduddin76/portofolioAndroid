package com.limaefdua.bootcamp.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.limaefdua.bootcamp.R;
import com.limaefdua.bootcamp.room.BcDatabase;
import com.limaefdua.bootcamp.room.table.User;
import com.limaefdua.bootcamp.sqlite.SQLiteDB;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteDB db;
    BcDatabase dbRoom;
    Button btnRegister;
    EditText etUsername, etPassword, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbRoom = BcDatabase.getDatabase(this);
        db = new SQLiteDB(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                User user = new User();
                user.username = etUsername.getText().toString();
                user.password = etPassword.getText().toString();
                user.email = etEmail.getText().toString();
                long insert = dbRoom.userDao().register(user);
//                long insert = db.register(user);
                if (insert > 0) {
                    Toast.makeText(this, "Register Success.", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(this, "Register Failed.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
