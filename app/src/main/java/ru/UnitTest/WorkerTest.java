package ru.UnitTest;

import android.support.annotation.NonNull;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import ru.Api.Api;
import ru.Api.ApiFactory;
import ru.entity.Division;
import ru.entity.Workers;


public class WorkerTest {
    private final Api api = ApiFactory.createApi();

    @Test
    public void Testwork()  {
        @NonNull Call<Workers> work = api.getWorkerId("imei=1234567890");
        try {
            @NonNull Response<Workers> worker = work.execute();
            Assert.assertTrue(worker.isSuccessful());
            Workers body = worker.body();
            Assert.assertNotNull(body);

        } catch (IOException e1) {
            Call<List<Division>> division = api.getDivision();
            Response<List<Division>> div = null;
            try {
                div = division.execute();
            } catch (IOException e) {                e.printStackTrace();            }
            List<Division> divisions = div.body();
            System.out.println( "division-> " + divisions.size());
        }
    }
}
