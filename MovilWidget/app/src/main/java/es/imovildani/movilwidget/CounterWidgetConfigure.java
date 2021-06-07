package es.imovildani.movilwidget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CounterWidgetConfigure extends AppCompatActivity implements View.OnClickListener{


    private int mAppWidgetId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_widget_configure);
        setResult(RESULT_CANCELED);
        obtainAppWidgetId();
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }


    private void obtainAppWidgetId() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // Si no llega un ID correcto, cancelamos la actividad
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }
    }


    @Override
    public void onClick(View view) {
        // Guardar el nombre dado por el usuario
        TextView name = (TextView) findViewById(R.id.edit_text_name);
        PreferencesManager pm = new PreferencesManager(this, mAppWidgetId);
        pm.saveName(name.getText().toString());

        // Lanzar el intent para la primera actualización
        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE, null,
                this, CounterWidgetProvider.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                new int[] {mAppWidgetId});
        sendBroadcast(intent);

        // Devolver el resultado
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }

}
