package ru.service;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.entity.Incident;
import ru.entity.Workers;
import static ru.Api.Constants.Debug;
import static ru.Api.Constants.api;

public class getWorker  {
    private String Imei;
    private Context context;
    private List<Incident> incident;
    public getWorker( Context context, String Imei) {
        this.Imei = Imei;
        this.context = context;
    }
    public List<Incident> getwork() {
        api.getworkerid( Imei).enqueue(new Callback<Workers>() {
            @Override
            public void onResponse(@NonNull Call<Workers> call, @NonNull Response<Workers> response) {
                if (response.isSuccessful()) {
                    Workers worker = response.body();
                    incident = new getIncident(worker.getImei()).getdincident();
                    Log.e(Debug, worker.getName());
                } else {    Log.e(Debug, "error " + response.code());   }
            }

            @Override
            public void onFailure(@NonNull Call<Workers> call, @NonNull Throwable t) {
                Log.e( Debug, "worker - not found!!!!" + t.getMessage());
                new getDivision( context).getdivision();
            }
        });
        return incident;
    }
}
