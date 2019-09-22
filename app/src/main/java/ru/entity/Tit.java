package ru.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Tit implements Parcelable {
    @SerializedName("idtit")
    private int idtit;
    @SerializedName("nametit")
    private String nametit;

    public static final Creator<Tit> CREATOR = new Creator<Tit>() {
        @Override
        public Tit createFromParcel(Parcel in) {
            return new Tit(in);
        }

        @Override
        public Tit[] newArray(int size) {            return new Tit[size];        }
    };

    protected Tit(Parcel in) {
        idtit   = in.readInt();
        nametit = in.readString();
    }

    public int getIdtit() {        return idtit;    }
    public String getNametit() {        return nametit;    }
    public void setIdtit(int idtit) {        this.idtit = idtit;    }
    public void setNametit(String nametit) {        this.nametit = nametit;    }

    @Override
    public int describeContents() {        return 0;    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( idtit);
        dest.writeString( nametit);
    }
}
