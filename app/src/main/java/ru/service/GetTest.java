package ru.service;

import android.util.Log;

import org.junit.Assert;

import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Response;
import ru.entity.ViewTest;

import static ru.Api.Constants.api;

public class GetTest {
    private String incident;
    public GetTest(String incident) {        this.incident = incident;    }

    public Callable task = () -> {
        Log.e("DEbug", incident);
        Call<ViewTest> result = api.gettest( incident);
        Response<ViewTest> response;
        response = result.execute();
        Assert.assertTrue(response.isSuccessful());
        ViewTest viewTest = response.body();
        Log.e("DEbug", String.valueOf(viewTest.getService()));
        return response.body();
    };
}
