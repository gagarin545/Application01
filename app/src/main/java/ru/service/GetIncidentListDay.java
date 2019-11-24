package ru.service;

import org.junit.Assert;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import ru.entity.Incident;

import static ru.Api.Constants.api;


public class GetIncidentListDay implements Runnable {
    public static List<Incident> incidents;
    private int[] iddivision;
    private int day;
    public GetIncidentListDay(int[] iddivision, int day) {
        this.iddivision = iddivision; this.day = day;
    }

    @Override
    public void run() {
        Call<List<Incident>> incidentBurning = api.getIncidentListDay( String.valueOf( day), Arrays.toString(iddivision).replace("[", "").replace("]", ""));
        Response<List<Incident>> response;
        try {
            response = incidentBurning.execute();
            Assert.assertTrue(response.isSuccessful());
            incidents = response.body();
            Assert.assertNotNull(incidents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Incident> getIncident() { return incidents;}
}
