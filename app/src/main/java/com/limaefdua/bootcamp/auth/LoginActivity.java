package com.limaefdua.bootcamp.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.limaefdua.bootcamp.dialog.InfoDialog;
import com.limaefdua.bootcamp.main.MainActivity;
import com.limaefdua.bootcamp.R;
import com.limaefdua.bootcamp.register.RegisterActivity;
import com.limaefdua.bootcamp.utils.Preferences;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView {
    Button btnLogin, btnCancel;
    LoginPresenter presenter;
    Preferences preference;
    EditText etUsername, etPassword;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preference = new Preferences(this);
        presenter = new LoginPresenter(this, this);

        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        tvRegister = findViewById(R.id.tvRegister);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnLogin.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                presenter.doLogin(etUsername.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.btnCancel:
                finish();
                break;
            case R.id.tvRegister:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            default:
                Toast.makeText(this, "No Action", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onLoginSuccess(String username) {
        preference.setIsLogin(true);
        preference.setUsername(username);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String message) {
        InfoDialog dialog = new InfoDialog(this);
        dialog.show();
        dialog.setMesssage(message);
        dialog.setTitle("LOGIN ERROR");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (preference.getIsLogin()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
