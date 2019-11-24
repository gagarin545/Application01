package ru.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Division implements Parcelable{
    @SerializedName("iddivision")
    private Integer iddivision;
    @SerializedName("namedivision")
    private String namedivision;
    @SerializedName("idcity")
    private int idcity;

    public static Parcelable.Creator<Division> getCREATOR() {        return CREATOR;    }

    private Division(Parcel in) {
        iddivision = in.readInt();
        namedivision = in.readString();
        idcity = in.readInt();
    }

    public int getIddivision() {        return iddivision;    }
    public void setIddivision(int iddivision) {        this.iddivision = iddivision;    }
    public String getNamedivision() {        return namedivision;    }
    public void setNamedivision(String namedivision) {        this.namedivision = namedivision;    }
    public int getIdcity() {        return idcity;    }    public void setIdcity(int idcity) {        this.idcity = idcity;    }

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
        dest.writeInt( iddivision);
        dest.writeString( namedivision);
        dest.writeInt( idcity);
    }
}
