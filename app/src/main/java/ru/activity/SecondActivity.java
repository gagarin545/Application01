package ru.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.example.application01.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView tv = findViewById(R.id.TV1);
        String incident = getIntent().getStringExtra("word");
        tv.setText(Html.fromHtml(incident));
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(MainActivity.worker.getwork().getName() != null)
            setTitle(Html.fromHtml("<small><b>" + String.format(getString(R.string.app_name), MainActivity.worker.getwork().getName()) + "</font>"));
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
}
