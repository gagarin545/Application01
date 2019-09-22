package ru.service;


import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.Api.Api;
import ru.Api.ApiFactory;
import ru.entity.Division;
import ru.entity.Workers;

import static ru.Api.Constants.Debug;

public class MyService extends Service {
    private final Api api = ApiFactory.createApi();
    private LocationManager locationManager;
    private  String Imei;
    ExecutorService es;

    @Override
    public void onCreate() {
        super.onCreate();
       // es = Executors.newFixedThreadPool(1);
   //     Imei = intent.getStringExtra("Imei");
        getWorker();
        Log.e(Debug, "MyService oncreate" );
    }

    private void getWorker(){
        api.getworkerid( Imei).enqueue(new Callback<Workers>() {
            @Override
            public void onResponse(@NonNull Call<Workers> call, @NonNull Response<Workers> response) {
                if (response.isSuccessful()) {
                Workers workers = response.body();
                    Log.e(Debug, workers.getName());
                } else {    Log.e(Debug, "error " + response.code());   }
            }

            @Override
            public void onFailure(@NonNull Call<Workers> call, @NonNull Throwable t) {
                Log.e( Debug, "worker - not found!!!!");
                api.getDivision().enqueue(new Callback<List<Division>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Division>> call,@NonNull Response<List<Division>> response) {
                        if(response.isSuccessful()) {
                            List<Division> divisions = response.body();
                            String[] div = new String[divisions.size()];
                            for( int i=0 ; i < divisions.size(); i++)
                                div[i] = divisions.get(i).getNamedevision();
                            Log.e(Debug, "div-->" + String.valueOf(div.length));
                        } else  {   Log.e(Debug, "error " + response.code()); }
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<Division>> call,@NonNull Throwable t) { Log.e(Debug,"No"); }
                });
            }
        });
    }

    /**
     * Слушатель для обновления гео
     */
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.v(Debug, "Location changed: " + location);
            getWorker();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.v(Debug, "Status changed: " + provider + ", status: " + status);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.v(Debug, "Provider enabled: " + provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.v(Debug, "Provider disabled: " + provider);
        }
    };
    @Override
    public void onDestroy() {
        //  locationManager.removeUpdates(locationListener);
        Log.e(Debug, "onDistroy");
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Debug, "MyService onStartCommand");
        Imei = intent.getStringExtra("Imei");
        return super.onStartCommand(intent, flags, startId);
    }


}
