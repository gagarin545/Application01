package ru.UnitTest;

import android.annotation.SuppressLint;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;
import ru.Api.Api;
import ru.Api.ApiFactory;
import ru.entity.Incident;

public class IncidentTest {
    private final Api api = ApiFactory.createApi();


    @SuppressLint("NewApi")
    @Test
    public void TestDiv()  {
        int[] i = {4};
        String s = Arrays.toString(i);
        Date dd = new Date();
        //long inc = 9633720L;
        long inc = 9681746L;


        Call<List<Incident>> cal = api.getIncident(Arrays.toString(i).replace("[", "").replace("]", ""));
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
            DateFormat df = new SimpleDateFormat("HH:mm dd.mm.y Z", Locale.getDefault());
            bod.forEach(x-> System.out.println(x.getN_incident() + "  " + df.format(x.getDecisiontime())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
