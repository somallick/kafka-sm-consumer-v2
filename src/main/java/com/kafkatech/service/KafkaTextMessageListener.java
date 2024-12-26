package com.kafkatech.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaTextMessageListener {

    private static final Logger log = LoggerFactory.getLogger(KafkaTextMessageListener.class);

    @KafkaListener(id = "text-receiver-1", topics = "${spring.kafka.topics[0].name}", groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "KafkaListenerContainerFactoryForTextMessage")
    public void consumeMessageFromTopic(@Payload String message,
                                        @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) Integer key,
                                        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                        @Header(KafkaHeaders.OFFSET) String offset,
                                        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timeStamp
                                        ) {
        log.info("Consumer consume the text message - {}" +
                " || Key - [{}]" +
                " || Received Fom Topic - [{}]" +
                " || Partition - [{}]" +
                " || offset - [{}]" +
                " || Timestamp = [{}]", message, key, topic, partition, offset, timeStamp);
    }

//    @KafkaListener(id = "receiver-2", groupId = "sm-group-1", topicPartitions = {
//            @TopicPartition(topic = "${kafka-topic.name}",
//                    partitionOffsets = @PartitionOffset(partition ="0-2", initialOffset = "0"))})
//    public void consumeMessageFromBeginnigOfTopic(String message) {
//        log.info("Consumer consume the message2 - {}", message);
//    }
}
