package com.supwisdom.datashow.basedata.domain;

public class RecordInfo {
    private String stuempno;
    private String custname;
    private String deviceid;
    private String devicename;
    private String ivtypename;
    private String transdate;
    private String transtime;
    private String inout;
    private String temperature;
    private String status;

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getStuempno() {
        return stuempno;
    }

    public void setStuempno(String stuempno) {
        this.stuempno = stuempno;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getIvtypename() {
        return ivtypename;
    }

    public void setIvtypename(String ivtypename) {
        this.ivtypename = ivtypename;
    }

    public String getTransdate() {
        return transdate;
    }

    public void setTransdate(String transdate) {
        this.transdate = transdate;
    }

    public String getTranstime() {
        return transtime;
    }

    public void setTranstime(String transtime) {
        this.transtime = transtime;
    }

    public String getInout() {
        return inout;
    }

    public void setInout(String inout) {
        this.inout = inout;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RecordInfo{" +
                "stuempno='" + stuempno + '\'' +
                ", custname='" + custname + '\'' +
                ", deviceid='" + deviceid + '\'' +
                ", devicename='" + devicename + '\'' +
                ", ivtypename='" + ivtypename + '\'' +
                ", transdate='" + transdate + '\'' +
                ", transtime='" + transtime + '\'' +
                ", inout='" + inout + '\'' +
                ", temperature='" + temperature + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
