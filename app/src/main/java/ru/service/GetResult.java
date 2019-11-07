package ru.service;

import org.junit.Assert;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import ru.entity.Result;

import static ru.Api.Constants.api;

public class GetResult implements Runnable{
    private List<Result> resultList;
    private int[] iddivision;

    public GetResult(int[] iddivision) {        this.iddivision = iddivision;    }

    @Override
    public void run() {
        Call<List<Result>> resultBurning = api.resultList(Arrays.toString(iddivision).replace("[", "").replace("]", ""));
        Response<List<Result>> response;
        try {
            response = resultBurning.execute();
            Assert.assertTrue(response.isSuccessful());
            resultList = response.body();
            Assert.assertNotNull(resultList);
            System.out.println("Выбрано:" + resultList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Result> getResult() { return resultList;}
}
