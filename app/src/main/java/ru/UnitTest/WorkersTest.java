package ru.UnitTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import ru.entity.Workers;
import static ru.Api.Constants.api;

public class WorkersTest {
  //  private final Api api = ApiFactory.createApi();

    @Test
    public void TestWorks() {

        Call<Workers> call = api.getWorkerId("1234567890");
        Response<Workers> response ;
        try {
            response = call.execute();

            // Проверим, что запрос завершился с кодом 2XX
            Assert.assertTrue(response.isSuccessful());

            Workers body = response.body();

            // Проверим, что тело запроса распарсилось нормально
            Assert.assertNotNull(body);

                System.out.println(body.getName() + " " + body.getImei());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
