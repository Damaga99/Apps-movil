package es.imovildani.networkapp;


import retrofit2.Call;
import retrofit2.http.GET;


public interface RestService {

    @GET("doc/transporte/busgijontr.json")
    Call<BusStop> getInfo();

}