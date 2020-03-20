package com.supwisdom.datashow.basedata.domain;

import java.io.Serializable;

public class DeviceInfo implements Serializable {
    private Integer deviceid;
    private String devicename;
    private String devphyid;

    public Integer getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Integer deviceid) {
        this.deviceid = deviceid;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getDevphyid() {
        return devphyid;
    }

    public void setDevphyid(String devphyid) {
        this.devphyid = devphyid;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "deviceid=" + deviceid +
                ", devicename='" + devicename + '\'' +
                ", devphyid='" + devphyid + '\'' +
                '}';
    }
}
