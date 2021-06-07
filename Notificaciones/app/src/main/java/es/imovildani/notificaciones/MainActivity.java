package es.imovildani.notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    Button boton1, boton2,boton3,boton4,boton5,boton6 ;
    PendingIntent pendingIntentLlamada,pendingIntentGoogle,pendingIntentCancelar ;
    private String CHANNEL_ID = "ID_CHANNEL";

    public static final int NOTIFICATION_ID = 10;
    public static final int NOTIFICATION_FOTOGRAFIA = 11;
    public static final int NOTIFICATION_TEXTO = 12;
    public static final int NOTIFICATION_PERSONALIZADA = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton1 =findViewById(R.id.button);
        boton2 =findViewById(R.id.button2);
        boton3 =findViewById(R.id.button3);
        boton4= findViewById(R.id.button4);
        boton5= findViewById(R.id.button5);
        boton6= findViewById(R.id.button6);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotificationChannel();
                lanzarNotificacionSimple();
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                createNotificationChannel();

                lanzarNotificacion();
            }
        });
        

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotificationChannel();

                lanzarNotificacion3Intents();

            }
        });


        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotificationChannel();

                lanzarNotificacion4Ampliable();

            }
        });

        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotificationChannel();

                lanzarNotificacion4AmpliableTEXTO();

            }
        });

        boton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotificationChannel();

                lanzarNotificacion5();

            }
        });


    }


    public void lanzarNotificacion5(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        Bitmap imagen = BitmapFactory.decodeResource(getResources(), R.drawable.imagen2);

        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.notificacion);
        RemoteViews notificacionLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notificacion_expandida);
        notificationLayout.setTextViewText(R.id.textView, "Notificación personalizada");
        notificacionLayoutExpanded.setTextViewText(R.id.textView2, "Notificación personalizada expandida");
        notificacionLayoutExpanded.setImageViewBitmap(R.id.imageView,  imagen );


        builder.setSmallIcon(R.drawable.ic_fotografia)
                .setSmallIcon(R.drawable.ic_fotografia)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificacionLayoutExpanded)
                .setAutoCancel(true)
                .setPriority(NotificationManagerCompat.IMPORTANCE_DEFAULT);

        // Lanzar la notificación
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_PERSONALIZADA, builder.build());
    }

    public void lanzarNotificacion4AmpliableTEXTO(){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_texto)
                .setContentTitle("Texto Capturado")
                .setContentText("Expande para la verlo ampliado")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(getString(R.string.texto)))
                .setPriority(NotificationManagerCompat.IMPORTANCE_DEFAULT);

        // Lanzar la notificación
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_TEXTO, builder.build());
    }


    public void lanzarNotificacion4Ampliable(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        Bitmap imagen = BitmapFactory.decodeResource(getResources(), R.drawable.imagen2);
        builder.setSmallIcon(R.drawable.ic_fotografia)
                .setContentTitle("Imagen Capturada")
                .setContentText("Expande para la verla ampliada")
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(imagen)
                        .bigLargeIcon(null))
                .setPriority(NotificationManagerCompat.IMPORTANCE_DEFAULT);

        // Lanzar la notificación
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_TEXTO, builder.build());
    }

    public void lanzarNotificacion3Intents(){
        // Constructor de notificaciones
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);

        builder.setSmallIcon(R.drawable.twitter_64);
        builder.setContentTitle("Título de notificación");
        builder.setContentText("Texto principal");
        builder.setSubText("Sub texto.");
        Bitmap bitmapLarge = BitmapFactory.decodeResource(getResources(), R.drawable.twitter_64);
        builder.setLargeIcon(bitmapLarge);
        builder.setTicker("Importante!");
        builder.setOngoing(false);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setVibrate(new long[] {1000,1000,1000,1000});
        Uri uriRing = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(uriRing);

// Definimos el PendingIntent de "Llamar al 112"
        // Crear Intent
        Intent mapsNotification = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California"));
        // Crear PendingIntent
        PendingIntent pendingIntentLlamada = PendingIntent.getActivity(this, 0, mapsNotification, PendingIntent.FLAG_UPDATE_CURRENT);
        // Asociar PendingIntent
        builder.setContentIntent(pendingIntentLlamada);


        // Definimos el PendingIntent de "Buscar por google"
        // Crear Intent
        Intent googleNotification = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.es"));
        // Crear PendingIntent
        PendingIntent pendingIntentGoogle = PendingIntent.getActivity(this, 0, googleNotification, PendingIntent.FLAG_UPDATE_CURRENT);
        // Asociar PendingIntent
        builder.setContentIntent(pendingIntentLlamada);


        // Definimos el PendingIntent de "Cancelar la notificacion"
        Intent cancelNotification = new Intent(this, NotificacionCancel.class);
        // Añadimos el ID de la notificacion en el intent para que el BroadcastReceiver pueda cerrar la notificación por su ID
        cancelNotification.putExtra("notification_id", NOTIFICATION_ID);
        // Crear PendingIntent
        PendingIntent pendingIntentCancel = PendingIntent.getBroadcast(this, 0, cancelNotification, PendingIntent.FLAG_CANCEL_CURRENT);


        // Añadimos los botones en cuestión para que realicen estas acciones
        builder.addAction(android.R.drawable.ic_menu_call, "Localizacion", pendingIntentLlamada);
        builder.addAction(android.R.drawable.ic_menu_search, "Buscar", pendingIntentGoogle);
        builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "Borrar notificación", pendingIntentCancel);

        // Para que sea permanente la notificacion
        builder.setOngoing(true);


        // Lanzar la notificación
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());


    }


    private void createNotificationChannel() {
        // Crea un canal de notificaciones, pero solo en API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManagerCompat.IMPORTANCE_DEFAULT;
            System.out.println(CHANNEL_ID);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Registra el canal con el sistema; ya no se pueden cambiar los parámetros
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



    public void lanzarNotificacionSimple(){
        // Constructor de notificaciones
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);

        // Establecer : icono pequeño, título, contenido, sub texto
       // builder.setSmallIcon(R.drawable.ic_facebook);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("Título de notificación");
        builder.setContentText("Texto principal");
        builder.setSubText("Sub texto.");

        // Desaparece después de pulsar sobre ella
        builder.setAutoCancel(true);

        // Lanzar la notificación
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void lanzarNotificacion(){
        // Constructor de notificaciones
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);

        //builder.setSmallIcon(R.drawable.ic_facebook);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentTitle("Título de notificación");
        builder.setContentText("Texto principal");
        builder.setSubText("Sub texto.");
       // Bitmap bitmapLarge = BitmapFactory.decodeResource(getResources(), R.drawable.imagen);

        Bitmap bitmapLarge = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground);
        builder.setLargeIcon(bitmapLarge);
        builder.setTicker("Importante!");
        builder.setOngoing(false);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setVibrate(new long[] {1000,1000,1000,1000});
        Uri uriRing = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(uriRing);

        // Lanzar la notificación
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

}