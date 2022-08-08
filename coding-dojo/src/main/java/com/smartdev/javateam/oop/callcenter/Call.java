package com.smartdev.javateam.oop.callcenter;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Call call = (Call) o;
        return getCallId() == call.getCallId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCallId());
    }
}
