package ru.UnitTest;

import android.annotation.SuppressLint;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import ru.Api.Api;
import ru.Api.ApiFactory;
import ru.entity.ViewTest;

public class ViewTestTest {
    private final Api api = ApiFactory.createApi();


    @SuppressLint("NewApi")
    @Test
    public void TestDiv()  {
        long inc = 9633720L;

        Call<ViewTest> cal = api.gettest(String.valueOf(inc));
        Response<ViewTest> respons ;
        try {
            respons = cal.execute();
            // Проверим, что запрос завершился с кодом 2XX
            Assert.assertTrue(respons.isSuccessful());
            ViewTest bod = respons.body();

            // Проверим, что тело запроса распарсилось нормально
            Assert.assertNotNull(bod);

            //   bod.forEach( inc -> System.out.println( inc.getN_incident() + "   ->" +  inc.getControlterm() ));

            //   body.forEach( inc -> System.out.println( inc.getN_incident()));
            System.out.println(bod.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
