package es.imovildani.monumentos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestService {
    @GET("descargar.php?id=743&tipo=JSON")
    Call<List<Monumento>> getInfo();

    @GET("descargar.php?id=743&tipo=JSON")
    Call<List<Monumento>> getByTitulo(@Query("titulo") String titulo);

    @GET("descargar.php?id=743&tipo=JSON")
    Call<List<Monumento>> getLatitud();

    @GET("descargar.php?id=743&tipo=JSON")
    Call<List<Float>> getLongitud();

}
