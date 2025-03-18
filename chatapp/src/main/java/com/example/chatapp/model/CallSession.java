package com.example.chatapp.model;

public class CallSession {
    private final String caller;
    private final String receiver;

    public CallSession(String caller, String receiver) {
        this.caller = caller;
        this.receiver = receiver;
    }

    public String getCaller() { return caller; }
    public String getReceiver() { return receiver; }
}
