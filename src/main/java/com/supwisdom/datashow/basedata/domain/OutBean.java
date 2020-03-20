package com.supwisdom.datashow.basedata.domain;

public class OutBean {
    private String accdate;
    private String ext;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private int cnt;
    private int cnt1;
    private int cnt2;
    private int cnt3;
    private int cnt4;
    private int cnt5;
    private double amt;
    private double amt2;

    public double getAmt2() {
        return amt2;
    }

    public void setAmt2(double amt2) {
        this.amt2 = amt2;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getAccdate() {
        return accdate;
    }

    public void setAccdate(String accdate) {
        this.accdate = accdate;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getCnt2() {
        return cnt2;
    }

    public void setCnt2(int cnt2) {
        this.cnt2 = cnt2;
    }

    public int getCnt3() {
        return cnt3;
    }

    public void setCnt3(int cnt3) {
        this.cnt3 = cnt3;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public int getCnt4() {
        return cnt4;
    }

    public void setCnt4(int cnt4) {
        this.cnt4 = cnt4;
    }

    public int getCnt5() {
        return cnt5;
    }

    public void setCnt5(int cnt5) {
        this.cnt5 = cnt5;
    }

    public int getCnt1() {
        return cnt1;
    }

    public void setCnt1(int cnt1) {
        this.cnt1 = cnt1;
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4;
    }

    public String getExt5() {
        return ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5;
    }

    @Override
    public String toString() {
        return "OutBean{" +
                "accdate='" + accdate + '\'' +
                ", ext='" + ext + '\'' +
                ", cnt=" + cnt +
                ", amt=" + amt +
                '}';
    }
}
