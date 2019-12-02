package ru.service;

import org.junit.Assert;

import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Response;
import ru.entity.Division;

import static ru.Api.Constants.api;

public class GetDivisions {
    public GetDivisions() {}
    public Callable task = () -> {
        Call<List<Division>> divisions =   api.getDivision();
        Response<List<Division>> response;
        response = divisions.execute();
        Assert.assertTrue(response.isSuccessful());
        return response.body();
    };
}
