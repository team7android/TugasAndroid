package com.andlab.tugasandroid.Result;

import com.google.gson.annotations.SerializedName;

/**
 * Created by greatsoft on 09/11/18.
 */

public class Result {
    @SerializedName("id_user")
    String id_user;

    @SerializedName("username")
    String username;

    @SerializedName("nama_user")
    String nama_user;

    @SerializedName("alamat")
    String alamat;

    @SerializedName("no_hp")
    String no_hp;

    public Result(){}

    public Result(String id_user, String username, String nama_user, String alamat, String no_hp) {
        this.id_user = id_user;
        this.username = username;
        this.nama_user = nama_user;
        this.alamat = alamat;
        this.no_hp = no_hp;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }
}
