package es.uniovi.imovil.jcgranda.courses;

import android.provider.BaseColumns;

public final class CourseManagerContract {

    public CourseManagerContract() {}
    
    public static abstract class Course implements BaseColumns {
	
        public static final String TABLE_NAME = "course";
        public static final String COLUMN_NAME_NAME = "name";
    }
	
	public static abstract class Chapter implements BaseColumns {
	
		public static final String TABLE_NAME = "chapter";
		public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_COURSE_ID = "teacherid";
	}
}
