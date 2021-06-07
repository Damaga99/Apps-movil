package es.imovildani.monumentos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MonumentoViewModel extends AndroidViewModel {


    private Repository repository;
    private LiveData<List<Monumento>> mMonumento;


    private LiveData<List<String>> mNames;

    public MonumentoViewModel(@NonNull Application application) {
        super(application);
        this.repository=new Repository(application.getApplicationContext());



        this.mMonumento = this.repository.getMonumentosList();
        this.mNames = this.repository.getCourseNames();



    }


    public LiveData<List<Monumento>> getMonumentoList() {
        return mMonumento;
    }


    public void updateMonumento(){

        repository.updateMonumentoData(mMonumento);
    }




    public LiveData<List<String>> getCourseNames() {

        return this.mNames;
    }


}
