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
import com.github.mikephil.charting.data.BarData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ru.adapter.ChartDataAdapter;
import ru.adapter.MyAdapterIncident;
import ru.entity.Incident;
import ru.entity.Result;
import ru.entity.WorkerResult;
import ru.service.GetDivision;
import ru.service.GetIncidents;
import ru.service.GetResult;
import ru.service.GetWorkerResult;
import ru.service.GetWorkers;
import ru.util.GenerateData;

import static ru.Api.Constants.Debug;
import static ru.Api.Constants.Imei;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Thread thread;
    ChartDataAdapter chartDataAdapter;
    ListView lv;
    BarData date;
    GetWorkers worker;
    List<Result> res;
    List <Incident> incidents;
    @SuppressLint({"HardwareIds", "HandlerLeak"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.listView1);
        findViewById(R.id.BT1).setOnClickListener(this::onClick); // определение кнопки Сегодня
        findViewById(R.id.BT2).setOnClickListener(this::onClick); // определение кнопки Неделю
        findViewById(R.id.BT3).setOnClickListener(this::onClick); // Работник за неделю
        findViewById(R.id.BT4).setOnClickListener(this::onClick); // Настроить
        Imei = getImei();
        worker = new GetWorkers( this, Imei);
        thread = new Thread(worker);
        thread.start();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent, View v, int position, long id){
      //          Log.e(Debug, String.valueOf(position));
                Intent intent = new Intent( MainActivity.this, SecondActivity.class);
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
                ArrayList<BarData> list = new ArrayList<>();
                GetResult getResult= new GetResult(worker.getwork().getIddivision());
                thread = new Thread(getResult);
                thread.start();
                while(thread.isAlive()) {}
                res = getResult.getResult();
                for(int i :worker.getwork().getIddivision())
                    if((date = new GenerateData().getBarData(res, i)) != null)
                        list.add( date);
                chartDataAdapter = new ChartDataAdapter(getApplicationContext(), list);
                lv.setAdapter(chartDataAdapter);
                Log.e(Debug, "On week" );
                break;
            case R.id.BT3:
                list = new ArrayList<>();
                Calendar calendar = Calendar.getInstance();
                GetWorkerResult getWorkerResult = new GetWorkerResult( worker.getwork().getIddivision());
                thread = new Thread(getWorkerResult);
                thread.start();
                while(thread.isAlive()) {}
                List<WorkerResult> workerResult = getWorkerResult.getWorkerResult();
                calendar.setTimeInMillis(workerResult.get(0).getTimeclose());
                for(int i=0; i < 7; i++) {
                    if ((date = new GenerateData().getBarData(workerResult, calendar.getTimeInMillis())) != null)
                        list.add(date);
                    calendar.add(Calendar.DAY_OF_YEAR, 1);
                }
                chartDataAdapter = new ChartDataAdapter(getApplicationContext(), list);
                lv.setAdapter(chartDataAdapter);
                break;
            case R.id.BT4:
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
