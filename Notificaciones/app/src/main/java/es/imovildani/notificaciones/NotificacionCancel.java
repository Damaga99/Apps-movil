package es.imovildani.notificaciones;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificacionCancel extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Cargamos el gestor de notificaciones
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);

        // Si la notificacion qrecibida es la que tiene nuestro ID
        if (intent.getExtras().getInt("notification_id") == MainActivity.NOTIFICATION_ID)
        {
            // La eliminamos ejecutando el cancel() del NotificationManager
            notificationManager.cancel(MainActivity.NOTIFICATION_ID);
        }
    }
}
