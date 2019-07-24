package me.aslammaududy.erestoowner.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order extends Menu {
    @SerializedName("id_pesanan")
    @Expose
    private String idPesanan;
    @SerializedName("catatan")
    @Expose
    private String note;
    @SerializedName("jumlah")
    @Expose
    private int count;
    @SerializedName("meja")
    @Expose
    private String meja;

    public String getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(String idPesanan) {
        this.idPesanan = idPesanan;
    }

    public String getMeja() {
        return meja;
    }

    public void setMeja(String meja) {
        this.meja = meja;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
