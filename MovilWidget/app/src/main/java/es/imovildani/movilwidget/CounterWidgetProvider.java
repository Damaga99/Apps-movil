package es.imovildani.movilwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

/**
 * Implementation of App Widget functionality.
 */
public class CounterWidgetProvider extends AppWidgetProvider {

    private String INCREMENT_BUTTON ="es.imovildani.movilwidget";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.counter_widget_provider);
        views.setTextViewText(R.id.maintext, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        // Recorrer los widgets que deben ser actualizados
        for (int i = 0; i < appWidgetIds.length; i++) {
            int appWidgetId = appWidgetIds[i];

            // Obtener la vista para el widget
            RemoteViews views = new RemoteViews(context.getPackageName(),
                    R.layout.counter_widget_provider);

            // La cadena debería estar en un recurso, pero es sólo un mensaje de
            // depuración que vamos a eliminar en breve
            PreferencesManager p = new PreferencesManager(context, appWidgetId);
            p.saveCount(p.readCount()+1);
            views.setTextViewText(R.id.maintext, "Cuenta: " + p.readCount());

            String name = p.readName();
            views.setTextViewText(R.id.name, name);

            // Indicar al appWidgetManager que actualice el widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
            int id =appWidgetId;
            Intent intent = new Intent(context, CounterWidgetProvider.class);
            intent.setAction(INCREMENT_BUTTON);
            intent.putExtra("appWidgetId " , appWidgetId);



            int requestCode = appWidgetId;
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.increment_button, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);

        }



    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null
                && intent.getAction().equals(INCREMENT_BUTTON)) {
            onIncrementButton(context, intent);
        }

        // Llamar a la clase padre para el resto de los intents
        super.onReceive(context, intent);
    }

    private void onIncrementButton(Context context, Intent intent) {
        int appWidgetId = intent.getExtras().getInt("appWidgetId ");

        Log.d("DEBUG_onIncrementButton","............................................"+appWidgetId);


        int ids[] = { appWidgetId };
        onUpdate(context, AppWidgetManager.getInstance(context), ids);
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}