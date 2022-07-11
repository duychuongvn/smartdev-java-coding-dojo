package com.smartdev.javateam.oop.callcenter;

import java.util.concurrent.CountDownLatch;

public class Call {

    private final int callId;
    private String customerName;
    private int level;

    private CountDownLatch countDownLatch;
    public Call(int callId, CountDownLatch countDownLatch) {
        this.callId = callId;
        this.countDownLatch = countDownLatch;
    }

    public void endCall() {
        this.countDownLatch.countDown();
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
