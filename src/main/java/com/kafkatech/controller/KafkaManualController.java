package com.kafkatech.controller;

import com.kafkatech.service.KafkaManualPoll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer-app")
public class KafkaManualController {

    private static final Logger log = LoggerFactory.getLogger(KafkaManualController.class);

    @Autowired
    private KafkaManualPoll kafkaManualPoll;

    @GetMapping("/consume")
    public ResponseEntity<?> publishMessage() {
        try {
            kafkaManualPoll.consumeMessageManuallyFromTopic();
            return ResponseEntity.ok("message consume successfully ..");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
