package es.uniovi.imovil.jcgranda.courses;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements CourseListFragment.Callbacks {
	
	private static final String COURSE_COUNT = "course_count";
	private static final String PREFERENCES = "preferences";
	private static final String DEFAULT_EXPORT_FILE = "courses.dat";	
	private static final String TAG = "MainActivity";
	
	private int mCourseCount = 0;
	private boolean mTwoPanes = false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		if (findViewById(R.id.course_details_container) != null) {
			mTwoPanes = true;
		}
		
		if (savedInstanceState != null) {
			
			mCourseCount = savedInstanceState.getInt(COURSE_COUNT);
		} else {
			
			SharedPreferences prefs = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
			mCourseCount = prefs.getInt(COURSE_COUNT, 0);
		}
	}	

	
	@Override
	public void onPause() {
		
		super.onPause();
		SharedPreferences prefs = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
		SharedPreferences.Editor prefsEditor = prefs.edit();
		prefsEditor.putInt(COURSE_COUNT, mCourseCount);
		prefsEditor.commit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflar el menú y añadir acciones al action bar si existe
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
			    
	    switch (item.getItemId()) {
	        case R.id.action_add_course:
	        	addCourse();
	            return true;
	        case R.id.action_settings:
	        	showPreferences();
	        	return true;
	        case R.id.action_export:
	        	exportCourseList();
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	public void onCourseSelected(Course course) {
		
		if (mTwoPanes) {
			FragmentManager fragmentManager = getSupportFragmentManager();	        	
        	CourseDetailsFragment fragment = (CourseDetailsFragment) fragmentManager.findFragmentById(R.id.course_details_frag);        	
            fragment.setCourse(course);
		} else {
			Intent intent = new Intent(this, CourseDetailsActivity.class);
		    intent.putExtra(CourseDetailsActivity.COURSE, course);
		    startActivity(intent);			
		}		
	}	

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putInt(COURSE_COUNT, mCourseCount);
	}
	
	
	private void addCourse() {
		
		FragmentManager fragmentManager = getSupportFragmentManager();	        	
    	CourseListFragment fragment = (CourseListFragment) fragmentManager.findFragmentById(R.id.course_list_frag);
    	String name = String.format(getString(R.string.default_course_format), ++mCourseCount);
		String teacher = String.format(getString(R.string.default_teacher_format), mCourseCount);
		Course course = new Course(name, teacher, null);
        fragment.addCourse(course);
	}
	
	private void showPreferences() {
		
		Intent intent = new Intent(this, PrefsActivity.class);
	    startActivity(intent);
	}
	

	private void exportCourseList() {
		
		FragmentManager fragmentManager = getSupportFragmentManager();	        	
    	CourseListFragment fragment = (CourseListFragment) fragmentManager.findFragmentById(R.id.course_list_frag);
    	
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    	String filename = prefs.getString(getString(R.string.export_preference), DEFAULT_EXPORT_FILE);
    	
    	FileOutputStream outputStream = null;
    	String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            
        	try {
        		File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        		File file = new File(dir, filename);
        		outputStream = new FileOutputStream(file);
        		fragment.saveList(outputStream);
        		Toast.makeText(this, R.string.export_msg_ok, Toast.LENGTH_SHORT).show();
        	}
        	catch (Exception ex) {
        		Toast.makeText(this, R.string.export_msg_error, Toast.LENGTH_SHORT).show();
        	} finally {
        		try {
        			if (outputStream != null) {
        				outputStream.close();
        			}
        		} catch (Exception ex) {
        			Log.d(TAG, ex.getMessage());
        		}
        	}        	
        }
	}
}
