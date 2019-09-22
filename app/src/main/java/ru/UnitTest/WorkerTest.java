package ru.UnitTest;

import android.support.annotation.NonNull;
import junit.framework.Assert;
import org.junit.Test;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import ru.Api.Api;
import ru.Api.ApiFactory;
import ru.entity.Division;
import ru.entity.Incident;
import ru.entity.Workers;


public class WorkerTest {
    private final Api api = ApiFactory.createApi();

    @Test
    public void Testwork()  {
        @NonNull Call<Workers> work = api.getworkerid("imei=1234567890");
        try {
            @NonNull Response<Workers> worker = work.execute();
            Assert.assertTrue(worker.isSuccessful());
            Workers body = worker.body();
            Assert.assertNotNull(body);
            if( body != null)
                if( body.getIddivision() != null) {
                    Call<List<Incident>> incident = api.getworkerincident(body.getImei());
                    Response<List<Incident>> inc = incident.execute();
                    List<Incident> incidents = inc.body();
                    System.out.println( body.getName() + ' ' + body.getImei() + ' ' +incidents.size() );
                }

        } catch (IOException e1) {
            Call<List<Division>> division = api.getDivision();
            Response<List<Division>> div = null;
            try {
                div = division.execute();
            } catch (IOException e) {                e.printStackTrace();            }
            List<Division> divisions = div.body();
            System.out.println( "division-> " + divisions.size());
        }
    }
}
