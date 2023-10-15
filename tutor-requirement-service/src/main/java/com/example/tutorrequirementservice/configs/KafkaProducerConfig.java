package com.example.tutorrequirementservice.configs;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    // Get the bootstrap server from the application.properties file
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    // Create a map to store the producer configuration
    public Map<String, Object> producerJsonConfig(){
        Map<String, Object> configProps = new HashMap<>();

        // Set the bootstrap server, key serializer class and value serializer class
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configProps;
    }

    // Create a bean for the producer factory
    @Bean
    public <T> ProducerFactory<String, T> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerJsonConfig());
    }

    // Create a bean for the KafkaTemplate
    @Primary
    @Bean
    public <T> KafkaTemplate<String, T> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

}