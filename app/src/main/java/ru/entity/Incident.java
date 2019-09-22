package ru.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Incident implements Parcelable {
    @SerializedName("typeIncident") //Тип инцидента
    private int typeincident;
    public int getTypeincident() {        return typeincident;    }
    public void setTypeincident(int typeincident) {        this.typeincident = typeincident;    }
    @SerializedName("nIncident")    //№ инцидента
    private long n_incident;
    public long getN_incident() {        return n_incident;    }
    public void setN_incident(long n_incident) {        this.n_incident = n_incident;    }
    @SerializedName("idCity")   // Код города
    private int idcity;
    public int getIdcity() {        return idcity;    }
    public void setIdcity(int idcity) {        this.idcity = idcity;    }
    @SerializedName("service")   // Услуга
    private long service;
    public long getService() {        return service;    }
    public void setService(long service) {        this.service = service;    }
    @SerializedName("declared")  // Заявлено
    private String declared;
    public String getDeclared() { return declared;    }
    public void setDeclared(String declared) {        this.declared = declared;    }
    @SerializedName("controlTerm")  // Контрольное время
    private String controlterm;
    public String getControlterm() {        return controlterm;    }
    public void setControlterm(String controlterm) {        this.controlterm = controlterm;    }
    @SerializedName("controlTermSla")  // Контрольное время Sla
    private String controltermsla;
    public String getControltermsla() {        return controltermsla; }
    public void setControltermsla(String controltermsla) {        this.controltermsla = controltermsla; }
    @SerializedName("controlTermTask")  // Контрольное время Задачи
    private String controltermtask;
    public String getControltermtask() {        return controltermtask;    }
    public void setControltermtask(String controltermtask) {        this.controltermtask = controltermtask;    }
    @SerializedName( "createTime")    // Время создания наряда
    private Timestamp createtime;
    public Timestamp getCreatetime() {        return createtime;    }
    public void setCreatetime(Timestamp createtime) {        this.createtime = createtime;    }
    @SerializedName("clazz") // Кл.
    private String clazz;
    public String getClazz() {        return clazz; }
    public void setClazz(String clazz) {        this.clazz = clazz;    }
    @SerializedName("repet") // Повторность.
    private int repet = 0;
    public int getRepet() {        return repet;    }
    public void setRepet(int repet) {        this.repet = repet;    }
    @SerializedName("yield") // Доходность
    private int yield;
    public int getYield() {        return yield;    }
    public void setYield(int yield) {        this.yield = yield;    }
    @SerializedName("decisionTime") // Время Визита
    private Timestamp decisiontime;
    public Timestamp getDecisiontime() {        return decisiontime;    }
    public void setDecisiontime(Timestamp decisiontime) {        this.decisiontime = decisiontime;    }
    @SerializedName("nameClient") // Клиент
    private String nameclient;
    public String getNameclient() {        return nameclient;    }
    public void setNameclient(String nameclient) {        this.nameclient = nameclient;    }
    @SerializedName("labelOfService") // Уровень обслуживания
    private int labelofservice;
    public int getLabelofservice() {        return labelofservice;    }
    public void setLabelofservice(int labelofservice) {        this.labelofservice = labelofservice;    }
    @SerializedName("address")   //Адрес
    private String address;
    public String getAddress() {        return address;    }
    public void setAddress(String address) {        this.address = address;    }
    @SerializedName("room") //Помещение
    private String room;
    public String getRoom() {        return room;    }
    public void setRoom(String room) {        this.room = room;    }
    @SerializedName("phone") // Телефон
    private long phone;
    public long getPhone() {        return phone;    }
    public void setPhone(long phone) {        this.phone = phone;    }
    @SerializedName( "techData") // Тех. данные
    private String techdata;
    public String getTechdata() {        return techdata;    }
    public void setTechdata(String techdata) {        this.techdata = techdata;     }
    @SerializedName("comment")   // Комментарии
    private String comment;
    public String getComment() {        return comment;    }
    public void setComment(String comment) {        this.comment = comment;    }
    @SerializedName( "timeclose")    // Время закрытия
    private Timestamp timeclose;
    public Timestamp getTimeclose() {        return timeclose;    }
    public void setTimeclose(Timestamp timeclose) {        this.timeclose = timeclose;    }
    @SerializedName("workersEntity")
    private Workers worker; //Работник
    public Workers getWorker() {        return worker;    }
    public void setWorker(Workers worker) {        this.worker = worker;    }
    @SerializedName("divisionEntity")
    private Division division;
    public Division getDivision() {        return division;    }
    public void setDivision(Division division) {        this.division = division;    }
    @SerializedName("technogyEntity")
    private Technology technogy ;
    public Technology getTechnogy() {        return technogy; }
    public void setTechnogy(Technology technogy) {        this.technogy = technogy;    }

    public Incident(int typeincident) {}
    public static final Creator<Incident> CREATOR = new Creator<Incident>() {
        @Override
        public Incident createFromParcel(Parcel in) {            return new Incident(in);        }
        @Override
        public Incident[] newArray(int size) {            return new Incident[size];        }
    };
    public static Parcelable.Creator<Incident> getCREATOR() {        return CREATOR;    }

    private Incident(Parcel in) {
        typeincident = in.readInt();
        n_incident = in.readLong();
        idcity = in.readInt();
        service = in.readLong();
        declared = in.readString();
        controlterm = in.readString();
        controltermsla = in.readString();
        controltermtask = in.readString();
        createtime = Timestamp.valueOf(in.readString());
        clazz = in.readString();
        repet = in.readInt();
        yield = in.readInt();
        decisiontime = Timestamp.valueOf(in.readString());
        nameclient = in.readString();
        labelofservice = in.readInt();
        address = in.readString();
        room = in.readString();
        phone = in.readLong();
        techdata = in.readString();
        comment = in.readString();
        timeclose = Timestamp.valueOf(in.readString());
        worker = in.readParcelable(Workers.class.getClassLoader());
        division = in.readParcelable(Division.class.getClassLoader());
        technogy = in.readParcelable(Technology.class.getClassLoader());
    }
    @Override
    public int describeContents() {        return 0;    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(typeincident);
        dest.writeLong(n_incident);
        dest.writeInt(idcity);
        dest.writeLong(service);
        dest.writeString(declared);
        dest.writeString(controlterm);
        dest.writeString(controltermsla);
        dest.writeString(controltermtask);
        dest.writeString(clazz);
        dest.writeInt(repet);
        dest.writeInt(yield);
        dest.writeString(nameclient);
        dest.writeInt(labelofservice);
        dest.writeString(address);
        dest.writeString(room);
        dest.writeLong(phone);
        dest.writeString(techdata);
        dest.writeString(comment);
        dest.writeParcelable(worker, flags);
        dest.writeParcelable(division, flags);
        dest.writeParcelable(technogy, flags);
    }
}
