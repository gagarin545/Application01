package ru.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.example.application01.R;

public class SecondActivity extends AppCompatActivity {

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView tv = findViewById(R.id.TV1);
        String incident = getIntent().getStringExtra("word");
        tv.setText(Html.fromHtml(incident));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}
