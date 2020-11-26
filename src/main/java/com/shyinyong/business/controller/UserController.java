package com.shyinyong.business.controller;

import com.shyinyong.business.service.Producer;
import com.shyinyong.business.payload.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    Producer sender;

    static int level = 10;

    @GetMapping("/me")
    public UserSummary getCurrentUser() {
        return new UserSummary(1000L, "shyinyong", "殷勇");
    }

    @GetMapping(path = "/info")
    @ResponseBody
    public String info(@RequestParam String event) {

        UserController.level++;

        Map<String, Object> eventData = new HashMap<>();
        eventData.put("app_id", "1");
        eventData.put("user_id", "110");
        eventData.put("event", event);
        eventData.put("level", level);

        String jsonMessage = new Gson().toJson(eventData);

        sender.send(jsonMessage);

        return jsonMessage;
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public String getAllUsers() {
        // This returns a JSON or XML with the users
        // return userRepository.findAll();

        return "findAll";
    }
}
