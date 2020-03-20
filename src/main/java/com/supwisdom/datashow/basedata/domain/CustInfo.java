package com.supwisdom.datashow.basedata.domain;

public class CustInfo {
    private String ids;
    private String stuempno;
    private String custname;
    private String deviceid;
    private String opercode;
    private String importtime;
    private String updatetime;
    private String expiredate;
    private String syncflag;
    private String synctime;
    private Integer verno;
    private String adddelflag;
    private String begindate;
    private String devicename;

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
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

    public String getOpercode() {
        return opercode;
    }

    public void setOpercode(String opercode) {
        this.opercode = opercode;
    }

    public String getImporttime() {
        return importtime;
    }

    public void setImporttime(String importtime) {
        this.importtime = importtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }

    public String getSyncflag() {
        return syncflag;
    }

    public void setSyncflag(String syncflag) {
        this.syncflag = syncflag;
    }

    public String getSynctime() {
        return synctime;
    }

    public void setSynctime(String synctime) {
        this.synctime = synctime;
    }

    public Integer getVerno() {
        return verno;
    }

    public void setVerno(Integer verno) {
        this.verno = verno;
    }

    public String getAdddelflag() {
        return adddelflag;
    }

    public void setAdddelflag(String adddelflag) {
        this.adddelflag = adddelflag;
    }

    public String getBegindate() {
        return begindate;
    }

    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "ids='" + ids + '\'' +
                ", stuempno='" + stuempno + '\'' +
                ", custname='" + custname + '\'' +
                ", deviceid=" + deviceid +
                ", opercode='" + opercode + '\'' +
                ", importtime='" + importtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", expiredate='" + expiredate + '\'' +
                ", syncflag=" + syncflag +
                ", synctime='" + synctime + '\'' +
                ", verno=" + verno +
                ", adddelflag=" + adddelflag +
                ", begindate='" + begindate + '\'' +
                '}';
    }
}
