package com.example.camera.test;

import com.example.camera.demo.frame.TrafficEventFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TrafficEventFrame trafficEventFrame;

    @RequestMapping("/login")
    public void login() {
        trafficEventFrame.login();
    }

    @RequestMapping("/manualsnap")
    public void manualsnap() {
        trafficEventFrame.manualsnap();
    }

    @RequestMapping("/logout")
    public void logout() {
        trafficEventFrame.logout();
    }
}
