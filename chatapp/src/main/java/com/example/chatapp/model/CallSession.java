package com.example.chatapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CallSession {
    private String caller;
    private String receiver;
    private long startTime;  // 🔥 ADD THIS FIELD
}
