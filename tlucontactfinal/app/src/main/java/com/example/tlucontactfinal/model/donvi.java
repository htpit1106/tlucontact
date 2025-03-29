package com.example.tlucontactfinal.model;

import java.io.Serializable;

public class donvi implements Serializable {
    String id, tendv, sdt, email, avatar, thongtin;
    public donvi(String id, String tendv, String sdt, String email, String avatar, String thongtin){
        this.id = id;
        this.tendv = tendv;
        this.sdt = sdt;
        this.email = email;
        this.avatar = avatar;
        this.thongtin = thongtin;
    }

    public donvi( String tendv, String sdt, String email, String avatar, String thongtin){
        this.tendv = tendv;
        this.sdt = sdt;
        this.email = email;
        this.avatar = avatar;
        this.thongtin = thongtin;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTendv() {
        return tendv;
    }

    public void setTendv(String tendv) {
        this.tendv = tendv;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getThongtin() {
        return thongtin;
    }

    public void setThongtin(String thongtin) {
        this.thongtin = thongtin;
    }
}
