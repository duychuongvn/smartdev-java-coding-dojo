package com.smartdev.javateam.oop.callcenter;

public class Respondent extends Employee {

    protected Respondent(String name, CallManager callManager) {
        super(name, LEVEL_RESPONDENT, callManager);
    }

    protected boolean canNotAnswer(Call call) {
        // cannot answer call id is even
        return call.getCallId() % 2 == 0;
    }
}
