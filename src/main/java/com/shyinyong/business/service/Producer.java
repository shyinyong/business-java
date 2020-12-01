package com.shyinyong.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message) {
        String kafkaTopic = "woohoo-event";

        kafkaTemplate.send(kafkaTopic, message);
    }
}
