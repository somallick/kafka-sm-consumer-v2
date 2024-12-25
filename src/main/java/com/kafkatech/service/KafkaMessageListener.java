package com.kafkatech.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    private static final Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

//    @Value("${kafka-topic.name}")
//    private String topicName;

//    @KafkaListener(id = "receiver-1", topics = "${kafka-topic.name}", groupId = "sm-group-1")
//    public void consumeMessageFromTopic(String message) {
//        log.info("Consumer consume the message - {}", message);
//    }

//    @KafkaListener(id = "receiver-2", groupId = "sm-group-1", topicPartitions = {
//            @TopicPartition(topic = "${kafka-topic.name}",
//                    partitionOffsets = @PartitionOffset(partition ="0-2", initialOffset = "0"))})
//    public void consumeMessageFromBeginnigOfTopic(String message) {
//        log.info("Consumer consume the message2 - {}", message);
//    }
}
