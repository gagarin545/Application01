package ru.service;

import org.junit.Assert;
import java.util.concurrent.Callable;
import retrofit2.Call;
import retrofit2.Response;
import ru.entity.Workers;

import static ru.Api.Constants.api;

public class GetWorker {
    private String Imei;
    public GetWorker( String Imei) {        this.Imei = Imei;    }

    public Callable task = () -> {
        Call<Workers> works = api.getWorkerId(Imei);
        Response<Workers> response;
        response = works.execute();
        Assert.assertTrue(response.isSuccessful());
        return response.body();
    };
}
