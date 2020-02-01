package com.epoint.mealorder.bizlogic.mealinfo.domain;

import java.util.Date;

public class Mealinfo
{
    /**
     * 菜单对象
     */
    private String cpId;
    private String cpName;
    private int cpType;
    private double money;
    private int canPack;
    private int count;
    private Date addTime;
    private String note;
    

  

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public int getCpType() {
        return cpType;
    }

    public void setCpType(int cpType) {
        this.cpType = cpType;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getCanPack() {
        return canPack;
    }

    public void setCanPack(int canPack) {
        this.canPack = canPack;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "Mealinfo [cpId=" + cpId + ", cpName=" + cpName + ", cpType=" + cpType + ", money=" + money + ", canPack=" + canPack + ", count=" + count + ", addTime=" + addTime + ", note=" + note + "]";
    }

    public Mealinfo(String cpId, String cpName, int cpType, double money, int canPack, int count, Date addTime, String note) {
        super();
        this.cpId = cpId;
        this.cpName = cpName;
        this.cpType = cpType;
        this.money = money;
        this.canPack = canPack;
        this.count = count;
        this.addTime = addTime;
        this.note = note;
    }
    
    
    

}
