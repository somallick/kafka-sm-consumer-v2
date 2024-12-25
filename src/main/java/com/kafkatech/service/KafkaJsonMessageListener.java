package com.kafkatech.service;

import com.kafkatech.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaJsonMessageListener {

    private static final Logger log = LoggerFactory.getLogger(KafkaJsonMessageListener.class);

    @KafkaListener(topics = "${kafka-topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeJsonMessageFromTopic(CustomerDTO customerDTO) {
        log.info("Consumer consume the json message - {}", customerDTO.toString());
    }
}
