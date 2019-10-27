package ru.UnitTest;

import android.annotation.SuppressLint;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import ru.Api.Api;
import ru.Api.ApiFactory;
import ru.entity.Incident;

public class test {
    private final Api api = ApiFactory.createApi();
    private Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
    @SuppressLint("NewApi")
    @Test
    public void TestDiv() {
        int[] i = {90, 133, 108, 107, 4, 109, 110};
        Call<List<Incident>> cal = api.getIncident(Arrays.toString(i).replace("[", "").replace("]", ""));
        Response<List<Incident>> respons ;
        try {
            respons = cal.execute();
            // Проверим, что запрос завершился с кодом 2XX
            Assert.assertTrue(respons.isSuccessful());
            List<Incident> bod = respons.body();

            // Проверим, что тело запроса распарсилось нормально
            Assert.assertNotNull(bod);

            bod.forEach( inc -> {
                int hours = (int)((inc.getControlterm() - time.getTime()) / 3600000);
                int min = (int) (((inc.getControlterm() - time.getTime()) - (long) (hours) * 3600000L) / 60000);
                String item = inc.getTypeincident() + inc.getN_incident() + " " + Math.abs(hours) + "ч." + Math.abs(min) + "мин." + " " + inc.getClazz() + " " + inc.getAddress() + " " + inc.getRoom();
                System.out.println( item + "! " + inc.getDivision().getNamedivision() );
            });

            //   body.forEach( inc -> System.out.println( inc.getN_incident()));
            System.out.println(bod.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
