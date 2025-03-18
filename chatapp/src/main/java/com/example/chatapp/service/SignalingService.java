package com.example.chatapp.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SignalingService {
    private final ConcurrentHashMap<String, String> activeCalls = new ConcurrentHashMap<>();

    public void startCall(String caller, String receiver) {
        activeCalls.put(caller, receiver);
    }

    public void endCall(String caller) {
        activeCalls.remove(caller);
    }

    public String getReceiver(String caller) {
        return activeCalls.get(caller);
    }
}