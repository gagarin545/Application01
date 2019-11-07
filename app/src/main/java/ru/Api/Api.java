package ru.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.entity.Division;
import ru.entity.Incident;
import ru.entity.Result;
import ru.entity.WorkerResult;
import ru.entity.Workers;

/**
 * API Interface
 */

public interface Api {
    @GET("/Division")        Call<List<Division>> getDivision();
    @FormUrlEncoded
    @POST("/Workers/imei")   Call<Workers> getWorkerId(@Field("imei") String imei);
    @FormUrlEncoded
    @POST("/Workers/add")  Call<Workers> addWorker(  @Field("name") String name, @Field("imei") String imei, @Field("iddivision") String iddivision);
    @GET("Incident/{iddivision}")   Call<List<Incident>> getIncident(@Path("iddivision") String iddivision  );
    @GET("Incident/burn/{iddivision}")   Call<List<Incident>> incidentListBurn(@Path("iddivision") String iddivision  );
    @GET("Result/{iddivision}")   Call<List<Result>> resultList(@Path("iddivision") String iddivision  );
    @GET("WorkerResult/{iddivision}")   Call<List<WorkerResult>> workerResultList(@Path("iddivision") String iddivision  );
}