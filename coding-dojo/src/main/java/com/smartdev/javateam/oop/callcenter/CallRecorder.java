package com.smartdev.javateam.oop.callcenter;

import java.util.*;

public class CallRecorder {

    private static final Map<Integer, Set<Call>> callRecords = Collections.synchronizedMap(new HashMap<>());
//    private static final Object locker = new Object();
    public static void record(Call call, Employee employee) {
        callRecords.computeIfAbsent(employee.getLevel(),  v-> Collections.synchronizedSet(new HashSet<>())).add(call);
//        if(!callRecords.containsKey(employee.getLevel())) {
//            synchronized (locker) {
//                if(!callRecords.containsKey(employee.getLevel())) {
//                    callRecords.put(employee.getLevel(),  Collections.synchronizedSet(new HashSet<>()));
//                }
//            }
//        }
//        Set<Call> calls = callRecords.get(employee.getLevel());
//        calls.add(call);
    }

    public static Set<Call> getCalls(int level) {
        return callRecords.get(level);
    }
}
