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
import android.util.Log;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ru.adapter.ChartDataAdapter;
import ru.adapter.MyAdapterIncident;
import ru.adapter.MyAdapterIncidentList;
import ru.adapter.MyAdapterIncidentRepet;
import ru.entity.Incident;
import ru.entity.Result;
import ru.entity.WorkerResult;
import ru.entity.Workers;
import ru.service.GetDivision;
import ru.service.GetIncident;
import ru.service.GetIncidentListDay;
import ru.service.GetIncidentRepet;
import ru.service.GetResult;
import ru.service.GetWorker;
import ru.service.GetWorkerResult;
import ru.util.GenerateData;

import static ru.Api.Constants.Imei;
import static ru.Api.Constants.change;

public class MainActivity extends AppCompatActivity {
    public static List <Incident> incidents;
    public static ExecutorService executor = Executors.newFixedThreadPool(2);
    static Workers workers;
    Future<List<Incident>> future;
    ChartDataAdapter chartDataAdapter;
    MyAdapterIncident adapter;
    ListView lv;
    BarData date;
    Toast toast;
    int day = 30;

    @SuppressLint({"HardwareIds", "HandlerLeak"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listView1);
        Imei = getImei();

        getWorker();
        if(workers != null) {
            setTitle(Html.fromHtml("<small><b>" + String.format(getString(R.string.app_name), workers.getName()) + "</font>"));
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent, View v, int position, long id){
                Intent intent = new Intent( MainActivity.this, SecondActivity.class);
                intent.putExtra("word", incidents.get(position).toString());
                intent.putExtra("incident", String.valueOf(incidents.get(position).getN_incident()));
                Log.e("DEbug", String.valueOf(incidents.get(position).getN_incident() ));
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
        executor.shutdown();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ArrayList<BarData> list = new ArrayList<>();
        if( change) getWorker();
        switch (item.getItemId()) {
            case R.id.today:
                try {
                    future = executor.submit( (new GetIncident(workers.getIddivision())).task);
                     incidents = future.get();
                }  catch (ExecutionException|InterruptedException e) { e.printStackTrace(); }
                toast = Toast.makeText(this, "Нарядов " + incidents.size() , Toast.LENGTH_LONG);
                toast.show();
                adapter = new MyAdapterIncident(this);
                lv.setAdapter(adapter);
                return true;
            case R.id.weekOnOrder:
                Future <List<Result>> future_res = executor.submit((new GetResult(workers.getIddivision())).task);
                try {
                    for(int i :workers.getIddivision())
                        if((date = new GenerateData().getBarData(future_res.get(), i)) != null)
                            list.add( date);
                } catch (ExecutionException|InterruptedException e) { e.printStackTrace(); }
                chartDataAdapter = new ChartDataAdapter(getApplicationContext(), list);
                lv.setAdapter(chartDataAdapter);
                return true;
            case R.id.weekOnWorkers:
                list = new ArrayList<>();
                Calendar calendar = Calendar.getInstance();
                try {
                    Future <List<WorkerResult>> future_reswork = executor.submit((new GetWorkerResult(workers.getIddivision())).task);
                    List<WorkerResult> workerResult = future_reswork.get();
                    calendar.setTimeInMillis(workerResult.get(0).getTimeclose());
                    for(int i=0; i < 7; i++) {
                        if ((date = new GenerateData().getBarData(workerResult, calendar.getTimeInMillis())) != null)
                            list.add(date);
                        calendar.add(Calendar.DAY_OF_YEAR, 1);
                    }
                } catch (ExecutionException|InterruptedException e) { e.printStackTrace(); }
                chartDataAdapter = new ChartDataAdapter(getApplicationContext(), list);
                lv.setAdapter(chartDataAdapter);
                return true;
            case R.id.Setting:
                new GetDivision( this).getdivision();
                return true;
            case R.id.Nday:
                try {
                    future = executor.submit(new GetIncidentListDay(workers.getIddivision(), day).task);
                    incidents = future.get();
                } catch (ExecutionException|InterruptedException e) { e.printStackTrace(); }
                toast = Toast.makeText(this, "Нарядов " + incidents.size() , Toast.LENGTH_LONG);
                toast.show();
                MyAdapterIncidentList adapters = new MyAdapterIncidentList(this );
                lv.setAdapter(adapters);
                return true;
            case R.id.Repet:
                try {
                    future = executor.submit( (new GetIncidentRepet(workers.getIddivision())).task);
                    incidents = future.get();
                } catch (ExecutionException|InterruptedException e) { e.printStackTrace(); }
                toast = Toast.makeText(this, "Нарядов " + incidents.size() , Toast.LENGTH_LONG);
                toast.show();
                MyAdapterIncidentRepet adapterRepet = new MyAdapterIncidentRepet(this );
                lv.setAdapter(adapterRepet);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void getWorker() {
        Future<Workers> futurework = executor.submit( new GetWorker(  Imei).task);
        try {
            workers = futurework.get();
            change = false;
        } catch (ExecutionException|InterruptedException e) { e.printStackTrace(); }
    }
    @SuppressLint("HardwareIds")
    private String getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
            return Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        return telephonyManager.getDeviceId();
    }
}
