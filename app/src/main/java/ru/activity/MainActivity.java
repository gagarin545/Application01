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
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.application01.R;
import com.github.mikephil.charting.data.BarData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ru.adapter.ChartDataAdapter;
import ru.adapter.MyAdapterIncident;
import ru.adapter.MyAdapterIncidentList;
import ru.adapter.MyAdapterIncidentRepet;
import ru.entity.Incident;
import ru.entity.Result;
import ru.entity.WorkerResult;
import ru.service.GetDivision;
import ru.service.GetIncidentListDay;
import ru.service.GetIncidentRepet;
import ru.service.GetIncidents;
import ru.service.GetResult;
import ru.service.GetWorkerResult;
import ru.service.GetWorkers;
import ru.util.GenerateData;

import static ru.Api.Constants.Imei;
import static ru.Api.Constants.change;

public class MainActivity extends AppCompatActivity {
    Thread thread;
    ChartDataAdapter chartDataAdapter;
    MyAdapterIncident adapter;
    ListView lv;
    BarData date;
    Toast toast;
    public static GetWorkers worker;
    List<Result> res;
    private List <Incident> incidents;
    int day = 30;
    @SuppressLint({"HardwareIds", "HandlerLeak"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listView1);
        Imei = getImei();
        getWorker();
        if(worker.getwork() != null) {
            setTitle(Html.fromHtml("<small><b>" + String.format(getString(R.string.app_name), worker.getwork().getName()) + "</font>"));
            GetIncidents getIncidents = new GetIncidents(worker.getwork().getIddivision());
            thread = new Thread(getIncidents);
            thread.start();
            while (thread.isAlive()) {
            }
            incidents = getIncidents.getIncident();
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent, View v, int position, long id){
                Intent intent = new Intent( MainActivity.this, SecondActivity.class);
                intent.putExtra("word", incidents.get(position).toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if( change) getWorker();
   }
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ArrayList<BarData> list = new ArrayList<>();
        GetResult getResult;
        if( change) getWorker();
        switch (item.getItemId()) {
            case R.id.today:
                GetIncidents getIncidents = new GetIncidents(worker.getwork().getIddivision());
                thread = new Thread( getIncidents);
                thread.start();
                while(thread.isAlive()) {}
                incidents = getIncidents.getIncident();
                toast = Toast.makeText(this, "Нарядов " + incidents.size() , Toast.LENGTH_LONG);
                toast.show();
                adapter = new MyAdapterIncident(this);
                lv.setAdapter(adapter);
                return true;
            case R.id.weekOnOrder:
                getResult= new GetResult(worker.getwork().getIddivision());
                thread = new Thread(getResult);
                thread.start();
                while(thread.isAlive()) {}
                res = getResult.getResult();
                for(int i :worker.getwork().getIddivision())
                    if((date = new GenerateData().getBarData(res, i)) != null)
                        list.add( date);
                chartDataAdapter = new ChartDataAdapter(getApplicationContext(), list);
                lv.setAdapter(chartDataAdapter);
                return true;
            case R.id.weekOnWorkers:
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
                return true;
            case R.id.Setting:
                new GetDivision( this).getdivision();
                return true;
            case R.id.Nday:
                GetIncidentListDay getIncidentListDay = new GetIncidentListDay(worker.getwork().getIddivision(), day);
                thread = new Thread( getIncidentListDay);
                thread.start();
                while(thread.isAlive()) {}
                incidents = getIncidentListDay.getIncident();
                toast = Toast.makeText(this, "Нарядов " + incidents.size() , Toast.LENGTH_LONG);
                toast.show();
                MyAdapterIncidentList adapters = new MyAdapterIncidentList(this );
                lv.setAdapter(adapters);
                return true;
            case R.id.Repet:
                GetIncidentRepet incidentRepet = new GetIncidentRepet(worker.getwork().getIddivision());
                thread = new Thread( incidentRepet);
                thread.start();
                while(thread.isAlive()) {}
                incidents = incidentRepet.getIncidentRepet();
                toast = Toast.makeText(this, "Нарядов " + incidents.size() , Toast.LENGTH_LONG);
                toast.show();
                MyAdapterIncidentRepet adapterRepet = new MyAdapterIncidentRepet(this );
                lv.setAdapter(adapterRepet);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("HardwareIds")
    private String getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
            return Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        return telephonyManager.getDeviceId();
    }
    void getWorker() {
        worker = new GetWorkers( this, Imei);
        thread = new Thread(worker);
        thread.start();
        while(thread.isAlive()) {}
        change = false;
    }
}
