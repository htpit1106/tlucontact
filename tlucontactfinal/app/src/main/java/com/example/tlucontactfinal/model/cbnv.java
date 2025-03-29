package com.example.tlucontactfinal.model;

import java.io.Serializable;

public class cbnv implements Serializable {
    String id, tencb, sdt, email, chucvu, avatar, thongtin;

    public cbnv() {
    }

    public cbnv(String id, String tencb, String sdt, String email, String chucvu, String avatar, String thongtin) {
        this.id = id;
        this.tencb = tencb;
        this.sdt = sdt;
        this.email = email;
        this.chucvu = chucvu;
        this.avatar = avatar;
        this.thongtin = thongtin;
    }

    public cbnv( String tencb, String sdt, String email, String chucvu, String avatar, String thongtin) {
        this.tencb = tencb;
        this.sdt = sdt;
        this.email = email;
        this.chucvu = chucvu;
        this.avatar = avatar;
        this.thongtin = thongtin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTencb() {
        return tencb;
    }

    public void setTencb(String tencb) {
        this.tencb = tencb;
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

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
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
