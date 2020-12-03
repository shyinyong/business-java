package com.shyinyong.business.controller;

import com.shyinyong.business.service.Producer;
import com.shyinyong.business.payload.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.*;
import java.util.Date;

import java.util.Map;
import java.util.HashMap;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

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
        Map<String, Object> gameEventData = new HashMap<>();
        List fields = new ArrayList();

        Map<String, Object> appId = new HashMap<>();
        appId.put("type", "int64");
        appId.put("optional", false);
        appId.put("field", "app_id");
        fields.add(appId);

        Map<String, Object> schema = new HashMap<>();
        schema.put("type", "struct");
        schema.put("optional", false);
        schema.put("name", "ksql.users");
        schema.put("fields", fields);
        // Add schema
        gameEventData.put("schema", schema);

        Map<String, Object> payload = new HashMap<>();
        payload.put("app_id", 1);
        payload.put("user_id", 110);
        payload.put("event", event);
        payload.put("level", UserController.level);
        payload.put("ip", "140.206.189.2");

        //gameEventData.put("payload", payload);
        String jsonMessage = new Gson().toJson(payload);
        sender.send(jsonMessage, "game-event-player");

        return jsonMessage;
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public String getAllUsers() {
        return "findAll";
    }
}
