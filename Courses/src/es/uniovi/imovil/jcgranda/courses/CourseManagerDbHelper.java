package es.uniovi.imovil.jcgranda.courses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CourseManagerDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CourseManager.db";

	private static final String SQL_CREATE_COURSES =
		"CREATE TABLE " + CourseManagerContract.Course.TABLE_NAME + " (" +
		CourseManagerContract.Course._ID + " INTEGER PRIMARY KEY," +
		CourseManagerContract.Course.COLUMN_NAME_NAME + " TEXT NOT NULL)";
	
	private static final String SQL_CREATE_CHAPTERS =
		"CREATE TABLE " + CourseManagerContract.Course.TABLE_NAME + " (" +
		CourseManagerContract.Chapter._ID + " INTEGER PRIMARY KEY," +
		CourseManagerContract.Chapter.COLUMN_NAME_NAME + " TEXT NOT NULL," +
		CourseManagerContract.Chapter.COLUMN_NAME_COURSE_ID + " INTEGER NOT NULL," +	
		"FOREIGN KEY(" + CourseManagerContract.Chapter.COLUMN_NAME_COURSE_ID  +
		") REFERENCES " + CourseManagerContract.Course.TABLE_NAME +
		"(" + CourseManagerContract.Course._ID + ") )";
	

	private static final String SQL_DELETE_COURSES =
		"DROP TABLE IF EXISTS " + CourseManagerContract.Course.TABLE_NAME;
	
	private static final String SQL_DELETE_CHAPTERS =
		"DROP TABLE IF EXISTS " + CourseManagerContract.Chapter.TABLE_NAME;
	
	
    public CourseManagerDbHelper(Context context) {
	
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	
    public void onCreate(SQLiteDatabase db) {
	
        db.execSQL(SQL_CREATE_COURSES);
        db.execSQL(SQL_CREATE_CHAPTERS);
    }
	
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
        db.execSQL(SQL_DELETE_COURSES);
        db.execSQL(SQL_DELETE_CHAPTERS);
        onCreate(db);
    }
	
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
        onUpgrade(db, oldVersion, newVersion);
    }
}
