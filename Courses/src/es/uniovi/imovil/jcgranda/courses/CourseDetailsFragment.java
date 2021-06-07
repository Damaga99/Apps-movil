package es.uniovi.imovil.jcgranda.courses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CourseDetailsFragment extends Fragment {
	
	private static final String COURSE_ARG = "course";
	
	private Course mCourse = null;

	public static CourseDetailsFragment newInstance(Course course) {
		
		CourseDetailsFragment fragment = new CourseDetailsFragment();
		
		Bundle args = new Bundle();
        args.putParcelable(COURSE_ARG, course);
        fragment.setArguments(args);
        
		return fragment;
	}
	
	public CourseDetailsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView;
		rootView = inflater.inflate(R.layout.course_details_fragment, container, false);			
		
		// Si estamos restaurando desde un estado previo no hacemos nada
		mCourse = null;
		if (savedInstanceState != null) {
			
			mCourse = savedInstanceState.getParcelable(COURSE_ARG);
		} else {
								
			Bundle args = getArguments();
			if (args != null) {
				mCourse = args.getParcelable(COURSE_ARG);	    
			}
		}
		
		TextView tvDescription = (TextView) rootView.findViewById(R.id.textViewDesc);
		if (mCourse != null) {
			tvDescription.setText(mCourse.getDescription());
		} else {
			tvDescription.setText(null);
		}	
		return rootView;
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putParcelable(COURSE_ARG, mCourse);
	}

	public void setCourse(Course course) {
		
		mCourse = course;
		TextView tvDescription = (TextView) getView().findViewById(R.id.textViewDesc);
		if (mCourse != null) {
			tvDescription.setText(mCourse.getDescription());
		} else {
			tvDescription.setText(null);
		}		
	}
}
