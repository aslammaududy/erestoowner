package me.aslammaududy.erestoowner.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Layout {
    @SerializedName("id_layout")
    @Expose
    private String idLayout;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("nomor_meja")
    @Expose
    private String nomorMeja;

    public String getIdLayout() {
        return idLayout;
    }

    public void setIdLayout(String idLayout) {
        this.idLayout = idLayout;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorMeja() {
        return nomorMeja;
    }

    public void setNomorMeja(String nomorMeja) {
        this.nomorMeja = nomorMeja;
    }

}
