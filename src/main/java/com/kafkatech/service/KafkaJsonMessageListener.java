package com.kafkatech.service;

import com.kafkatech.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaJsonMessageListener {

    private static final Logger log = LoggerFactory.getLogger(KafkaJsonMessageListener.class);

    @KafkaListener(id = "json-receiver-1", topics = "${spring.kafka.topics[1].name}", groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "KafkaListenerContainerFactoryForJsonMessage")
    public void consumeJsonMessageFromTopic(@Payload CustomerDTO customerDTO,
                                            @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) Integer key,
                                            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                            @Header(KafkaHeaders.OFFSET) String offset,
                                            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timeStamp
                                            ) {
        log.info("Consumer consume the json message - {}" +
                " || Key - [{}]" +
                " || Received Fom Topic - [{}]" +
                " || Partition - [{}]" +
                " || offset - [{}]" +
                " || Timestamp = [{}]", customerDTO.toString(), key, topic, partition, offset, timeStamp);
    }
}
