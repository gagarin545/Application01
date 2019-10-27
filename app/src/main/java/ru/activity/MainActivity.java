package ru.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.application01.R;

import java.util.List;

import ru.adapter.MyAdapterIncident;
import ru.entity.Incident;
import ru.service.GetDivision;
import ru.service.GetIncidents;
import ru.service.GetWorkers;
import ru.util.Control;

import static ru.Api.Constants.Debug;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Control control;
    Thread thread;
    GetWorkers worker;
    ListView lv;
    List <Incident> incidents;
    @SuppressLint({"HardwareIds", "HandlerLeak"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // control = new Control( );
        lv = findViewById(R.id.listView1);
        findViewById(R.id.BT1).setOnClickListener(this::onClick); // определение кнопки Сегодня
        findViewById(R.id.BT2).setOnClickListener(this::onClick); // определение кнопки Неделю
        findViewById(R.id.BT3).setOnClickListener(this::onClick); // Настроить
        worker = new GetWorkers( this, getImei());
        thread = new Thread(worker);
        thread.start();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent, View v, int position, long id){
                Log.e(Debug, String.valueOf(position));
                Intent intent = new Intent( MainActivity.this, SecondActivity.class);
                System.out.println(incidents.get(position).toString());
                intent.putExtra("word", incidents.get(position).toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e( Debug, "main->onStart");
   }
    @Override
    protected void onStop() {
        super.onStop();

    }
    @Override
    public void onClick(View v) {
        Log.e( Debug, "onclik");
     //   control.SetCommand(new CallCommand(new Commands(this, Imei)));
      //  control.press(v.getId());
        switch (v.getId()){
            case R.id.BT1:

                while(thread.isAlive()) {}
                GetIncidents getIncidents = new GetIncidents(worker.getwork().getIddivision());
                thread = new Thread( getIncidents);
                thread.start();
                while(thread.isAlive()) {}
                incidents = getIncidents.getIncident();
                ArrayAdapter <Incident> adapter = new MyAdapterIncident(this );
                lv.setAdapter(adapter);
                //   System.out.println(workers.getName());
                Log.e(Debug, String.valueOf(incidents.size()));
                break;
            case R.id.BT2:
                Log.e(Debug, "On week" );
                break;
            case R.id.BT3:
                Log.e(Debug, "Setting" );
                new GetDivision( this).getdivision();
                break;
        }
    }
    @SuppressLint("HardwareIds")
    private String getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
            return Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        return telephonyManager.getDeviceId();
    }

}
