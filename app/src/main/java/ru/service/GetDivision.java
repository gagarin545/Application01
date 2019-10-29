package ru.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.activity.DivisionsActivity;
import ru.entity.Division;

import static ru.Api.Constants.Debug;
import static ru.Api.Constants.api;
public class GetDivision {
    private Context context;
    public GetDivision(Context context) {
        this.context = context;
    }
    public void getdivision() {
        api.getDivision().enqueue(new Callback<List<Division>>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(@NonNull Call<List<Division>> call, @NonNull Response<List<Division>> response) {
                if (response.isSuccessful()) {
                    List<Division> divisions = response.body();
                    String[] div = new String[divisions.size()];
                    int[] division_poz =  new int[divisions.size()];
                    for (int i = 0; i < divisions.size(); i++) {
                        div[i] = divisions.get(i).getNamedivision();
                        division_poz[i] = divisions.get(i).getIddivision();
                    }
                    Log.e(Debug, "div=" + div.length + div[div.length - 1]);
                    Intent intent = new Intent(context, DivisionsActivity.class);
                    intent.putExtra("word", div);
                    intent.putExtra("poz", division_poz);
                    context.startActivity(intent);
                } else {
                    Log.e(Debug, "error " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Division>> call, @NonNull Throwable t) {
                Log.e(Debug, "No");
            }
        });
    }
}