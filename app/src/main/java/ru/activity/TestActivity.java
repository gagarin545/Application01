package ru.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.util.Linkify;
import android.widget.TextView;
import com.example.application01.R;
import ru.entity.ViewTest;
import ru.util.AsyncGetTest;

public class TestActivity extends AppCompatActivity {
   ViewTest viewTest;
   public static  TextView tvTest;
    public static Handler handtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        tvTest = findViewById(R.id.test_TV);
        String incident = getIntent().getStringExtra("test");
        new AsyncGetTest( incident).execute();

    }
}
