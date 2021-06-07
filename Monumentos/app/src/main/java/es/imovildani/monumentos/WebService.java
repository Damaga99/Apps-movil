package es.imovildani.monumentos;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.List;
import java.util.ListIterator;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {

    private MutableLiveData<List<Monumento>> MonumentoMutableLiveData = new MutableLiveData<>();



    private RestService restService;

    public WebService(Context context) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        try {
            clientBuilder.sslSocketFactory(getSSLConfig(context).getSocketFactory());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // 5.5 Implementa el constructor de WebService. En el constructor incluye el código que
        //      crea la implementación del interface de Retrofit e inicializa la variable miembro
        //      restService.
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opendata.gijon.es/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(clientBuilder.build())
                .build();

        this.restService = retrofit.create(RestService.class);


        try{
            getSSLConfig(context);

        }catch(CertificateException e){
            Log.e("CertificateException", e.toString());
        }catch (IOException e){
            Log.e("IOException", e.toString());
        }catch (KeyStoreException e){
            Log.e("KeyStoreException", e.toString());
        }catch (NoSuchAlgorithmException e){
            Log.e("NoSuchAlgorithmExcept", e.toString());
        }catch (KeyManagementException e){
            Log.e("KeyManagementException", e.toString());
        }



        // 5.6 Al final del constructor haz una llamada a updateBusStops() para que nuestro
        //     observable se inicialice al construir la clase.
        updateMonumento();


    }


    private void updateMonumento(){

        this.restService.getInfo().enqueue(new Callback<List<Monumento>>() {
            @Override
            public void onResponse(Call<List<Monumento>> call, Response<List<Monumento>> response) {
                List<Monumento> Monumento = response.body();


                MonumentoMutableLiveData.setValue(Monumento);
            }

            @Override
            public void onFailure(Call<List<Monumento>> call, Throwable t) {
                Log.e("Error", t.toString());
            }
        });


    }

    public MutableLiveData<List<Monumento>> getMonumento(){
        updateMonumento();

        return MonumentoMutableLiveData;
    }






    public MutableLiveData<Monumento> getMonumentoByName(String name) {
        // Actualizamos los datos
        updateMonumento();

        // Variable Monumentos que almacenara los datos del Monumentos
        MutableLiveData<Monumento> Monumentos = new MutableLiveData<>();

        Log.d("NOMBRE_WebService01", name);

        this.restService.getByTitulo(name).enqueue(new Callback<List<Monumento>>() {
            @Override
            public void onResponse(Call<List<Monumento>> call, Response<List<Monumento>> response) {
                List<Monumento> MonumentosRecibidos = response.body();

                ListIterator<Monumento> iter = MonumentosRecibidos.listIterator();
                while(iter.hasNext()){
                    // Guardamos el Monumentos actual
                    Monumento MonumentoActual = iter.next();
                    String nombreMonumentoActual = MonumentoActual.getTitulo();
                    // Examinamos la condición
                    if(nombreMonumentoActual.equals(name)) {
                        Monumentos.setValue(MonumentoActual);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Monumento>> call, Throwable t) {
                Log.e("Error", t.toString());
            }
        });

        // Retornamos el observable
        return Monumentos;
    }



    private static SSLContext getSSLConfig(Context context) throws CertificateException, IOException,
            KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        // Loading CAs from an InputStream
        CertificateFactory cf = null;
        cf = CertificateFactory.getInstance("X.509");

        Certificate ca;
        // I'm using Java7. If you used Java6 close it manually with finally.
        try (InputStream cert = context.getResources().openRawResource(R.raw.gijon_es)) {
            ca = cf.generateCertificate(cert);
            Log.d("SslUtilsAndroid", "ca = cf.generateCertificate(cert);");
        }

        // Creating a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);

        // Creating a TrustManager that trusts the CAs in our KeyStore.
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        // Creating an SSLSocketFactory that uses our TrustManager
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);
        //  x509TrustManager= (X509TrustManager) tmf.getTrustManagers()[0];
        //  sslContext.init(null, tmf.getTrustManagers(), new SecureRandom());
        return sslContext;
    }



}
