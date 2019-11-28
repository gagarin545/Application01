package ru.service;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import retrofit2.Call;
import retrofit2.Response;
import ru.entity.Incident;

import static ru.Api.Constants.api;

public class GetIncidentListDay{
    private int[] iddivision;
    private int day;
    public GetIncidentListDay(int[] iddivision, int day) {
        this.iddivision = iddivision; this.day = day;
    }
    public Callable task = () -> {
        Call<List<Incident>> incidentBurning = api.getIncidentListDay(String.valueOf(day), Arrays.toString(iddivision).replace("[", "").replace("]", ""));
        Response<List<Incident>> response;
        response = incidentBurning.execute();
        Assert.assertTrue(response.isSuccessful());
        return response.body();
    };

}
