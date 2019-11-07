package ru.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class WorkerResult implements Parcelable {
    @SerializedName("timeclose")
    private long timeclose;
    @SerializedName("idworker")
    private int idworker;
    @SerializedName("count")
    private int count;
    @SerializedName("name")
    private String name;

    public WorkerResult() {}

    protected WorkerResult(Parcel in) {
        timeclose = in.readLong();
        idworker = in.readInt();
        count = in.readInt();
        name = in.readString();
    }

    public static final Creator<WorkerResult> CREATOR = new Creator<WorkerResult>() {
        @Override
        public WorkerResult createFromParcel(Parcel in) {
            return new WorkerResult(in);
        }

        @Override
        public WorkerResult[] newArray(int size) {
            return new WorkerResult[size];
        }
    };

    public long getTimeclose() {        return timeclose;    }
    public void setTimeclose(long timeclose) {        this.timeclose = timeclose;    }
    public int getIdworker() {        return idworker;    }
    public void setIdworker(int idworker) {        this.idworker = idworker;    }
    public int getCount() {        return count;    }
    public void setCount(int count) {        this.count = count;    }
    public String getName() {        return name;    }
    public void setName(String name) {        this.name = name;    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(timeclose);
        dest.writeInt(idworker);
        dest.writeInt(count);
        dest.writeString(name);
    }
}
