package ru.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Workers implements Parcelable {
    @SerializedName("idworker")
    private int worker;
    @SerializedName("name")
    private String name;
    @SerializedName("imei")
    private  String imei;
    @SerializedName("iddivision")
    private int[] iddivision;

    public Workers(Parcel in) {
        worker = in.readInt();
        name = in.readString();
        imei = in.readString();
        iddivision = in.createIntArray();
    }

    public int getWorker() {        return worker;    }
    public void setWorker(int worker) {        this.worker = worker;    }
    public String getName() {        return name;    }
    public void setName(String name) {        this.name = name;    }
    public String getImei() {        return imei;    }
    public void setImei(String imei) {        this.imei = imei;    }
    public int[] getIddivision() {        return iddivision;    }
    public void setIddivision(int[] iddivision) {        this.iddivision = iddivision;    }
    public static final Creator<Workers> CREATOR = new Creator<Workers>() {
        @Override
        public Workers createFromParcel(Parcel in) {
            return new Workers(in);
        }
        @Override
        public Workers[] newArray(int size) {
            return new Workers[size];
        }
    };
    @Override
    public int describeContents() {        return 0;    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(worker);
        dest.writeString(name);
        dest.writeString(imei);
        dest.writeIntArray(iddivision);
    }
}
