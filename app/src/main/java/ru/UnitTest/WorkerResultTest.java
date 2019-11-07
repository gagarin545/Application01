package ru.UnitTest;

import android.annotation.SuppressLint;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import ru.Api.Api;
import ru.Api.ApiFactory;
import ru.entity.WorkerResult;

public class WorkerResultTest {
    private final Api api = ApiFactory.createApi();


    @SuppressLint("NewApi")
    @Test
    public void TestDiv()  {
        int[] i = {90, 4, 110 };
        String nameDivision = null;
        ArrayList<String> day = new ArrayList<>();
        String s = Arrays.toString(i);
        Call<List<WorkerResult>> call = api.workerResultList( Arrays.toString(i).replace("[", "").replace("]", ""));
        Response<List<WorkerResult>> respons ;
        try {
            respons = call.execute();
            Assert.assertTrue(respons.isSuccessful());
            List<WorkerResult> bod = respons.body();
            Assert.assertNotNull(bod);

            long l = bod.get(0).getTimeclose();

            System.out.println( l);
            for (WorkerResult result : bod) {

                System.out.println(result.getName() + "|" + new Date(result.getTimeclose()) + "|" + result.getCount());
                        //Log.e(Debug, result.getIddivision() + " | " + new Date(result.getTimeclose()) + " | " + result.getKvcount() + " | " + result.getSkvcount());

                }
                System.out.println(nameDivision);
                day.forEach( d-> System.out.println( d));
                day = new ArrayList<>();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

