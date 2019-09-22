package ru.UnitTest;

import android.support.annotation.NonNull;
import android.util.Log;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.Api.Api;
import ru.Api.ApiFactory;
import ru.entity.Workers;

import static ru.Api.Constants.Debug;

public class WorkersTest {
    private final Api api = ApiFactory.createApi();

    @Test
    public void TestWorks() {

        Call<Workers> call = api.getworkerid("imei=1234567890");
        call.enqueue(new Callback<Workers>() {
            @Override
            public void onResponse(@NonNull Call<Workers> call,@NonNull Response<Workers> response) {
                if (response.isSuccessful()) {
                    Workers body = response.body();
                    System.out.println(body.getName() + body.getImei());
                    Log.e(Debug, body.getName() + body.getImei());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Workers> call,@NonNull Throwable t) {
                System.out.println("workers - Не найден!!!!");
                Log.e( Debug, "workers - Не найден!!!!");
            }
        });
    }
}
