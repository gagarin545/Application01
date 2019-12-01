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
import java.util.List;

import ru.adapter.ChartDataAdapter;
import ru.adapter.MyAdapterIncident;
import ru.adapter.MyAdapterIncidentList;
import ru.adapter.MyAdapterIncidentRepet;
import ru.entity.Incident;
import ru.entity.ViewTest;
import ru.entity.Workers;
import ru.service.GetDivision;

import static ru.Api.Constants.Imei;
import static ru.Api.Constants.change;
import static ru.util.GetExecut.getIncident;
import static ru.util.GetExecut.getIncidentNday;
import static ru.util.GetExecut.getIncidentRepet;
import static ru.util.GetExecut.getOnOrder;
import static ru.util.GetExecut.getWeekOnWorker;
import static ru.util.GetExecut.getWorker;

public class MainActivity extends AppCompatActivity {
    public static List <Incident> incidents;
    static Workers workers;
    ChartDataAdapter chartDataAdapter;
    MyAdapterIncident adapter;
    ListView lv;
    Toast toast;

    @SuppressLint({"HardwareIds", "HandlerLeak"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listView1);
        Imei = getImei();
        workers = getWorker();
        Log.e("DEbug", "--" + workers.getName());
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
   }
   @Override
   protected void onResume() {
       super.onResume();
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
    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ArrayList<BarData> list = new ArrayList<>();
        if( change) getWorker();
        switch (item.getItemId()) {
            case R.id.today:
                incidents = getIncident(workers.getIddivision());
                toast = Toast.makeText(this, "Нарядов " + incidents.size() , Toast.LENGTH_LONG);
                toast.show();
                adapter = new MyAdapterIncident(this);
                lv.setAdapter(adapter);
                return true;
            case R.id.weekOnOrder:
                chartDataAdapter = new ChartDataAdapter(getApplicationContext(), getOnOrder(workers.getIddivision()));
                lv.setAdapter(chartDataAdapter);
                return true;
            case R.id.weekOnWorkers:
                chartDataAdapter = new ChartDataAdapter(getApplicationContext(), getWeekOnWorker(workers.getIddivision()));
                lv.setAdapter(chartDataAdapter);
                return true;
            case R.id.Setting:
                new GetDivision( this).getdivision();
                return true;
            case R.id.Nday:
                incidents =  getIncidentNday(workers.getIddivision());
                toast = Toast.makeText(this, "Нарядов " + incidents.size() , Toast.LENGTH_LONG);
                toast.show();
                MyAdapterIncidentList adapters = new MyAdapterIncidentList(this );
                lv.setAdapter(adapters);
                return true;
            case R.id.Repet:
                incidents =  getIncidentRepet(workers.getIddivision());
                toast = Toast.makeText(this, "Нарядов " + incidents.size() , Toast.LENGTH_LONG);
                toast.show();
                MyAdapterIncidentRepet adapterRepet = new MyAdapterIncidentRepet(this );
                lv.setAdapter(adapterRepet);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    ViewTest getTest( String incident) {
        ViewTest viewTest = null;
  //      Future<ViewTest> future_test = executor.submit( new GetTest( incident).task);
   //     try {
    //        viewTest = future_test.get();
     //       Log.e("Debug",  "test");
      //      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
       //         Arrays.stream(new ViewTest[]{viewTest}).forEach(x-> System.out.println(x.toString()));
        //    }
     //   } catch (ExecutionException | InterruptedException e) { e.printStackTrace();                }
      //  Log.e("Debug", String.valueOf(executor.getActiveCount()));
        return  viewTest;
    }

    @SuppressLint("HardwareIds")
    private String getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
            return Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        return telephonyManager.getDeviceId();
    }
}
