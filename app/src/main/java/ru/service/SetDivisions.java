package ru.service;

import org.junit.Assert;
import java.util.HashSet;
import java.util.concurrent.Callable;
import retrofit2.Call;
import retrofit2.Response;
import ru.entity.Workers;
import static ru.Api.Constants.Imei;
import static ru.Api.Constants.api;

public class SetDivisions {
    private HashSet<Integer> division;
    private String name;
    public SetDivisions(HashSet<Integer> division, String name){
        this.division = division;
        this.name = name;
    }
    public Callable task = () -> {
        Call <Workers> worker = api.addWorker( name, Imei, division.toString());
        Response<Workers> response;
        response = worker.execute();
        Assert.assertTrue(response.isSuccessful());
        return response.body();
    };
}
