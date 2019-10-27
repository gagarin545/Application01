package ru.UnitTest;

import android.annotation.SuppressLint;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import ru.Api.Api;
import ru.Api.ApiFactory;
import ru.entity.Result;

public class ResultTest {
    private final Api api = ApiFactory.createApi();


    @SuppressLint("NewApi")
    @Test
    public void TestDiv()  {
        int[] i = {4, 110};
        String s = Arrays.toString(i);
        Call<List<Result>> call = api.resultList( Arrays.toString(i).replace("[", "").replace("]", ""));
        Response<List<Result>> respons ;
        try {
            respons = call.execute();
            // Проверим, что запрос завершился с кодом 2XX
            Assert.assertTrue(respons.isSuccessful());
            List<Result> bod = respons.body();

            // Проверим, что тело запроса распарсилось нормально
            Assert.assertNotNull(bod);

            bod.forEach( res -> {
                System.out.println( new Date(res.getDat()) + "   ->" + res.getKvcount() + "|" + res.getSkvcount() + "  " + res.getNamedivision() );

             System.out.println("|" + res.getIddivision()); });

            //   body.forEach( inc -> System.out.println( inc.getN_incident()));
            System.out.println(bod.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

