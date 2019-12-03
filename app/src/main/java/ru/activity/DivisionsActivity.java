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
import android.widget.Toast;

import com.example.application01.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ru.adapter.MyAdapterDivision;
import ru.entity.Division;

import static ru.activity.MainActivity.workers;
import static ru.util.GetExecut.getDivision;
import static ru.util.GetExecut.setDivision;

public class DivisionsActivity extends AppCompatActivity implements View.OnClickListener{
    public static List<Division> divisions = new ArrayList<>();
    HashSet<Integer> iddivision = new HashSet<>();
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisions);
        et = findViewById(R.id.Names);
        ListView choiceList = findViewById(R.id.listView2);
        findViewById(R.id.dbutton1).setOnClickListener(this);
        findViewById(R.id.dbutton2).setOnClickListener(this);
        try {
            divisions = getDivision();
            ArrayAdapter<Division> adapter = new MyAdapterDivision(this);
            choiceList.setAdapter(adapter);
        } catch (ExecutionException | InterruptedException  e) { e.printStackTrace();        }
        if(workers != null) et.setText( workers.getName());

        choiceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick (AdapterView < ? > parent, View v, int position, long id){
                boolean status = true;
                Log.e("Debug", "poz->" + String.valueOf(divisions.get(position).getIddivision()) + " " + divisions.get(position).getNamedivision());
                if(!iddivision.add(divisions.get(position).getIddivision()))
                    iddivision.removeIf(x-> x==divisions.get(position).getIddivision());
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
                if( et.getText().length() == 0) {
                    Toast toast = Toast.makeText(this, "Не введена Фамилию И.О.", Toast.LENGTH_LONG);
                    toast.show();
                }   else {
                    if(divisions.size() > 0 ) {
                        try {
                            workers = setDivision( iddivision, et.getText().toString());
                        } catch (ExecutionException | InterruptedException e) { e.printStackTrace();                      }
                    } onStop();
                }
                break;
            case R.id.dbutton2:
                onStop();
                break;
        }
    }
}