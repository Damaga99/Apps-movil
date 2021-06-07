package es.imovildani.monumentos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MonumentoDetailsActivity extends AppCompatActivity {


    DetailsViewModel DetailsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView nombre = findViewById(R.id.txt_cda_Nombre);
        TextView email= findViewById(R.id.txt_email);
        TextView CP = findViewById(R.id.txt_postal);
        TextView desc = findViewById(R.id.txt_cda_Descripcion);
        TextView direc = findViewById(R.id.txt_direccion);
        TextView dist = findViewById(R.id.txt_distrito);

        TextView face = findViewById(R.id.txt_Facebook);
        TextView gplus = findViewById(R.id.txt_GooglePlus);
        TextView insta = findViewById(R.id.txt_Instagram);
        TextView link = findViewById(R.id.txt_Linkedin);
        TextView pint = findViewById(R.id.txt_Pinterest);
        TextView twit = findViewById(R.id.txt_Twitter);
        TextView yout = findViewById(R.id.txt_Youtube);

        TextView fx = findViewById(R.id.txt_fax);
        TextView hor= findViewById(R.id.txt_horario);
        TextView km = findViewById(R.id.txt_kml);
        TextView loc=findViewById(R.id.txt_localizacion);
        TextView linea = (TextView) findViewById(R.id.txt_cda_linea);
        TextView mat = findViewById(R.id.txt_materias);
        TextView ciudad = findViewById(R.id.txt_ciudad);

        TextView obser=findViewById(R.id.txt_observaciones);
        TextView plus= findViewById(R.id.txt_pluscode);
        TextView prov = findViewById(R.id.txt_provincia);
        TextView telef= findViewById(R.id.txt_telefono);

        TextView we= findViewById(R.id.txt_web);
        TextView idio=findViewById(R.id.txt_idioma);
        TextView frecuentes = findViewById(R.id.txt_preguntas);
        TextView etiquet = findViewById(R.id.txt_etiquetas);





        Intent intent = getIntent();
        Bundle bundle= intent.getExtras();
        String name = bundle.getString(MainActivity.NAME_REPLY);

        DetailsViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(DetailsViewModel.class);

        DetailsViewModel.setName(name);
        System.out.println(DetailsViewModel.getName());

        DetailsViewModel.getMonumento().observe(this, new Observer<Monumento>() {
            @Override
            public void onChanged(Monumento monumento) {
                System.out.println(monumento);


                String asignatura = monumento.getTitulo();
                String correo =monumento.getCorreoElectronico() ;
                String codigopostal =monumento.getCodigoPostal();
                String descripcion = Html.fromHtml(monumento.getDescripcion()).toString();

                String direccion=monumento.getDireccion();
                String distrito=monumento.getDistrito();

                String facebook=monumento.getFacebook();
                String googleplus=monumento.getGoogleplus();
                String instagram =monumento.getInstagram();
                String linkedin=monumento.getLinkedin();
                String pinterest=monumento.getPinterest();
                String twitter= monumento.getTwitter();
                String youtube = monumento.getYoutube();

                String fax=monumento.getFax();
                String horario=monumento.getHorario();
                String kml=monumento.getKml();
                String localizacion=monumento.getLocalizacion();

                String lin = monumento.getLineasBus();
                String materias=monumento.getMaterias();
                String ciu = monumento.getMunicipio();
                String Observaciones=monumento.getObservaciones();
                String pluscode=monumento.getPluscode();
                String Provincia=monumento.getProvincia();
                String telefono=monumento.getTelefono();
                String web=monumento.getWeb();
                String idioma=monumento.getIdioma();
                String preguntas=monumento.getPreguntasFrecuentes();
                String etiquetas=monumento.getEtiquetas();


                if(facebook.isEmpty()){                      face.setTextColor(Color.BLACK);
                }
                else{                    face.setTextColor(Color.BLUE);                }

                if(googleplus.isEmpty()){                    gplus.setTextColor(Color.BLACK);
                }
                else{                    gplus.setTextColor(Color.BLUE);                }
                if(instagram.isEmpty()){                    insta.setTextColor(Color.BLACK);
                }
                else{                    insta.setTextColor(Color.BLUE);                }
                if(linkedin.isEmpty()){                     link.setTextColor(Color.BLACK);
                }
                else{                    link.setTextColor(Color.BLUE);                }
                if(pinterest.isEmpty()){                    pint.setTextColor(Color.BLACK);
                }
                else{                    pint.setTextColor(Color.BLUE);                }
                if(twitter.isEmpty()){                    twit.setTextColor(Color.BLACK);
                }
                else{                    twit.setTextColor(Color.BLUE);
                }if(youtube.isEmpty()){                    yout.setTextColor(Color.BLACK);
                }
                else{                    yout.setTextColor(Color.BLUE);                }



                if(!codigopostal.isEmpty()){                    CP.setText(codigopostal);
                }else{                    CP.setText(" -");                }

                if(!distrito.isEmpty()){                    dist.setText(distrito);
                }else{                    dist.setText(" -");                }

                if(!fax.isEmpty()){                    fx.setText(fax);
                }else{                    fx.setText(" -");                }

                hor.setText(horario);


                if(!kml.isEmpty()){                    km.setText(kml);
                }else{                    km.setText(" -");                }

                if(!localizacion.isEmpty()){                    loc.setText(localizacion);
                }else{                    loc.setText(" -");                }

                if(!materias.isEmpty()){                   mat.setText(materias);
                }else{                    mat.setText(" -");                }

                if(!pluscode.isEmpty()){                    plus.setText(pluscode);
                }else{                    plus.setText(" -");                }

                if(!telefono.isEmpty()){                    telef.setText(telefono);
                }else{                    telef.setText(" -");                }

                if(!Observaciones.isEmpty()){                    obser.setText(Observaciones);
                }else{                    obser.setText(" -");                }

                if(!Provincia.isEmpty()){                    prov.setText(Provincia);
                }else{                    prov.setText(" -");                }



                if(!web.isEmpty()){                    we.setText(web);
                }else{                    we.setText(" -");                }

                if(!idioma.isEmpty()){                   idio.setText(idioma);
                }else{                    idio.setText(" -");
                }

                if(!preguntas.isEmpty()){                    frecuentes.setText(preguntas);
                }else{                    frecuentes.setText(" -");
                }
                if(!etiquetas.isEmpty()){                    etiquet.setText(etiquetas);
                }else{                    etiquet.setText(" -");
                }


                if(!correo.isEmpty()){                         email.setText(correo);
                }else{                    email.setText(" -");                }

                if(!direccion.isEmpty()){                      direc.setText(direccion);
                }else{                    direc.setText(" -");                }

                if(!asignatura.isEmpty()){                    nombre.setText(asignatura);
                }else{                    nombre.setText(" -");                }

                if(!descripcion.isEmpty()){                   desc.setText(descripcion);
                }else{                    desc.setText(" -");                }

                if(!lin.isEmpty()){                           linea.setText(lin);
                }else{                    linea.setText(" -");                }

                if(!ciu.isEmpty()){                           ciudad.setText(ciu);
                }else{                    ciudad.setText(" -");                }



            }

        });
    }

}
