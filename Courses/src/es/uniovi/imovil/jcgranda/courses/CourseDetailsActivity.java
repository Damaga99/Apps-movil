package es.uniovi.imovil.jcgranda.courses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;


public class CourseDetailsActivity extends ActionBarActivity {
	
	public static final String COURSE = "es.uniovi.imovil.jcgranda.courses.COURSE";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_details_activity);
		
		// Existe el contenedor del fragmento?
		if (findViewById(R.id.fragment_container) != null) {

			// Si estamos restaurando desde un estado previo no hacemos nada
			if (savedInstanceState != null) {
				return;
			}

			// Crear el fragmento pasándole el parámetro
			Intent intent = getIntent();
		    Course course = intent.getParcelableExtra(COURSE);
			CourseDetailsFragment fragment =
				CourseDetailsFragment.newInstance(course);
			
			// Añadir el fragmento al contenedor 'fragment_container'
			getSupportFragmentManager().beginTransaction()
					.add(R.id.fragment_container, fragment).commit();
		}
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
}
