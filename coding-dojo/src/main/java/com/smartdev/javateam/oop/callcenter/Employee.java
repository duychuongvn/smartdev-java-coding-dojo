package com.smartdev.javateam.oop.callcenter;

public class Employee {

    protected static final int LEVEL_RESPONDENT = 1;
    protected static final int LEVEL_MANAGER = 2;
    protected static final int LEVEL_DIRECTOR = 3;
    private final String name;
    private final int level;
    protected CallManager callManager;
    private boolean isFree = true;

    protected Employee(String name, int level, CallManager callManager) {
        this.level = level;
        this.name = name;
        this.callManager = callManager;
    }

    public void receiveCall(Call call) {

        if (canNotAnswer(call)) {
            escalateCall(call);
        } else {
            answerCall(call);
            endCall(call);
        }
    }

    private void markBusy() {
        synchronized (this) {
            this.isFree = false;
        }
    }

    private void markFree() {
        synchronized (this) {
            this.isFree = true;
        }
    }
    private void answerCall(Call call) {
        markBusy();
        System.out.println("Call " + call.getCallId() + " received by employee " + this.name + " for customer " + call.getCustomerName());
        callManager.onReceived(call, this);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Do nothing
        }
    }

    protected boolean canNotAnswer(Call call) {
        return false;
    }

    public void endCall(Call call) {
        System.out.println("Call " + call.getCallId() + " ended by employee " + this.name + " for customer " + call.getCustomerName());
        markFree();
        this.callManager.callEnded(call);
    }


    public void escalateCall(Call call) {
        this.setFree();
        call.setLevel(call.getLevel() + 1);
        this.callManager.dispatchCall(call);
    }

    public boolean isFree() {
        synchronized (this){
            if(isFree) {
                isFree = false;
                return true;
            }
            return isFree;
        }

    }

    protected void setFree() {
       markFree();
    }

    public int getLevel() {
        return level;
    }

}
