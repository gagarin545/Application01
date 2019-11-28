package ru.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.application01.R;

import ru.util.CalloutLink;

public class SecondActivity extends AppCompatActivity  implements View.OnClickListener {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String color = "#4CAF50";
        setContentView(R.layout.activity_second);
        tv = findViewById(R.id.TV1);
        tv.setText(Html.fromHtml("<font COLOR='#33B5E5'><b>Инцидент: </b></font>"));
        String incident = getIntent().getStringExtra("incident");
        Log.e("DEbug", "->" + incident);
        append_point(incident);
        tv.append("\n");
        tv.append(Html.fromHtml(getIntent().getStringExtra("word")));

     //   SpannableString commentsContent = new SpannableString( incident);
      //  commentsContent.setSpan(new CalloutLink( this, Color.parseColor(color)), 0, incident.length(), 0);
    //    tv.setMovementMethod(LinkMovementMethod.getInstance());
     //   tv.append(commentsContent + "\n");

    //    Log.e("DEbug", incident.substring(43,54));



        //tv.setText(Html.fromHtml(commentsContent.toString()));
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
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        Log.e("DEbug", String.valueOf(v.getId()));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
