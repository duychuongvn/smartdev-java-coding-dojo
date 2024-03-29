package com.smartdev.javateam.oop.callcenter;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

public class ForwardDirectorCallManagerTest {
    final int maxCalls = 30;
    final CountDownLatch latch = new CountDownLatch(maxCalls);

    /**
     * Suppose:
     * Respondents only answer the odd calls
     * Manager only answer the  calls that have id is not divided for 4
     * Director only answer the  calls that have id divided for 4
     */

    @Test
    public void respondentShouldReceiveTheCall() throws InterruptedException {

        CallManager callManager = new ForwardDirectorCallManager(latch);
        ExecutorService executors = Executors.newCachedThreadPool();
        for (int i = 0; i < maxCalls; i++) {
            int callId = i + 1;
            executors.submit(call(callId, 1, "Customer: " + callId, callManager))
            ;
        }

        executors.shutdown();

        latch.await();
        Set<Call> respondentCalls = CallRecorder.getCalls(Employee.LEVEL_RESPONDENT);
        Assert.assertTrue("Respondents received all odd calls", respondentCalls.stream().allMatch(x -> x.getCallId() % 2 == 1));
        Set<Call> managerCalls = CallRecorder.getCalls(Employee.LEVEL_MANAGER);
        Assert.assertTrue("Managers received even calls but not divided for 4", managerCalls.stream().allMatch(x -> x.getCallId() % 2 == 0 && x.getCallId() % 4 != 0));

        Set<Call> directorCalls = CallRecorder.getCalls(Employee.LEVEL_DIRECTOR);
        Assert.assertTrue("Directors received the calls divided for 4", directorCalls.stream().allMatch(x -> x.getCallId() % 4 == 0));

        Assert.assertEquals(15, respondentCalls.size());
        Assert.assertEquals(8, managerCalls.size());
        Assert.assertEquals(7, directorCalls.size());
    }

    private Runnable call(int callId, int level, String customerName, CallManager callManager) {
        return () -> {

            Call call = new Call(callId);
            call.setLevel(level);
            call.setCustomerName(customerName);
            callManager.dispatchCall(call);
        };
    }
}