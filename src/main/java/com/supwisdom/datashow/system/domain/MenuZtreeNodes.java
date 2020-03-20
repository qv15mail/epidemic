package com.supwisdom.datashow.system.domain;

public class MenuZtreeNodes {
    private Integer id;
    private Integer pId;
    private String  name;
    private boolean isParent;
    private boolean open;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "MenuZtreeNodes{" +
                "id=" + id +
                ", pId=" + pId +
                ", name='" + name + '\'' +
                ", isParent=" + isParent +
                ", open=" + open +
                '}';
    }
}
