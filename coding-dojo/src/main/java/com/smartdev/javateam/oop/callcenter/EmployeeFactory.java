package com.smartdev.javateam.oop.callcenter;

public class EmployeeFactory {

    public static Employee createInstance(int level, String name, CallManager callManager) {

        if (Employee.LEVEL_DIRECTOR == level) {
            return new Director(name, callManager);
        }
        if (Employee.LEVEL_MANAGER == level) {
            return new Manager(name, callManager);
        }
        return new Respondent(name, callManager);
    }
}
