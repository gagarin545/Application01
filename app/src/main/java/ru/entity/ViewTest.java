package ru.entity;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class ViewTest  implements Parcelable {
    @SerializedName("service")
    private long service;
    @SerializedName("ip_address")
    private String ip_address;
    @SerializedName("port")
    private String port;
    @SerializedName("slot")
    private String slot ;
    @SerializedName("ont")
    private String ont;
    @SerializedName("status")
    private String status;
    @SerializedName("profil")
    private String profil;
    @SerializedName("init_count")
    private String init_count;
    @SerializedName("snr_out")
    private float snr_out;
    @SerializedName("snr_in")
    private float snr_in;
    @SerializedName("l_Attenuation_out")
    private float l_Attenuation_out;
    @SerializedName("l_Attenuation_in")
    private float l_Attenuation_in;
    @SerializedName("s_Attenuation_out")
    private float s_Attenuation_out;
    @SerializedName("s_Attenuation_in")
    private float s_Attenuation_in;
    @SerializedName("power_out")
    private float power_out;
    @SerializedName("power_in")
    private float power_in;
    @SerializedName("v_previous_out")
    private float v_previous_out;
    @SerializedName("v_previous_in")
    private float v_previous_in;
    @SerializedName("v_current_out")
    private float v_current_out;
    @SerializedName("v_current_in")
    private float v_current_in;
    @SerializedName("v_max_out")
    private float v_max_out;
    @SerializedName("v_max_in")
    private float v_max_in;
    @SerializedName("description")
    private String description;
    @SerializedName("sn")
    private String sn;
    @SerializedName("passwd")
    private String passwd;
    @SerializedName("ontVersion")
    private String ontVersion;
    @SerializedName("distance")
    private String distance;
    @SerializedName("status_port1")
    private String status_port1;
    @SerializedName("status_port2")
    private String status_port2;
    @SerializedName("status_port3")
    private String status_port3;
    @SerializedName("status_port4")
    private String status_port4;
    @SerializedName("V_port1")
    private String V_port1;
    @SerializedName("V_port2")
    private String V_port2;
    @SerializedName("V_port3")
    private String V_port3;
    @SerializedName("V_port4")
    private String V_port4;
    @SerializedName("macAddress")
    private String macAddress;
    @SerializedName("vlans")
    private String vlans;

    private ViewTest (Parcel in) {
        service = in.readLong();
        ip_address = in.readString();
        port = in.readString();
        slot = in.readString();
        ont = in.readString();
        status = in.readString();
        profil = in.readString();
        init_count = in.readString();
        snr_out = in.readFloat();
        snr_in = in.readFloat();
        l_Attenuation_out = in.readFloat();
        l_Attenuation_in = in.readFloat();
        s_Attenuation_out = in.readFloat();
        s_Attenuation_in = in.readFloat();
        power_out = in.readFloat();
        power_in = in.readFloat();
        v_previous_out = in.readFloat();
        v_previous_in = in.readFloat();
        v_current_out = in.readFloat();
        v_current_in = in.readFloat();
        v_max_out = in.readFloat();
        v_max_in = in.readFloat();
        description = in.readString();
        sn = in.readString();
        passwd = in.readString();
        ontVersion = in.readString();
        distance = in.readString();
        status_port1 = in.readString();
        status_port2 = in.readString();
        status_port3 = in.readString();
        status_port4 = in.readString();
        V_port1 = in.readString();
        V_port2 = in.readString();
        V_port3 = in.readString();
        V_port4 = in.readString();
        macAddress = in.readString();
        vlans = in.readString();
    }
    private static final Parcelable.Creator<ViewTest> CREATOR = new Parcelable.Creator<ViewTest>() {

        @Override
        public ViewTest createFromParcel(Parcel source) {
            return new ViewTest(source);
        }

        @Override
        public ViewTest[] newArray(int size) {
            return new ViewTest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(service);
        dest.writeString(ip_address);
        dest.writeString(port);
        dest.writeString(slot );
        dest.writeString(ont);
        dest.writeString(status);
        dest.writeString(profil);
        dest.writeString(init_count);
        dest.writeFloat(snr_out);
        dest.writeFloat(snr_in);
        dest.writeFloat(l_Attenuation_out);
        dest.writeFloat(l_Attenuation_in);
        dest.writeFloat(s_Attenuation_out);
        dest.writeFloat(s_Attenuation_in);
        dest.writeFloat(power_out);
        dest.writeFloat(power_in);
        dest.writeFloat(v_previous_out);
        dest.writeFloat(v_previous_in);
        dest.writeFloat(v_current_out);
        dest.writeFloat(v_current_in);
        dest.writeFloat(v_max_out);
        dest.writeFloat(v_max_in);
        dest.writeString(description);
        dest.writeString(sn);
        dest.writeString(passwd);
        dest.writeString(ontVersion);
        dest.writeString(distance);
        dest.writeString(status_port1);
        dest.writeString(status_port2);
        dest.writeString(status_port3);
        dest.writeString(status_port4);
        dest.writeString(V_port1);
        dest.writeString(V_port2);
        dest.writeString(V_port3);
        dest.writeString(V_port4);
        dest.writeString(macAddress);
        dest.writeString(vlans);
    }
    public long getService() {        return service;    }
    public String getIp_address() {        return ip_address;    }
    public String getPort() {        return port;    }
    public String getSlot() {        return slot;    }
    public String getOnt() {        return ont;   }
    public String getStatus() {        return status;    }
    public String getProfil() {        return profil;    }
    public String getInit_count() {        return init_count;    }
    public float getSnr_out() {        return snr_out;    }
    public float getSnr_in() {        return snr_in;    }
    public float getL_Attenuation_out() {        return l_Attenuation_out;    }
    public float getL_Attenuation_in() {        return l_Attenuation_in;    }
    public float getS_Attenuation_out() {        return s_Attenuation_out;    }
    public float getS_Attenuation_in() {        return s_Attenuation_in;    }
    public float getPower_out() {        return power_out;    }
    public float getPower_in() {        return power_in;    }
    public float getV_previous_out() {        return v_previous_out;    }
    public float getV_previous_in() {        return v_previous_in;    }
    public float getV_current_out() {        return v_current_out;    }
    public float getV_current_in() {        return v_current_in;    }
    public float getV_max_out() {        return v_max_out;    }
    public float getV_max_in() {        return v_max_in;    }
    public String getDescription() {        return description;    }
    public String getSn() {        return sn;    }
    public String getPasswd() {        return passwd;    }
    public String getOntVersion() {        return ontVersion;    }
    public String getDistance() {        return distance;    }
    public String getStatus_port1() {        return status_port1;    }
    public String getStatus_port2() {        return status_port2;    }
    public String getStatus_port3() {        return status_port3;    }
    public String getStatus_port4() {        return status_port4;    }
    public String getV_port1() {        return V_port1;    }
    public String getV_port2() {        return V_port2;    }
    public String getV_port3() {        return V_port3;    }
    public String getV_port4() {        return V_port4;    }
    public String getMacAddress() {        return macAddress;    }
    public String getVlans() {        return vlans;    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        String color= "<font COLOR='#33B5E5'><b>", b = ": </b></font>";


        if( profil != null)
            return  color + "логин" + b + service + "<br>" +
                    color + "ip_адрес" + b + ip_address + "<br>" +
                    color + "слот" + b + port + "<br>" +
                    color + "порт" + b + slot + "<br>" +
                    color + "статус" + b + (Integer.valueOf(status)>0 ? "On":"Off") + "<br>" +
                    color + "Профиль" + b + profil + "<br>" +
                    color + "Инициялизации" + b + init_count + "<br>" +
                    color + "Сигнал/шум" + b  + snr_out + " " + snr_in + "<br>" +
                    color + "Линия зат." + b  + String.format("%.2f  %.2f",l_Attenuation_in, l_Attenuation_out) + "<br>" +
                    color + "Сигнал зат." + b  + String.format("%.2f  %.2f",s_Attenuation_in, s_Attenuation_out) + "<br>" +
                    color + "Power" +  b + String.format("%.2f %.2f",power_in,power_out) + "<br>" +
                    color + "Пред.скорость" + b + "\t" + String.format("%.2f\t%.2f",v_previous_in, v_previous_out ) + "<br>" +
                    color + "Тек.скорость" + b + "\t" + String.format("%.2f\t%.2f", v_current_in,v_current_out) + "<br>" +
                    color + "Max скорость" + b + "\t" + String.format("%.2f\t%.2f",v_max_in,v_max_out) + "<br>"+
                    color + "Vlan" + b  + vlans;
        else
            return  color + "логин" + b + service + "<br>" +
                    color + "ip_адрес" + b + ip_address + "<br>" +
                    color + "слот" + b + slot + "<br>" +
                    color + "порт" + b + port + "<br>" +
                    color + "онт" + b + ont + "<br>" +
                    color + "статус" + b + (Integer.valueOf(status)>0 ? "On":"Off") + "<br>" +
                    color + "Описание" + b  + description + "<br>" +
                    color + "С.номер" + b  + sn + "<br>" +
                    color + "К.слово" + b  + passwd + "<br>" +
                    color + "Версия онт" + b  + ontVersion + "<br>" +
                    color + "Расстояние" + b  + distance + "<br>" +
                    color + "Power" +  b + String.format("%.2f", power_in) + "<br>" +
                    color + "порт(1)" + b  + (Integer.valueOf(status_port1)>0 ? "On":"Off") + "<br>" +
                    color + "порт(2)" + b  + (Integer.valueOf(status_port1)>0 ? "On":"Off") + "<br>" +
                    color + "порт(3)" + b  + (Integer.valueOf(status_port1)>0 ? "On":"Off") + "<br>" +
                    color + "порт(4)" + b  + (Integer.valueOf(status_port1)>0 ? "On":"Off") + "<br>" +
                    color + "macAddress" + b  + macAddress +  "<br>" +
                    color + "Vlan" + b  + vlans;
    }
}
