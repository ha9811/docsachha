package com.example.appdocsach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Docsach extends AppCompatActivity {
    TextView txtdocsach, txttenphan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docsach);
        txtdocsach = findViewById(R.id.txtdocsach);
        txttenphan = findViewById(R.id.txttenphan);
        GetInformation();
    }

    private void GetInformation() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundledocsach");
        String tenPhan = bundle.getString("tenphan_key");
        String text = bundle.getString("text_key");
        txttenphan.setText(tenPhan);
        txtdocsach.setText(text);
        txtdocsach.setMovementMethod(new ScrollingMovementMethod());
    }
}
