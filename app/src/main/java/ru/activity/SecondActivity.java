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
    TextView tv;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String color = "#4CAF50";
        setContentView(R.layout.activity_second);
        tv = findViewById(R.id.TV1);

        String incident = getIntent().getStringExtra("incident");
        if( incident == null )
            tv.setText(Html.fromHtml(getTest(getIntent().getStringExtra( "test" ))));
        else {
            tv.setText(Html.fromHtml("<font COLOR='#33B5E5'><b>Инцидент: </b></font>"));
            Log.e("DEbug", "->" + incident);
            append_point(incident);
            tv.append("\n");
            tv.append(Html.fromHtml(getIntent().getStringExtra("word")));
        }
    }
    void append_point(String s) {
        //   Log.e(debug, "DSL -> " + s);
        String color = "#4CAF50";
        SpannableString commentsContent = new SpannableString(s);
        commentsContent.setSpan(new CalloutLink(this, Color.parseColor(color)), 0, s.length(), 0);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.append(commentsContent );
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(MainActivity.workers.getName() != null)
            setTitle(Html.fromHtml("<small><b>" + String.format(getString(R.string.app_name), MainActivity.workers.getName()) + "</font>"));
    }

}
