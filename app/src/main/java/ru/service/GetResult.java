package ru.service;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import retrofit2.Call;
import retrofit2.Response;
import ru.entity.Result;

import static ru.Api.Constants.api;

public class GetResult {
    private int[] iddivision;
    public GetResult(int[] iddivision) {        this.iddivision = iddivision;    }

    public Callable task = () -> {
        Call<List<Result>> resultBurning = api.resultList(Arrays.toString(iddivision).replace("[", "").replace("]", ""));
        Response<List<Result>> response;
        response = resultBurning.execute();
        Assert.assertTrue(response.isSuccessful());
        return response.body();
    };
}
