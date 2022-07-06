package com.smartdev.javateam.oop.callcenter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class EmployeeFactory {

    public static Employee createInstance(int level, String name, CallManager callManager) {

        if(Employee.LEVEL_DIRECTOR == level) {
            return new Director(name, callManager);
        }
        if(Employee.LEVEL_MANAGER == level) {
            return new Manager(name, callManager);
        }
        return new Respondent(name, callManager);
    }
}
