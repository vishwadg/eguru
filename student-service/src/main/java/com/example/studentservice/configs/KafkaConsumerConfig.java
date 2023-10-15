package com.example.studentservice.configs;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    // Get the bootstrap servers from the properties file
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    // Get the group ID from the properties file
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    // Create a map of the consumer configuration
    public Map<String, Object> consumerJsonConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return props;
    }

    // Create a bean for the ConcurrentKafkaListenerContainerFactory
    @Bean
    public <T> ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerJsonFactory() {

        // Create a new ConcurrentKafkaListenerContainerFactory
        ConcurrentKafkaListenerContainerFactory<String, ?> factory = new ConcurrentKafkaListenerContainerFactory<>();
        // Create a new DefaultKafkaConsumerFactory
        DefaultKafkaConsumerFactory<String, Object> defaultKafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(consumerJsonConfig());

        // Create a new JsonDeserializer
        final JsonDeserializer<Object> valueDeserializer = new JsonDeserializer<>();
        // Add the trusted packages
        valueDeserializer.addTrustedPackages("com.example.commonmodule.DTOs");
        // Set the value deserializer
        defaultKafkaConsumerFactory.setValueDeserializer(valueDeserializer);
        // Set the key deserializer
        defaultKafkaConsumerFactory.setKeyDeserializer(new StringDeserializer());

        // Set the consumer factory
        factory.setConsumerFactory(defaultKafkaConsumerFactory);
        // Set the message converter
        factory.setMessageConverter(new StringJsonMessageConverter());
        // Set the batch listener
        factory.setBatchListener(true);
        // Return the factory
        return factory;
    }

}