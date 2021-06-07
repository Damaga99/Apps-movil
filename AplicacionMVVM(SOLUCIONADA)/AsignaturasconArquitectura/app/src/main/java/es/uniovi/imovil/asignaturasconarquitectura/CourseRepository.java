package es.uniovi.imovil.asignaturasconarquitectura;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by arias on 16/03/2018.
 */

public class CourseRepository {
    private CourseDAO courseDAO;
    private LiveData<Course> mCourses;
    private LiveData<List<String>> mNames;

    public CourseRepository(Application application) {
        CourseDatabase courseDatabase = CourseDatabase.getDatabase(application);
        this.courseDAO = courseDatabase.courseDAO();
        this.mNames = this.courseDAO.getNames();
        //this.mCourses = this.courseDAO.getCoursesByName();
    }

    public LiveData<Course> getCourseByName(String name) {
        this.mCourses = this.courseDAO.getCourseByName(name);
        return this.mCourses;
    }

    public LiveData<List<String>> getCourseNames() {
        return mNames;
    }
    /*public LiveData<List<Course>> getAllCourses() {
        return this.mCourses;
    }

*/
    public void insertCourse(Course course) {
        new insertAsyncTask(courseDAO).execute(course);
    }

    private static class insertAsyncTask extends AsyncTask<Course, Void, Void> {

        private CourseDAO myAsyncTaskDAO;

        public insertAsyncTask(CourseDAO myAsyncTaskDAO) {
            this.myAsyncTaskDAO = myAsyncTaskDAO;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            myAsyncTaskDAO.insertCourse(courses[0]);
            return null;
        }
    }
}
