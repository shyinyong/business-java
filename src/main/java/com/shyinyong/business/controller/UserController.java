package com.shyinyong.business.controller;

import com.shyinyong.business.service.Producer;
import com.shyinyong.business.payload.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.*;
import java.util.Date;

import java.util.Map;
import java.util.HashMap;
import java.util.Calendar;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    Producer sender;

    static int level = 1;

    @GetMapping("/me")
    public UserSummary getCurrentUser() {
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        String time = utc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss xxx"));

        return new UserSummary(1000L, "shyinyong", time);
    }

    @GetMapping(path = "/info")
    @ResponseBody
    public String info(@RequestParam String event) {
        // utc time
        UserController.level++;

        Map<String, Object> eventData = new HashMap<>();
        eventData.put("app_id", 1);
        eventData.put("user_id", 110);
        eventData.put("event", event);
        eventData.put("level", UserController.level);
        eventData.put("ip", "140.206.189.2");
        eventData.put("version", "1");
        //eventData.put("time", time);
        String jsonMessage = new Gson().toJson(eventData);

        sender.send(jsonMessage);

        return jsonMessage;
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public String getAllUsers() {
        return "findAll";
    }
}
