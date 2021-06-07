package es.uniovi.imovil.asignaturasconarquitectura;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by arias on 16/03/2018.
 */

@Dao
public interface CourseDAO {
    @Insert
    public void insertCourse(Course course);

    @Query("DELETE from course_table")
    public void deleteAll();

    @Query("SELECT * FROM course_table WHERE name LIKE :name")
    public LiveData<Course> getCourseByName(String name);

    @Query("SELECT name FROM course_table")
    public LiveData<List<String>> getNames();
}
