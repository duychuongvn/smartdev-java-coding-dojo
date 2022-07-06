package com.smartdev.javateam.oop.callcenter;

public class Call {

    private final int callId;
    private String customerName;
    private int level;

    public Call(int callId) {
        this.callId = callId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCallId() {
        return callId;
    }
}
