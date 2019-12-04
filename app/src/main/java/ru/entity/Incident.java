package ru.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.sql.Date;
import java.text.SimpleDateFormat;
import ru.adapter.MyAdapterIncident;

public class Incident implements Parcelable {
    @SerializedName("typeincident") //Тип инцидента
    private int typeincident;
    public String getTypeincident() {        return typeincident == 0 ? "Л-":"ШПД-";    }
    public void setTypeincident(int typeincident) {        this.typeincident = typeincident;    }
    @SerializedName("n_incident")    //№ инцидента
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
    @SerializedName("controlterm")  // Контрольное время
    private long controlterm;
    public long getControlterm() {        return controlterm;    }
    public void setControlterm(long controlterm) {        this.controlterm = controlterm;    }
    @SerializedName("controltermsla")  // Контрольное время Sla
    private String controltermsla;
    public String getControltermsla() {        return controltermsla; }
    public void setControltermsla(String controltermsla) {        this.controltermsla = controltermsla; }
    @SerializedName("controltermtask")  // Контрольное время Задачи
    private String controltermtask;
    public String getControltermtask() {        return controltermtask;    }
    public void setControltermtask(String controltermtask) {        this.controltermtask = controltermtask;    }
    @SerializedName( "createtime")    // Время создания наряда
    private long createtime;
    public long getCreatetime() {        return createtime;    }
    public void setCreatetime(long createtime) {        this.createtime = createtime;    }
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
    @SerializedName("decisiontime") // Время Визита
    private long decisiontime;
    public long getDecisiontime() {        return decisiontime;    }
    public void setDecisiontime(long decisiontime) {        this.decisiontime = decisiontime;    }
    @SerializedName("nameclient") // Клиент
    private String nameclient;
    public String getNameclient() {        return nameclient;    }
    public void setNameclient(String nameclient) {        this.nameclient = nameclient;    }
    @SerializedName("labelofservice") // Уровень обслуживания
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
    @SerializedName( "techdata") // Тех. данные
    private String techdata;
    public String getTechdata() {        return techdata;    }
    public void setTechdata(String techdata) {        this.techdata = techdata;     }
    @SerializedName("comment")   // Комментарии
    private String comment;
    public String getComment() {        return comment;    }
    public void setComment(String comment) {        this.comment = comment;    }
    @SerializedName( "timeclose")    // Время закрытия
    private long timeclose;
    public long getTimeclose() {        return timeclose;    }
    public void setTimeclose(long timeclose) {        this.timeclose = timeclose;    }
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
        controlterm = in.readLong();
        controltermsla = in.readString();
        controltermtask = in.readString();
        createtime = in.readLong();
        clazz = in.readString();
        repet = in.readInt();
        yield = in.readInt();
        decisiontime = in.readLong();
        nameclient = in.readString();
        labelofservice = in.readInt();
        address = in.readString();
        room = in.readString();
        phone = in.readLong();
        techdata = in.readString();
        comment = in.readString();
        timeclose = in.readLong();
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
        dest.writeLong(controlterm);
        dest.writeString(controltermsla);
        dest.writeString(controltermtask);
        dest.writeString(clazz);
        dest.writeInt(repet);
        dest.writeInt(yield);
        dest.writeLong(decisiontime);
        dest.writeString(nameclient);
        dest.writeInt(labelofservice);
        dest.writeString(address);
        dest.writeString(room);
        dest.writeLong(phone);
        dest.writeString(techdata);
        dest.writeString(comment);
        dest.writeLong(createtime);
        dest.writeLong(timeclose);
        dest.writeParcelable(worker, flags);
        dest.writeParcelable(division, flags);
        dest.writeParcelable(technogy, flags);
    }

    String color= "<font COLOR='#33B5E5'><b>";
    @Override
    public String toString() {
        String term = null;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm dd.MM.yy");
        if(controlterm != 0) {
            int hours = (int)((getControlterm() - MyAdapterIncident.time.getTime()) / 3600000L);
            int min = (int) (((getControlterm() - MyAdapterIncident.time.getTime()) - (long) (hours) * 3600000L) / 60000);
            if(hours > 0 && min > 0 )
                term = "<font COLOR='#FF8C00'><b>" +Math.abs(hours) + " ч. " + Math.abs(min) + " мин.</b></font>";
            else
                term = "<font COLOR='#FF0000'><b>" +Math.abs(hours) + " ч. " + Math.abs(min) + " мин.</b></font>";
            if( ((controlterm + 7200000) - decisiontime) > 0 )
                term = "<font COLOR='#339900'><b>" + format.format(new Date( controlterm + 7200000L)) + "</b></font>";
        }
        return  "<font COLOR='#33B5E5'><b>Логин: </b></font>" + service + "<br>" +
                "<font COLOR='#33B5E5'><b>Заявлено: </b></font>" + declared  + "<br>" +
                "<font COLOR='#33B5E5'><b>КС: </b></font>" + term + "<br>" +
                "<font COLOR='#33B5E5'><b>Назначен: </b></font>" + worker.getName() +
                "<font COLOR='#33B5E5'><b>КС (sla): </b></font>" + controltermsla + "<br>" +
                "<font COLOR='#33B5E5'><b>КС задачи: </b></font>" + controltermtask + "<br>" +
                "<font COLOR='#33B5E5'><b>Кл.: </b></font>" + clazz + "<br>" +
                "<font COLOR='#33B5E5'><b>Интервал c: </b></font>" + format.format(new Date(decisiontime)) + "<br>" +
                "<font COLOR='#33B5E5'><b>Уровень облуживания: </b></font>" + labelofservice + "<br>" +
                "<font COLOR='#33B5E5'><b>Адрес: </b></font>" + address + " " + room + "<br>" +
                "<font COLOR='#33B5E5'><b>Телефон: </b></font>+7" + phone + "<br>" +
                "<font COLOR='#33B5E5'><b>Тех.данные: </b></font>" + techdata + "<br>" +
                "<font COLOR='#33B5E5'><b>Комментарии: </b></font>" + comment + "<br>" +
                "<font COLOR='#33B5E5'><b>Повтор: </b></font>" + repet + "<br>" +
                "<font COLOR='#33B5E5'><b>Имя: </b></font>" + nameclient + "<br>" +
                "<font COLOR='#33B5E5'><b>Доходность: </b></font>" + yield + "<br>" ;
    }
}
