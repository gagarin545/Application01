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
import ru.entity.Workers;

/**
 * API Interface
 */

public interface Api {
    @FormUrlEncoded
    @POST("Workers/imei")   Call <Workers> getworkerid(@Field("imei") String imei);
    @GET("Division")        Call<List<Division>> getDivision();
    @FormUrlEncoded
    @POST("Worker/add")  Call<Workers> addWorker(  @Field("name") String name, @Field("imei") String imei, @Field("iddivision") String iddivision);
    @FormUrlEncoded
    @POST("test")  Call<Workers> ad( @Field("name") String name, @Field("imei") String imei);
    @GET("Incident/{imei}")   Call<List<Incident>> getIncident(@Path("imei") String imei    );
    @GET("Division/ids={id}/imei={imei}")  Call<Workers> getIddivision(@Path("id") String id, @Path("imei") String imei);


}