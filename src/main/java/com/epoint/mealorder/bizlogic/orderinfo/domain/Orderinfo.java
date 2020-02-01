package com.epoint.mealorder.bizlogic.orderinfo.domain;

import java.util.Date;

public class Orderinfo
{

    private String orderId;
    private String cpId;
    private String orderName;
    private Date orderTime;
    private String phone;
    private int count;
    private String address;
    private double price;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Date getOrdertime() {
        return orderTime;
    }

    public void setOrdertime(Date ordertime) {
        this.orderTime = ordertime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Orderinfo(String orderId, String cpId, String orderName, Date ordertime, String phone, int count, String address, double price) {
        super();
        this.orderId = orderId;
        this.cpId = cpId;
        this.orderName = orderName;
        this.orderTime = ordertime;
        this.phone = phone;
        this.count = count;
        this.address = address;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Orderinfo [orderId=" + orderId + ", cpId=" + cpId + ", orderName=" + orderName + ", ordertime=" + orderTime + ", phone=" + phone + ", count=" + count + ", address=" + address + ", price=" + price + "]";
    }

}
