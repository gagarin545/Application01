package ru.service;

import android.content.Context;
import android.util.Log;

import org.junit.Assert;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;
import ru.entity.Workers;

import static ru.Api.Constants.Debug;
import static ru.Api.Constants.api;

public class GetWorkers  implements Runnable {
    private String Imei;
    private Context context;
    private Workers workers;
    public GetWorkers(Context context, String Imei) {
        this.context = context;
        this.Imei = Imei;
    }

    @Override
    public void run() {
        Call<Workers> works = api.getWorkerId(Imei);
        Response<Workers> response;
        try {
            response = works.execute();
            Assert.assertTrue(response.isSuccessful());
            workers = response.body();
            Assert.assertNotNull(workers);
            Log.e( Debug, workers.getName() + workers.getImei() );
        } catch (IOException e) {
            Log.e( Debug, "worker - not found!!!!" );
            new GetDivision( context).getdivision();
           // e.printStackTrace();
        }

    }
    public Workers getwork() { return workers;}
}
