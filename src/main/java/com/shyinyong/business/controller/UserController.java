package com.shyinyong.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shyinyong.business.service.Producer;

import com.shyinyong.business.payload.*;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    Producer sender;

    @GetMapping("/me")
    public UserSummary getCurrentUser() {
        return new UserSummary(1000L, "shyinyong", "殷勇");
    }

    @GetMapping(path = "/info")
    @ResponseBody
    public String info(@RequestParam String id) {
        // enter user center
        String message = "访问个人中心:";

        sender.send(message + id);

        return "ID: " + id;
        // This returns a JSON or XML with the users
        //return userRepository.findAll();
    }

//    @GetMapping(path="/all")
//    public @ResponseBody Iterable<User> getAllUsers() {
//        // This returns a JSON or XML with the users
//        return userRepository.findAll();
//    }
}
