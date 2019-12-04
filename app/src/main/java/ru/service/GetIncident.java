package ru.service;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import retrofit2.Call;
import retrofit2.Response;
import ru.entity.Incident;
import static ru.Api.Constants.api;

public class GetIncident {
    private int[] iddivision;
    public GetIncident( int[] iddivision) {
        this.iddivision = iddivision;
    }
        public Callable task = () -> {
            Call<List<Incident>> incidentBurning = api.getIncident(Arrays.toString(iddivision).replace("[", "").replace("]", ""));
            Response<List<Incident>> response;
            response = incidentBurning.execute();
            Assert.assertTrue(response.isSuccessful());
            return response.body();
        };
}
