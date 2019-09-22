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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.application01.R;
import ru.service.MyService;
import ru.util.CallCommand;
import ru.util.Commands;
import ru.util.Control;

import static ru.Api.Constants.Debug;
import static ru.Api.Constants.Imei;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Control control;

    @SuppressLint("StaticFieldLeak")
    public static EditText et1;
    @SuppressLint({"HardwareIds", "HandlerLeak"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        control = new Control( );

        et1 = (EditText) findViewById(R.id.ET1);
        TextView tv1 = (TextView) findViewById(R.id.TV1);
        Button bt1 = (Button) findViewById(R.id.BT1); // определение кнопки Send
        bt1.setOnClickListener(this);
        Button bt2 = (Button) findViewById(R.id.BT2); // определение кнопки Test
        bt2.setOnClickListener(this);
        Button bt7 = (Button) findViewById(R.id.BT7); // определение кнопки настройки
        bt7.setOnClickListener(this);
        Button bRest = (Button) findViewById(R.id.Rest); // определение кнопки Rest
        bRest.setOnClickListener(this);
        Imei = getImei();

    }

    /**
     * Запускаем сервис
     */
    private void startService() {
        Log.e( Debug, "main->startService");
       startService(new Intent(this, MyService.class).putExtra("Imei", getImei()));
     //   startService(new Intent(this, MyService.class).putExtra("Imei", "1234567890"));
    }

    @Override
    protected void onStart() {
        super.onStart();
      //  IntentFilter intentFilter = new IntentFilter(ACTION_GOT);
      //  registerReceiver(workReceiver, intentFilter);
        Log.e( Debug, "main->onStart");

  //      IntentFilter intentFilter = new IntentFilter( API_BASE_URL);
 //       registerReceiver(receiver, intentFilter);

    }
    @Override
    protected void onStop() {
    //    unregisterReceiver(receiver);

        super.onStop();
       // stopService( intent);
    }
    @Override
    public void onClick(View v) {
        Log.e( Debug, "onclik");
        control.SetCommand(new CallCommand(new Commands(this, Imei)));
        control.press(v.getId());
    }
    @SuppressLint("HardwareIds")
    private String getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
            return Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        return telephonyManager.getDeviceId();
    }

}
