package com.example.tutorservice.configs;

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

/**
 * The TutorProducerConfig class configures Kafka producer properties and settings for producing messages.
 * It specifies the bootstrap servers and serializer classes for message keys and values.
 */
@Configuration
public class TutorProducerConfig {
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String kafkaProducerServers;


    /**
     * Configure Kafka producer properties for producing messages.
     *
     * @return A map of producer properties.
     */
    public Map<String, Object> tutorProducerConfig() {
        Map<String, Object> tpConfig = new HashMap<>();
        tpConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerServers);
        tpConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        tpConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return tpConfig;
    }


    /**
     * Create a Kafka producer factory for producing messages.
     *
     * @return The Kafka producer factory configured with properties.
     */
    @Bean
    public <T> ProducerFactory<String, T> producerFactory() {
        return new DefaultKafkaProducerFactory<>(tutorProducerConfig());
    }

    /**
     * Create a KafkaTemplate for producing messages.
     *
     * @return The KafkaTemplate configured with the producer factory.
     */
    @Primary
    @Bean
    public <T> KafkaTemplate<String, T> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
