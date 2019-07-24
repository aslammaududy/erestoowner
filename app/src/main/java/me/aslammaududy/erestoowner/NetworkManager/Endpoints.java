package me.aslammaududy.erestoowner.NetworkManager;

import java.util.List;

import me.aslammaududy.erestoowner.Model.Layout;
import me.aslammaududy.erestoowner.Model.Menu;
import me.aslammaududy.erestoowner.Model.Order;
import me.aslammaududy.erestoowner.Model.Payment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Endpoints {
    @POST("layout.php?aksi=buat")
    Call<ResponseBody> createLayouts(@Body List<Layout> layouts);

    @POST("layout.php?aksi=tambah")
    Call<ResponseBody> addLayouts(@Body List<Layout> layouts);

    @GET("layout.php?aksi=ambil")
    Call<List<Layout>> getLayouts();

    @GET("menu.php?aksi=ambil")
    Call<List<Menu>> getMenus();

    @POST("pesanan.php?aksi=tambah")
    Call<ResponseBody> addOrder(@Body Order order);

    @GET("pesanan.php?aksi=ambil")
    Call<List<Order>> getOrders();

    @POST("pembayaran.php?aksi=tambah")
    Call<ResponseBody> addPayment(@Body Payment payment);

    @GET("pembayaran.php?aksi=ambil")
    Call<List<Payment>> getPayment();
}
