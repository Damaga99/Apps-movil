package es.imovildani.monumentos;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class DetailsViewModel extends AndroidViewModel {
    private LiveData<Monumento> mMonumento;
    Repository repository;

    private MutableLiveData<String> mName = new MutableLiveData<>();

    public DetailsViewModel(@NonNull Application application) {
        super(application);

        this.repository = new Repository(application);


        mMonumento = Transformations.switchMap(mName, new Function<String, LiveData<Monumento>>() {
            @Override
            public LiveData<Monumento> apply(String input) {
                return repository.getCourseByName(input);
            }
        });

    }



    public void setName(String mName) {
        this.mName.setValue(mName);
    }
    public String getName() {
        return this.mName.getValue();
    }

    public LiveData<Monumento> getMonumento() {
        return this.mMonumento;
    }
}

