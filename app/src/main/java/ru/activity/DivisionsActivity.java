package ru.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.example.application01.R;
import java.util.ArrayList;
import ru.service.SetDivision;

public class DivisionsActivity extends AppCompatActivity implements View.OnClickListener{
    ArrayList<Integer> iddivision = new ArrayList<>() ;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisions);
        String[] division = getIntent().getStringArrayExtra("word");
        int[] division_poz =  getIntent().getIntArrayExtra("poz");
        et = findViewById(R.id.Names);
        ListView choiceList = findViewById(R.id.listView2);
        findViewById(R.id.dbutton1).setOnClickListener(this);
        findViewById(R.id.dbutton2).setOnClickListener(this);
        if(MainActivity.workers != null) et.setText( MainActivity.workers.getName());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item, division);
        choiceList.setAdapter(adapter);
        choiceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick (AdapterView < ? > parent, View v, int position, long id){
                boolean status = true;
                for( int i = 0 ; i < iddivision.size(); i++)
                    if (division_poz[position] == iddivision.get(i)) {
                        iddivision.remove(i);
                        status = false;
                    }
                if( status)
                    iddivision.add(division_poz[position]);
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
                if( et.getText().length() > 0 && iddivision.size() > 0 ) {
                    SetDivision setdivision = new SetDivision(iddivision, et.getText().toString());
                    setdivision.setdivision();
                }
                onStop();
                break;
            case R.id.dbutton2:
                onStop();
                break;
        }
    }
}