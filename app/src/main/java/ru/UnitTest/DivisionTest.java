package ru.UnitTest;


import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import ru.Api.Api;
import ru.Api.ApiFactory;
import ru.entity.Division;

public class DivisionTest {
    private final Api api = ApiFactory.createApi();

    @Test
    public void TestDiv()  {
        Call<List<Division>> call = api.getDivision();
        Response<List<Division>> response ;

        try {
            response = call.execute();

            // Проверим, что запрос завершился с кодом 2XX
            Assert.assertTrue(response.isSuccessful());

            List<Division> body = response.body();

            // Проверим, что тело запроса распарсилось нормально
            Assert.assertNotNull(body);

         //   for( Division devision: body)
            //    System.out.println(devision.getNamedivision() + " " + devision.getIddivision());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
