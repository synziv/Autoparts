package com.example.recyclertp.ui.fournisseurs;

import android.os.Parcel;
import android.os.Parcelable;

public class Fournisseur implements Parcelable {
    private int id;
    private String name;
    private String ad1;
    private String ad2;
    private String city;
    private String prov;
    private String cP;
    private String tel;
    private String contact;

    public String getName() {
        return name;
    }

    public String getAd1() {
        return ad1;
    }

    public String getAd2() {
        return ad2;
    }


    public String getCity() {
        return city;
    }

    public String getProv() {
        return prov;
    }

    public String getcP() {
        return cP;
    }

    public String getTel() {
        return tel;
    }

    public String getContact() {
        return contact;
    }

    public Fournisseur(int id, String name, String ad1, String ad2, String city, String prov, String cP, String tel, String contact){
        this.id=id;
        this.name=name;
        this.ad1=ad1;
        this.ad2=ad2;
        this.city=city;
        this.prov=prov;
        this.cP=cP;
        this.tel=tel;
        this.contact=contact;
    }
    protected Fournisseur(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Fournisseur> CREATOR = new Creator<Fournisseur>() {
        @Override
        public Fournisseur createFromParcel(Parcel in) {
            return new Fournisseur(in);
        }

        @Override
        public Fournisseur[] newArray(int size) {
            return new Fournisseur[size];
        }
    };
}
