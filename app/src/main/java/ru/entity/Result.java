package ru.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable {
    @SerializedName("timeclose")
    private long timeclose;
    @SerializedName("iddivision")
    private int iddivision;
    @SerializedName("kvcount")
    private int kvcount;
    @SerializedName("skvcount")
    private int skvcount;
    @SerializedName("namedivision")
    private String namedivision;

    private Result(Parcel in) {
        timeclose = in.readLong();
        iddivision = in.readInt();
        kvcount = in.readInt();
        skvcount = in.readInt();
        namedivision = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) { return new Result(in);        }
        @Override
        public Result[] newArray(int size) {            return new Result[size];        }
    };

    public long getTimeclose() {        return timeclose;    }
    public void setTimeclose(long timeclose) {        this.timeclose = timeclose;    }
    public int getIddivision() {        return iddivision;    }
    public void setIddivision(int iddivision) {        this.iddivision = iddivision;    }
    public int getKvcount() {        return kvcount;    }
    public void setKvcount(int kvcount) {        this.kvcount = kvcount;    }
    public int getSkvcount() {        return skvcount;    }
    public void setSkvcount(int skvcount) {        this.skvcount = skvcount;    }
    public String getNamedivision() {        return namedivision;    }
    public void setNamedivision(String namedivision) {        this.namedivision = namedivision;    }
    public Result() {}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(timeclose);
        dest.writeInt(iddivision);
        dest.writeInt(kvcount);
        dest.writeInt(skvcount);
        dest.writeString(namedivision);
    }
}
