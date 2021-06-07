package es.uniovi.imovil.asignaturasconarquitectura;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by arias on 16/03/2018.
 */

@Entity(tableName = "course_table")
public class Course {
    @PrimaryKey
    @NonNull
    private String name;

    private String teacher;
    private String description;

    public Course(String name, String teacher, String description) {
        this.name = name;
        this.teacher = teacher;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
