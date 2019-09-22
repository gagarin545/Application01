package ru.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.application01.R;

import java.util.ArrayList;

import static ru.Api.Constants.Debug;

public class SecondActivity extends AppCompatActivity {
    ArrayList<Integer> iddivision = new ArrayList<>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String[] division = getIntent().getStringArrayExtra("word");
        ListView choiceList = (ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, division);
        choiceList.setAdapter(adapter);
        choiceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent, View v, int position, long id){
                iddivision.add(position);
                Log.e(Debug , String.valueOf(position));
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();

     //   setDivision setdivision = new setDivision( iddivision);
       // setdivision.setdivision();

    }


}
