package com.supwisdom.datashow.basedata.domain;

import java.util.List;

public class BDQParam {
    private String accdate;
    private String acctime;
    private String bdate;
    private String edate;
    private String str1;
    private String str2;
    private int tmp1;
    private int tmp2;
    private int tmp3;
    private float ltmp1;
    private float ltmp2;
    private float ltmp3;
    private List<?> ids;

    public String getAcctime() {
        return acctime;
    }

    public void setAcctime(String acctime) {
        this.acctime = acctime;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public List getIds() {
        return ids;
    }

    public String getAccdate() {
        return accdate;
    }

    public void setAccdate(String accdate) {
        this.accdate = accdate;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public int getTmp1() {
        return tmp1;
    }

    public void setTmp1(int tmp1) {
        this.tmp1 = tmp1;
    }

    public int getTmp2() {
        return tmp2;
    }

    public void setTmp2(int tmp2) {
        this.tmp2 = tmp2;
    }

    public int getTmp3() {
        return tmp3;
    }

    public void setTmp3(int tmp3) {
        this.tmp3 = tmp3;
    }

    public float getLtmp1() {
        return ltmp1;
    }

    public void setLtmp1(float ltmp1) {
        this.ltmp1 = ltmp1;
    }

    public float getLtmp2() {
        return ltmp2;
    }

    public void setLtmp2(float ltmp2) {
        this.ltmp2 = ltmp2;
    }

    public float getLtmp3() {
        return ltmp3;
    }

    public void setLtmp3(float ltmp3) {
        this.ltmp3 = ltmp3;
    }

    public void setIds(List<?> ids) {
        this.ids = ids;
    }
}
