package ru.UnitTest;

import android.annotation.SuppressLint;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import ru.Api.Api;
import ru.Api.ApiFactory;
import ru.entity.Incident;

public class IncidentListDayTest {
    private final Api api = ApiFactory.createApi();

    @SuppressLint("NewApi")
    @Test
    public void TestDiv()  {
        int[] i = {4, 228};
        String s = Arrays.toString(i);
        Date dd = new Date();


        Call<List<Incident>> cal = api.getIncidentListDay("30", Arrays.toString(i).replace("[", "").replace("]", ""));
        Response<List<Incident>> respons ;
        try {
            respons = cal.execute();
            // Проверим, что запрос завершился с кодом 2XX
            Assert.assertTrue(respons.isSuccessful());
            List<Incident> bod = respons.body();

            // Проверим, что тело запроса распарсилось нормально
            Assert.assertNotNull(bod);

         //   bod.forEach( inc -> System.out.println( inc.getN_incident() + "   ->" +  inc.getControlterm() ));

            //   body.forEach( inc -> System.out.println( inc.getN_incident()));
            System.out.println(bod.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
