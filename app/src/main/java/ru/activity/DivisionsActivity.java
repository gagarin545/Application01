package ru.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.application01.R;

import java.util.ArrayList;

import ru.service.setDivision;

import static ru.Api.Constants.Debug;

public class DivisionsActivity extends AppCompatActivity implements View.OnClickListener{
    ArrayList<Integer> iddivision = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisions);
        String[] division = getIntent().getStringArrayExtra("word");
        ListView choiceList = (ListView) findViewById(R.id.listView1);
        findViewById(R.id.dbutton1).setOnClickListener(this);
        findViewById(R.id.dbutton2).setOnClickListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, division);
        choiceList.setAdapter(adapter);

        choiceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick (AdapterView < ? > parent, View v, int position, long id){
                boolean status = true;
                for( int i = 0 ; i < iddivision.size(); i++)
                    if (position == iddivision.get(i)) {
                        iddivision.remove(i);
                        status = false;
                    }
                if( status)
                    iddivision.add(position);

                iddivision.forEach( di -> Log.e(Debug ,">>" + String.valueOf(di)));
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dbutton1:
                Log.e(Debug, String.valueOf(v.getId()));
                EditText et = findViewById(R.id.Names);
                setDivision setdivision = new setDivision( iddivision, et.getText().toString() );
                setdivision.setdivision();
                onStop();
                break;
            case R.id.dbutton2:
                onStop();
                break;
        }
    }
}