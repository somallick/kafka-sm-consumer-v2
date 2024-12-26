package com.kafkatech.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    /*
     * Configuration For text message consumer
     */
    @Bean
    public Map<String, Object> consumerConfigForTextMessage() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }
    @Bean
    public ConsumerFactory<String,Object> consumerFactoryForTextMessage(){
        return new DefaultKafkaConsumerFactory<>(consumerConfigForTextMessage());
    }
    @Bean(name = "KafkaListenerContainerFactoryForTextMessage")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactoryForTextMessage() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryForTextMessage());
        return factory;
    }

    /*
     * Configuration For json message consumer
     */
    @Bean
    public Map<String, Object> consumerConfigForJsonMessage() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.kafkatech.dto");
        return props;
    }
    @Bean
    public ConsumerFactory<String,Object> consumerFactoryForJsonMessage(){
        return new DefaultKafkaConsumerFactory<>(consumerConfigForJsonMessage());
    }
    @Bean(name = "KafkaListenerContainerFactoryForJsonMessage")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactoryForJsonMessage() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryForJsonMessage());
        return factory;
    }
}

/*
* For Generation for Deserialization,
* Ref this - https://stackoverflow.com/questions/74113383/kafka-different-deserializers-for-different-topics
*/