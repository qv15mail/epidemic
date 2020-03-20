package com.supwisdom.datashow.basedata.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

public class CustBean implements Serializable {
    @Excel(name = "学工号")
    private String stuempno;
    @Excel(name = "姓名")
    private String custname;
    @Excel(name = "有效期")
    private String expiredate;
    @Excel(name = "开始日期")
    private String begindate;

    public String getBegindate() {
        return begindate;
    }

    public void setBegindate(String begindate) {
        this.begindate = begindate;
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

    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }
}
