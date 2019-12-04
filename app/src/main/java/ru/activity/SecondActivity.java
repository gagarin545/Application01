package ru.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.example.application01.R;

import ru.util.CalloutLink;

import static ru.util.GetExecut.getTest;

public class SecondActivity extends AppCompatActivity  {
    public static TextView tv;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv = findViewById(R.id.TV1);
        String incident = getIntent().getStringExtra("incident");
        SpannableString commentsContent = new SpannableString(incident);
        commentsContent.setSpan(new CalloutLink(this, Color.parseColor("#4CAF50")), 0, incident.length(), 0);
        tv.setText(Html.fromHtml("<font COLOR='#33B5E5'><b>Инцидент: </b></font>"));
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.append(commentsContent );
        tv.append("\n");
        tv.append(Html.fromHtml(getIntent().getStringExtra("word")));

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(MainActivity.workers.getName() != null)
            setTitle(Html.fromHtml("<small><b>" + String.format(getString(R.string.app_name), MainActivity.workers.getName()) + "</font>"));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
