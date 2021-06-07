package es.uniovi.imovil.jcgranda.courses;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class CourseListFragment extends Fragment implements AdapterView.OnItemClickListener {

	private static final String COURSE_LIST = "course_list";
	private static final String COURSE_NAME_TAG = "name";
	private static final String COURSE_TEACHER_TAG = "teacher";
	private static final String COURSE_DESCRIPTION_TAG = "description";	
	private static final String COURSE_LIST_FILENAME = "course_list.dat";
	private static final String TAG = "CourseListFragment";
	
	public interface Callbacks {
		public void onCourseSelected(Course course);
	}
	
	private CourseAdapter mAdapter = null;
	private Callbacks mCallback = null;
	private ArrayList<Course> mCourseList = null;
	
	public static CourseListFragment newInstance() {
		
		CourseListFragment fragment = new CourseListFragment();
		return fragment;
	}
	
	public CourseListFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		
        super.onAttach(activity);
        try {
        	mCallback = (Callbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement Callbacks");
        }
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView;
		rootView = inflater.inflate(R.layout.course_list_fragment, container, false);			
		
		if (savedInstanceState != null) {
			
			mCourseList = savedInstanceState.getParcelableArrayList(COURSE_LIST);
		} else {
			// Lista guardada en fichero?
			if (!restoreList())
			{
				// Lista por defecto
				String [] courses = getResources().getStringArray(R.array.courses);
				String [] teachers = getResources().getStringArray(R.array.teachers);
				String [] descriptions = getResources().getStringArray(R.array.descriptions);
				mCourseList = createCourseList(courses, teachers, descriptions);
			}
		}
		ListView lvItems = (ListView) rootView.findViewById(R.id.list_view_courses);
		mAdapter = new CourseAdapter(getActivity(), mCourseList);
		lvItems.setAdapter(mAdapter);
		lvItems.setOnItemClickListener(this);

		return rootView;
	}

	private ArrayList<Course> createCourseList(String[] names, String[] teachers, String[] descriptions) {
		
		if (names.length != teachers.length || names.length != descriptions.length) {
			throw new IllegalStateException();
		}
			
		ArrayList<Course> courses = new ArrayList<Course>(names.length);
		for (int i = 0; i < names.length; i++) {
			courses.add(new Course(names[i], teachers[i], descriptions[i]));
		}
		return courses;
	}
	
	public void addCourse(Course course) {

		if (course == null) {
			throw new IllegalArgumentException();			
		}
		
		mCourseList.add(course);
		mAdapter.notifyDataSetChanged();		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		Course course = (Course) parent.getItemAtPosition(position);
		mCallback.onCourseSelected(course);
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putParcelableArrayList(COURSE_LIST, mCourseList);
	}
	
	@Override
	public void onPause() {
		
		super.onPause();
		FileOutputStream outputStream = null;
		try {
			outputStream = getActivity().openFileOutput(COURSE_LIST_FILENAME, Context.MODE_PRIVATE);
			saveList(outputStream);
		} catch (Exception ex) {
			Log.d(TAG, ex.getMessage());
		}
		finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception ex) {
					Log.d(TAG, ex.getMessage());
				}
			}
		}
	}
	
	private static JSONArray courseListToJson(ArrayList<Course> courses) {

		JSONArray array = new JSONArray();
		for (Course course: courses) {
			
			try {
				JSONObject jsonCourse = new JSONObject();
				jsonCourse.put(COURSE_NAME_TAG, course.getName());
				jsonCourse.put(COURSE_TEACHER_TAG, course.getTeacher());
				jsonCourse.put(COURSE_DESCRIPTION_TAG, course.getDescription());
				array.put(jsonCourse);
			} catch (JSONException ex) {
				// Nunca debería ocurrir
				Log.d(TAG, ex.getMessage());
			}			
		}
		
		return array;
	}
	
	private static ArrayList<Course> jsonToCourseList(JSONArray array) {
		
		ArrayList<Course> courses = new ArrayList<Course>();
		for (int i = 0; i < array.length(); i++) {
			
			try {
				JSONObject object = array.getJSONObject(i);
				String name = object.getString(COURSE_NAME_TAG);
				String teacher = object.getString(COURSE_TEACHER_TAG);
				String description = null;
				try {
					description = object.getString(COURSE_DESCRIPTION_TAG);
				} catch (JSONException ex) {
					// La descripción es opcional, no se hace nada
				}
					
				courses.add(new Course(name, teacher, description));
			} catch (Exception ex) {
				// El JSON no es válido
				return null;
			}			
		}
		
		return courses;
	}
	
	public void saveList(FileOutputStream outputStream) {
		
		JSONArray array = courseListToJson(mCourseList);
		try {			
			outputStream.write(array.toString().getBytes());
		} catch (Exception ex) {
			Log.d(TAG, ex.getMessage());
		}
	}
	
	private boolean restoreList() {
				
		try {
			File file = new File(getActivity().getFilesDir(), COURSE_LIST_FILENAME);
			BufferedInputStream buffer = new BufferedInputStream(getActivity().openFileInput(COURSE_LIST_FILENAME));
			
		    byte[] bytes = new byte[(int) file.length()];			
			buffer.read(bytes, 0, bytes.length);
	        buffer.close();
	        
	        String jsonString = new String(bytes); 
			JSONArray array = new JSONArray(jsonString);
			ArrayList<Course> courses = jsonToCourseList(array);
			if (courses != null) {
				mCourseList = courses;
				return true;
			}
			
		} catch (Exception ex) {
		}
		
		mCourseList = null;
		return false;
	}
}
