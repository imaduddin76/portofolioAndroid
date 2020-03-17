package com.limaefdua.bootcamp.dialog;
//
// Created by maftuhin on 10/23/2019.
//

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.limaefdua.bootcamp.R;

public class InfoDialog extends Dialog {
    private TextView tvTitle, tvMessage;
    private Button btnOk;

    public InfoDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_info);

        tvMessage = findViewById(R.id.tvMessage);
        tvTitle = findViewById(R.id.tvTitle);
        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void setMesssage(String message){
        tvMessage.setText(message);
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }
}
