package com.supwisdom.datashow.basedata.domain;

import java.io.Serializable;
import java.util.Objects;

public class Appoinment implements Serializable {
    private String ids         ;
    private String custname    ;
    private String mobile      ;
    private String idno        ;
    private String idtype      ;
    private String sdate       ;
    private String linkman     ;
    private String deptname    ;
    private String reason      ;
    private String approve     ;
    private String approvetime ;
    private String suggest     ;
    private String opercode    ;
    private String updatetime  ;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public String getApprovetime() {
        return approvetime;
    }

    public void setApprovetime(String approvetime) {
        this.approvetime = approvetime;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getOpercode() {
        return opercode;
    }

    public void setOpercode(String opercode) {
        this.opercode = opercode;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "Appoinment{" +
                "ids='" + ids + '\'' +
                ", custname='" + custname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", idno='" + idno + '\'' +
                ", idtype='" + idtype + '\'' +
                ", sdate='" + sdate + '\'' +
                ", linkman='" + linkman + '\'' +
                ", deptname='" + deptname + '\'' +
                ", reason='" + reason + '\'' +
                ", approve='" + approve + '\'' +
                ", approvetime='" + approvetime + '\'' +
                ", suggest='" + suggest + '\'' +
                ", opercode='" + opercode + '\'' +
                ", updatetime='" + updatetime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appoinment that = (Appoinment) o;
        return Objects.equals(ids, that.ids) &&
                Objects.equals(custname, that.custname) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(idno, that.idno) &&
                Objects.equals(idtype, that.idtype) &&
                Objects.equals(sdate, that.sdate) &&
                Objects.equals(linkman, that.linkman) &&
                Objects.equals(deptname, that.deptname) &&
                Objects.equals(reason, that.reason) &&
                Objects.equals(approve, that.approve) &&
                Objects.equals(approvetime, that.approvetime) &&
                Objects.equals(suggest, that.suggest) &&
                Objects.equals(opercode, that.opercode) &&
                Objects.equals(updatetime, that.updatetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ids, custname, mobile, idno, idtype, sdate, linkman, deptname, reason, approve, approvetime, suggest, opercode, updatetime);
    }
}
