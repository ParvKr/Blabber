package com.example.chatapp.service;

import com.example.chatapp.model.CallSession;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SignalingService {
    
    private final ConcurrentHashMap<String, CallSession> activeCalls = new ConcurrentHashMap<>();

    // Start a new call session
    public boolean startCall(String caller, String receiver) {
        if (activeCalls.containsKey(caller) || activeCalls.containsKey(receiver)) {
            return false; // Caller or receiver is already in a call
        }
        activeCalls.put(caller, new CallSession(caller, receiver, System.currentTimeMillis()));
        return true;
    }

    // End a call by removing the caller
    public void endCall(String caller) {
        activeCalls.remove(caller);
    }

    // Get the receiver for a given caller
    public String getReceiver(String caller) {
        CallSession session = activeCalls.get(caller);
        return (session != null) ? session.getReceiver() : null;
    }

    // Get the caller by receiver (reverse lookup)
    public String getCaller(String receiver) {
        for (Map.Entry<String, CallSession> entry : activeCalls.entrySet()) {
            if (entry.getValue().getReceiver().equals(receiver)) {
                return entry.getKey();
            }
        }
        return null;
    }

    // Get call duration in seconds
    public long getCallDuration(String caller) {
        CallSession session = activeCalls.get(caller);
        return (session != null) ? (System.currentTimeMillis() - session.getStartTime()) / 1000 : 0;
    }
}
