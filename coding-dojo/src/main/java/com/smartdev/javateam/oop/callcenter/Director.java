package com.smartdev.javateam.oop.callcenter;

public class Director extends Employee {

    protected Director(String name, CallManager callManager) {
        super(name, LEVEL_DIRECTOR, callManager);
    }
}
