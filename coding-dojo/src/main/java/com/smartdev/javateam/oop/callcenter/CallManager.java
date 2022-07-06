package com.smartdev.javateam.oop.callcenter;

import java.util.*;

/**
 * Call Center:
 * Imagine you have a call center with three levels of employees: respondent, manager, and director.
 * An incoming telephone call must be first allocated to a respondent who is free.
 * If the respondent can't handle the call, he or she must escalate the call to a manager.
 * If the manager is not free or not able to handle it,
 * then the call should be escalated to a director.
 * Design the classes and data structures for this problem.
 * Implement a method dispatchCall() which assigns a call to the first available employee.
 */
public class CallManager {

    private static final int NUMBER_OF_RESPONDENTS= 10;
    private static final int NUMBER_OF_MANAGERS= 5;
    private static final int NUMBER_OF_DIRECTORS= 2;
    private final Map<Integer, List<Employee>> allEmployees = new HashMap<>();
    private final Map<Integer, Queue<Call>> callQueues = new HashMap<>();

    public CallManager() {
        initEmployees(NUMBER_OF_RESPONDENTS, Employee.LEVEL_RESPONDENT, "Respondent");
        initEmployees(NUMBER_OF_MANAGERS, Employee.LEVEL_MANAGER, "Manager");
        initEmployees(NUMBER_OF_DIRECTORS, Employee.LEVEL_DIRECTOR, "Director");
    }


    private void initEmployees(int numberOfEmployees, int level, String name) {

        List<Employee> employees = new ArrayList<>(numberOfEmployees);
        for (int i = 0; i < numberOfEmployees; i++) {
            employees.add(EmployeeFactory.createInstance(level, name +": " + (i + 1), this));
        }
        allEmployees.put(level, employees);
    }

    public void callEnded(Call call) {

        Queue<Call> callQueue = getCallQueue(call);
        if (!callQueue.isEmpty()) {
            Call nexCall = callQueue.peek();
            int callLevel = nexCall.getLevel();
            Optional<Employee> freeEmployee = getFreeEmployee(callLevel);
            if (freeEmployee.isPresent()) {
                callQueue.remove();
                freeEmployee.get().receiveCall(nexCall);
            }
        }
    }

    public void dispatchCall(Call call) {
        Optional<Employee> freeEmployee = getFreeEmployee(call.getLevel());
        if(freeEmployee.isPresent()) {
            Employee employee = freeEmployee.get();
            call.setLevel(employee.getLevel());
            employee.receiveCall(call);
        } else {
            getCallQueue(call).add(call);
        }
    }

    private Queue<Call> getCallQueue(Call call) {
        return callQueues.computeIfAbsent(call.getLevel(), v -> new ArrayDeque<>());
    }

    private Optional<Employee> getFreeEmployee(int level) {
        return this.allEmployees.get(level).stream().filter(Employee::isFree).findAny();
    }
}
