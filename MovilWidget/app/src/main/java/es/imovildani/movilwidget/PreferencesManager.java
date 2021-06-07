package es.imovildani.movilwidget;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

public class PreferencesManager {

    private static final String FILE_NAME =
            "es.imovildani.movilwidget.increment";
    private static final String COUNT = "COUNT";
    private static final String NAME = "NAME";

    SharedPreferences mSharedPreferences;


    public PreferencesManager(Context context, int appWidgetId) {
        mSharedPreferences = context.getSharedPreferences(
                FILE_NAME + appWidgetId, Context.MODE_PRIVATE);
    }

    public int readCount() {
        int defaultCountValue = 0;
        return mSharedPreferences.getInt(COUNT, defaultCountValue);
    }

    public void saveCount(int count) {
        SharedPreferences.Editor prefsEditor = mSharedPreferences.edit();
        prefsEditor.putInt(COUNT, count);
        prefsEditor.commit();
    }


    public String readName() {
        String defaultName = "";
        return mSharedPreferences.getString(NAME, defaultName);
    }

    public void saveName(String name) {
        SharedPreferences.Editor prefsEditor = mSharedPreferences.edit();
        prefsEditor.putString(NAME, name);
        prefsEditor.commit();
    }
}