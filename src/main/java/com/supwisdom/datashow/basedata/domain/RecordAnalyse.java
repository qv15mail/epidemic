package com.supwisdom.datashow.basedata.domain;

public class RecordAnalyse {
    private Integer deviceid;
    private String devicename;
    private Integer totcnt;
    private Integer qrcode;
    private Integer card;
    private Integer inppt;

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

    public Integer getTotcnt() {
        return totcnt;
    }

    public void setTotcnt(Integer totcnt) {
        this.totcnt = totcnt;
    }

    public Integer getQrcode() {
        return qrcode;
    }

    public void setQrcode(Integer qrcode) {
        this.qrcode = qrcode;
    }

    public Integer getCard() {
        return card;
    }

    public void setCard(Integer card) {
        this.card = card;
    }

    public Integer getInppt() {
        return inppt;
    }

    public void setInppt(Integer inppt) {
        this.inppt = inppt;
    }
}
