package ru.service;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Response;
import ru.entity.WorkerResult;

import static ru.Api.Constants.api;

public class GetWorkerResult {
    private int[] iddivision;
    public GetWorkerResult(int[] iddivision) {        this.iddivision = iddivision;    }

    public Callable task = () -> {
        Call<List<WorkerResult>> resultWorker = api.workerResultList(Arrays.toString(iddivision).replace("[", "").replace("]", ""));
        Response<List<WorkerResult>> response;
        response = resultWorker.execute();
        Assert.assertTrue(response.isSuccessful());
        return response.body();
    };
}
