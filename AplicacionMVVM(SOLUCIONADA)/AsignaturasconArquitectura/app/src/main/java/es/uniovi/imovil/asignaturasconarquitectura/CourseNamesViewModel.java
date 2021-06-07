package es.uniovi.imovil.asignaturasconarquitectura;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by arias on 16/03/2018.
 */

public class CourseNamesViewModel extends AndroidViewModel {
    private CourseRepository mRepository;
    private LiveData<List<String>> mNames;

    public CourseNamesViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CourseRepository(application);
        mNames = mRepository.getCourseNames();
    }

    public LiveData<List<String>> getCoursesNames() {
        return mNames;
    }

    public void insert(Course course) {
        mRepository.insertCourse(course);
    }
}
