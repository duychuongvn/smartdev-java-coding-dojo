package com.smartdev.javateam.oop.callcenter;

public class Employee {

    protected static final int LEVEL_RESPONDENT = 1;
    protected static final int LEVEL_MANAGER = 2;
    protected static final int LEVEL_DIRECTOR = 3;
    private final String name;
    private final int level;
    private boolean isFree = true;
    protected CallManager callManager;

    protected Employee(String name, int level, CallManager callManager) {
        this.level = level;
        this.name = name;
        this.callManager = callManager;
    }

    public void receiveCall(Call call) {

        if(canNotAnswer(call)) {
            escalateCall(call);
        } else {
            answerCall(call);
            endCall(call);
        }
    }

    private void answerCall(Call call) {
        this.isFree = false;
        System.out.println("Call received by employee "+ this.name + " for customer " + call.getCustomerName());
        CallRecorder.record(call, this);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Do nothing
        }
    }

    protected boolean canNotAnswer(Call call) {
        return false;
    }

    public void endCall(Call call) {
        System.out.println("Call ended by employee "+ this.name + " for customer " + call.getCustomerName());
        this.isFree = true;
        this.callManager.callEnded(call);
    }


   public void escalateCall(Call call) {
       this.setFree();
       call.setLevel(call.getLevel() + 1);
       this.callManager.dispatchCall(call);
   }

    public boolean isFree() {
        return isFree;
    }

    protected void setFree() {
        isFree = true;
    }

    public int getLevel() {
        return level;
    }

}
