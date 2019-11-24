package ru.UnitTest;

import android.annotation.SuppressLint;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
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
        int[] i = {90, 4, 110 };
        String nameDivision = null;
        ArrayList<String> day = new ArrayList<>();
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



          //  for( int ii: i) {
             //   for (Result result : bod) {
              //      if (result.getIddivision() == ii) {
             //           nameDivision = result.getNamedivision();
          //              day.add(String.valueOf(new Date(result.getTimeclose())));
                        //Log.e(Debug, result.getIddivision() + " | " + new Date(result.getTimeclose()) + " | " + result.getKvcount() + " | " + result.getSkvcount());
         //           }

         //       }
           //     System.out.println(nameDivision);
          //      day.forEach( d-> System.out.println( d));
         //       day = new ArrayList<>();
        //    }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

