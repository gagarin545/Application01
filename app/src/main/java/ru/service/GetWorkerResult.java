package ru.service;

import org.junit.Assert;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import ru.entity.WorkerResult;

import static ru.Api.Constants.api;

public class GetWorkerResult implements Runnable{
    private List<WorkerResult> resultList;
    private int[] iddivision;

    public GetWorkerResult(int[] iddivision) {        this.iddivision = iddivision;    }

    @Override
    public void run() {
        Call<List<WorkerResult>> resultWorker = api.workerResultList(Arrays.toString(iddivision).replace("[", "").replace("]", ""));
        Response<List<WorkerResult>> response;
        try {
            response = resultWorker.execute();
            Assert.assertTrue(response.isSuccessful());
            resultList = response.body();
            Assert.assertNotNull(resultList);
            System.out.println("Выбрано:" + resultList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<WorkerResult> getWorkerResult() { return resultList;}
}
