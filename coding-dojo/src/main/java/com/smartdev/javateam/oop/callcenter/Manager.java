package com.smartdev.javateam.oop.callcenter;


public class Manager extends Employee {

    protected Manager(String name, CallManager callManager) {
        super(name, LEVEL_MANAGER, callManager);
    }

    protected boolean canNotAnswer(Call call) {
        // cannot answer call id is divide for 4
        return call.getCallId() % 4 == 0;
    }
}
