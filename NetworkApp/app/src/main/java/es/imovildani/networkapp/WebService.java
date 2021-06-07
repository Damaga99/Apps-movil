package es.imovildani.networkapp;

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
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {

    private MutableLiveData<Llegadas> llegadasMutableLiveData = new MutableLiveData<>();
    private RestService restService;


    public WebService(Context context){

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
                .baseUrl("http://datos.gijon.es/")
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
        updateBusStops();


    }




private void updateBusStops(){



    this.restService.getInfo().enqueue(new Callback<BusStop>() {
        @Override
        public void onResponse(Call<BusStop> call, Response<BusStop> response) {
            BusStop busStop = response.body();
                //.......
                llegadasMutableLiveData.setValue(busStop.getLlegadas());
        }

        @Override
        public void onFailure(Call<BusStop> call, Throwable t) {
            Log.e("Error", t.toString());
        }
    });


}

    public MutableLiveData<Llegadas> getBusStops(){
        updateBusStops();

        return llegadasMutableLiveData;
    }




    // private static X509TrustManager x509TrustManager;
    // 6.3 Crea en WebService el método SSLContext basado en la información que aparece en la página
    //      https://developer.android.com/training/articles/security-ssl. El código es el siguiente:
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
