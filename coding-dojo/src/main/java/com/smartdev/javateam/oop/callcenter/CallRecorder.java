package com.smartdev.javateam.oop.callcenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CallRecorder {

    private static final Map<Integer, List<Call>> callRecords = new ConcurrentHashMap<>();

    public static void record(Call call, Employee employee) {
        callRecords.computeIfAbsent(employee.getLevel(), v -> new ArrayList<>()).add(call);
    }

    public static List<Call> getCalls(int level) {
        return callRecords.get(level);
    }
}
