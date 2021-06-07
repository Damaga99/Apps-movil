package es.imovildani.networkapp;

import android.content.Context;

import androidx.lifecycle.LiveData;

public class Repository {

    private WebService webService;
    private LiveData<Llegadas> busStops;


    public Repository(Context context){
        this.webService = new WebService(context);

        updateBusStopsData(busStops);


    }



    public void updateBusStopsData(LiveData<Llegadas> busStops){
        this.busStops= this.webService.getBusStops();


    }


    public LiveData<Llegadas> getBusStopsList(){
        return busStops;
    }

}
