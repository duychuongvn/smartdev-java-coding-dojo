package com.smartdev.javateam.oop.callcenter;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public class ForwardDirectorCallManager implements CallManager {

    private static final int NUMBER_OF_RESPONDENTS = 10;
    private static final int NUMBER_OF_MANAGERS = 5;
    private static final int NUMBER_OF_DIRECTORS = 2;
    private final Map<Integer, List<Employee>> allEmployees = Collections.synchronizedMap(new HashMap<>());
    private final Map<Integer, Queue<Call>> callQueues = Collections.synchronizedMap(new HashMap<>());
    private CountDownLatch countDownLatch;
    public ForwardDirectorCallManager(CountDownLatch countDownLatch) {
        initEmployees(NUMBER_OF_RESPONDENTS, Employee.LEVEL_RESPONDENT, "Respondent");
        initEmployees(NUMBER_OF_MANAGERS, Employee.LEVEL_MANAGER, "Manager");
        initEmployees(NUMBER_OF_DIRECTORS, Employee.LEVEL_DIRECTOR, "Director");
        this.countDownLatch = countDownLatch;
    }


    private void initEmployees(int numberOfEmployees, int level, String name) {

        List<Employee> employees = new ArrayList<>(numberOfEmployees);
        for (int i = 0; i < numberOfEmployees; i++) {
            employees.add(EmployeeFactory.createInstance(level, name + ": " + (i + 1), this));
        }
        allEmployees.put(level, employees);
    }

    public void callEnded(Call call) {

        countDownLatch.countDown();
        Queue<Call> callQueue = getCallQueue(call);
        if (!callQueue.isEmpty()) {

            Optional<Employee> freeEmployee = getFreeEmployee(call.getLevel());
            if (freeEmployee.isPresent()) {
                Call nexCall = callQueue.poll();
                freeEmployee.get().receiveCall(nexCall);
            }
        }
    }

    @Override
    public void onReceived(Call call, Employee employee) {
        CallRecorder.record(call, employee);
    }

    public void dispatchCall(Call call) {
        Optional<Employee> freeEmployee = getFreeEmployee(call.getLevel());
        if (freeEmployee.isPresent()) {
            Employee employee = freeEmployee.get();
            call.setLevel(employee.getLevel());
            employee.receiveCall(call);
        } else if (call.getLevel() == Employee.LEVEL_MANAGER) {
            call.setLevel(Employee.LEVEL_DIRECTOR);
        }

        getCallQueue(call).add(call);

    }

    private Queue<Call> getCallQueue(Call call) {
        return callQueues.computeIfAbsent(call.getLevel(), v -> new ArrayDeque<>());
    }

    private Optional<Employee> getFreeEmployee(int level) {
        return this.allEmployees.get(level).stream().filter(Employee::isFree).findAny();
    }
}
