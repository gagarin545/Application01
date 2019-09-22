package ru.service;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.entity.Workers;

import static ru.Api.Constants.Debug;
import static ru.Api.Constants.Imei;
import static ru.Api.Constants.api;

public class setDivision {
    private ArrayList<Integer> division;
    private String name;
    public setDivision( ArrayList<Integer> division, String name){
         this.division = division;
         this.name = name;
    }

    public void setdivision(){
        Log.e( Debug, Imei + Arrays.toString(new ArrayList[]{division}));
        api.addWorker( name, Imei, division.toString() ).enqueue(new Callback<Workers>() {

            @Override
            public void onResponse(@NonNull Call<Workers> call,@NonNull Response<Workers> response) {
                if(response.isSuccessful()) {
                    Log.e(Debug, "Добавить юзера");
                }   else  {   Log.e(Debug, "error >" + response.code()); }
            }

            @Override
            public void onFailure(@NonNull Call<Workers> call,@NonNull Throwable t) {
                Log.e(Debug, "not ok" + t.getMessage());
            }
        });
    }
}
