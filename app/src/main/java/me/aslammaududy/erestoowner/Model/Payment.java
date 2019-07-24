package me.aslammaududy.erestoowner.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payment extends Order {
    @SerializedName("id_pembayaran")
    @Expose
    private String idPembayaran;
    @SerializedName("total")
    @Expose
    private String total;

    public String getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(String idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
