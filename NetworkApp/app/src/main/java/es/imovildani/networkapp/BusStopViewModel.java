package es.imovildani.networkapp;

    import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class BusStopViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<Llegadas> mBusStops;

    public BusStopViewModel(@NonNull Application application) {
        super(application);
        this.repository=new Repository(application.getApplicationContext());


        //MIRAR
        this.mBusStops = this.repository.getBusStopsList();

    }


    public LiveData<Llegadas> getBusStopsList() {
        return mBusStops;
    }


    public void updateBusStops(){
        repository.updateBusStopsData(mBusStops);
    }

}
