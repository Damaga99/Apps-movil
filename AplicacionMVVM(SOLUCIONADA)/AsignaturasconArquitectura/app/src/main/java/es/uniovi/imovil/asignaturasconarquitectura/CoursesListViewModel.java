package es.uniovi.imovil.asignaturasconarquitectura;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by portatil on 18/03/2018.
 */

public class CoursesListViewModel extends AndroidViewModel {
    private CourseRepository courseRepository;
    private LiveData<Course> mcourse;
    private MutableLiveData<String> mName;


    public void setName(String mName) {
        this.mName.setValue(mName);
    }

    public LiveData<Course> getCourse() {
        return this.mcourse;
    }

    public CoursesListViewModel(@NonNull Application application) {
        super(application);
        courseRepository = new CourseRepository(application);
        mName = new MutableLiveData<>();
        mcourse = Transformations.switchMap(mName, new Function<String, LiveData<Course>>() {
            @Override
            public LiveData<Course> apply(String input) {
                return courseRepository.getCourseByName(input);
            }
        });




    }
}
