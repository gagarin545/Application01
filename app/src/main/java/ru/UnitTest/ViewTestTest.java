package ru.UnitTest;

import android.annotation.SuppressLint;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

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
        //String incident = "9633720";
        //String incident = "9666819";
        String incident = "9678887";

        Call<ViewTest> result = api.gettest( incident);
        try {
            Response<ViewTest> response = result.execute();
            Assert.assertTrue(response.isSuccessful());
            ViewTest viewTest = response.body();
            Arrays.stream(new ViewTest[]{viewTest}).forEach(x-> System.out.println(x.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
