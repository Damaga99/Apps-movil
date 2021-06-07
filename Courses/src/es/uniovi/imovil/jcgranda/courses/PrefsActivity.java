package es.uniovi.imovil.jcgranda.courses;

import android.os.Bundle;
import android.preference.PreferenceActivity;


public class PrefsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
	
        super.onCreate(savedInstanceState);

        // Cargar las preferencias desde el fichero
        addPreferencesFromResource(R.xml.preferences);
    }
}
