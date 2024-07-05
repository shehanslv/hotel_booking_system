package com.shehan.hotel_booking_system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Main {

    @RequestMapping("/test")
    public @ResponseBody Map<String, String> testRun(){

        Map<String, String> map = new HashMap<>();
        map.put("message","success");
        return map;
    }

}
