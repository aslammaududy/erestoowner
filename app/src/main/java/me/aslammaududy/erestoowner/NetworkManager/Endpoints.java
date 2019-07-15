package me.aslammaududy.erestoowner.NetworkManager;

import java.util.List;

import me.aslammaududy.erestoowner.Model.Layout;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Endpoints {
    @POST("layout.php?aksi=tambah")
    Call<ResponseBody> addLayouts(@Body List<Layout> layouts);

    @GET("layout.php?aksi=ambil")
    Call<List<Layout>> getLayouts();
}
