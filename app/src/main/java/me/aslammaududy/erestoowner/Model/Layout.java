package me.aslammaududy.erestoowner.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Layout {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("nomor_meja")
    @Expose
    private String nomorMeja;

    public Layout(String id, String nama, String nomorMeja) {
        this.id = id;
        this.nama = nama;
        this.nomorMeja = nomorMeja;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
