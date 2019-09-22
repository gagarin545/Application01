package ru.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Technology implements Parcelable {
    @SerializedName( "idTechnology")
    private long idTechnology;
    @SerializedName( "mameTechnology")
    private String nametechnology;

    public Technology(long idTechnology) {}
    public long getIdTechnology() {        return idTechnology;    }
    public void setIdTechnology(long idTechnology) {        this.idTechnology = idTechnology;    }
    public String getNametechnology() {        return nametechnology;    }
    public void setNametechnology(String nametechnology) {        this.nametechnology = nametechnology;    }

    public static Parcelable.Creator<Technology> getCREATOR() {        return CREATOR;    }
    private static final Parcelable.Creator<Technology> CREATOR = new Parcelable.Creator<Technology>() {
        @Override
        public Technology createFromParcel(Parcel in) {            return new Technology(in);        }
        @Override
        public Technology[] newArray(int size) {            return new Technology[size];        }
    };
    private Technology(Parcel in) {
        idTechnology = in.readInt();
        nametechnology = in.readString();
    }

    @Override
    public int describeContents() {        return 0;    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idTechnology);
        dest.writeString(nametechnology);
    }
}
