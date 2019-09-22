package ru.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Division implements Parcelable{
    @SerializedName("iddevision")
    private int iddevision;
    @SerializedName("namedevision")
    private String namedevision;
    @SerializedName("idcity")
    private int idcity;

    public static Parcelable.Creator<Division> getCREATOR() {        return CREATOR;    }

    private Division(Parcel in) {
        iddevision = in.readInt();
        namedevision = in.readString();
        idcity = in.readInt();
    }

    public int getIddevision() {        return iddevision;    }
    public void setIddevision(int iddevision) {        this.iddevision = iddevision;    }
    public String getNamedevision() {        return namedevision;    }
    public void setNamedevision(String namedevision) {        this.namedevision = namedevision;    }
    public int getIdcity() {        return idcity;    }
    public void setIdcity(int idcity) {        this.idcity = idcity;    }

    private static final Parcelable.Creator<Division> CREATOR = new Parcelable.Creator<Division>() {
        @Override
        public Division createFromParcel(Parcel in) {            return new Division(in);        }
        @Override
        public Division[] newArray(int size) {            return new Division[size];        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong( iddevision);
        dest.writeString( namedevision);
        dest.writeInt( idcity);
    }
}
