package ru.service;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.entity.Incident;
import static ru.Api.Constants.Debug;
import static ru.Api.Constants.api;

class getIncident {
    private String Imei;
    private List<Incident> incident;
    getIncident( String Imei) {
        this.Imei = Imei;
    }
    List<Incident> getdincident() {
        api.getIncident( Imei).enqueue(new Callback<List<Incident>>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(@NonNull Call<List<Incident>> call, @NonNull Response<List<Incident>> response) {
                if (response.isSuccessful()) {
                    incident= response.body();
                    Log.e(Debug, "incident=" + incident.size());
                } else {    Log.e(Debug, "error " + response.code());  }
            }

            @Override
            public void onFailure(@NonNull Call<List<Incident>> call,@NonNull Throwable t) {
                Log.e(Debug, "No");
            }
        });
        return incident;
    }
}
