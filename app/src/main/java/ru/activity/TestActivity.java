package ru.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.application01.R;

import org.junit.Assert;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import retrofit2.Call;
import retrofit2.Response;
import ru.entity.ViewTest;
import ru.service.GetTest;
import ru.util.AsyncGetTest;

import static ru.Api.Constants.api;
import static ru.util.GetExecut.executor;

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
        tvTest.setText("    Ждите тестирую " + incident + "\n");
        new AsyncGetTest( incident).execute();


    }
}
