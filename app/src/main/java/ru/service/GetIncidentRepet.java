package ru.service;

import org.junit.Assert;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import ru.entity.Incident;

import static ru.Api.Constants.api;


public class GetIncidentRepet implements Runnable {
    public static List<Incident> incidents;
    private int[] iddivision;

    public GetIncidentRepet(int[] iddivision) {
        this.iddivision = iddivision;
    }

    @Override
    public void run() {
        Call<List<Incident>> incidentRepet = api.getIncidentRepet( Arrays.toString(iddivision).replace("[", "").replace("]", ""));
        Response<List<Incident>> response;
        try {
            response = incidentRepet.execute();
            Assert.assertTrue(response.isSuccessful());
            incidents = response.body();
            Assert.assertNotNull(incidents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Incident> getIncidentRepet() { return incidents;}
}
