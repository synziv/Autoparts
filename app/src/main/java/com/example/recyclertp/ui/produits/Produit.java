package com.example.recyclertp.ui.produits;

import android.os.Parcel;
import android.os.Parcelable;

public class Produit implements Parcelable {
    public String getTitle() {
        return title;
    }

    public String getModelNo() {
        return modelNo;
    }

    public String getCode() {
        return code;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getInventory() {
        return inventory;
    }

    private int id;
    private String title;
    private String modelNo;
    private String code;
    private double unitPrice;
    private int inventory;
    private int supplierId;

    public Produit(int id, String title, String modelNo, String code, double unitPrice, int inventory, int supplierId){
        this.id=id;
        this.title = title;
        this.modelNo=modelNo;
        this.code=code;
        this.unitPrice=unitPrice;
        this.inventory=inventory;
        this.supplierId=supplierId;
    }

    protected Produit(Parcel in) {
        id = in.readInt();
        title = in.readString();
        modelNo = in.readString();
        code = in.readString();
        unitPrice = in.readDouble();
        inventory = in.readInt();
        supplierId = in.readInt();
    }

    public static final Creator<Produit> CREATOR = new Creator<Produit>() {
        @Override
        public Produit createFromParcel(Parcel in) {
            return new Produit(in);
        }

        @Override
        public Produit[] newArray(int size) {
            return new Produit[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(modelNo);
        dest.writeString(code);
        dest.writeDouble(unitPrice);
        dest.writeInt(inventory);
        dest.writeInt(supplierId);
    }
}
