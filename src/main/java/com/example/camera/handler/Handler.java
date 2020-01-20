package com.example.camera.handler;

import org.springframework.stereotype.Component;

@Component
public class Handler implements DaHuaHandler {
    @Override
    public void handler(String eventType, String plateNumber, String filepath) {
        System.out.println(eventType+plateNumber+filepath);
    }
}
