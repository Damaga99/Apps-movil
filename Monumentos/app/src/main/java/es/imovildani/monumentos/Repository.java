package es.imovildani.monumentos;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class Repository {


    private es.imovildani.monumentos.WebService webService;
    private LiveData<List<Monumento>> monumento;




    MutableLiveData<List<String>> mNames = new MutableLiveData<>();

    public Repository(Context context){
        this.webService = new es.imovildani.monumentos.WebService(context);


        updateMonumentoData(monumento);


    }



    public void updateMonumentoData(LiveData<List<Monumento>> monumento){
        this.monumento= this.webService.getMonumento();


    }





    public LiveData<List<Monumento>> getMonumentosList(){
        return monumento;
    }

    public LiveData<Monumento> getCourseByName(String name) {
        //...
        return this.webService.getMonumentoByName(name);
    }


    public LiveData<List<String>> getCourseNames() {
        return this.mNames;
    }
}
