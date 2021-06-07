package es.uniovi.imovil.asignaturasconarquitectura;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by arias on 16/03/2018.
 */

@Database(entities = {Course.class}, version = 1)
public abstract class CourseDatabase extends RoomDatabase {
    public abstract CourseDAO courseDAO();

    private static CourseDatabase INSTANCE;

    public static CourseDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CourseDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CourseDatabase.class, "course_database")
                            .addCallback(sRoomDatabaseCallbackCreate)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallbackCreate = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDBAsync(INSTANCE).execute();
        }
    };

    private static RoomDatabase.Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new populateDBAsync(INSTANCE).execute();
        }
    };


    private static class populateDBAsync extends AsyncTask<Void, Void, Void> {

        private final CourseDAO mDAO;

         populateDBAsync(CourseDatabase db) {
             this.mDAO = db.courseDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
             mDAO.deleteAll();
             Course course = new Course("Fundamentos de Computadores", "Joaquín Entrialgo","Asignatura de primero");
             mDAO.insertCourse(course);
             course = new Course("Informática Móvil", "José Ramón Arias", "Asignatura de cuarto curso");
             mDAO.insertCourse(course);
            return null;
        }
    }

}
