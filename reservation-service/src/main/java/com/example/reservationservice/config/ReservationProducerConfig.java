package com.example.reservationservice.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * The ReservationProducerConfig class provides configuration for producing messages to a Kafka topic.
 * It defines the producer properties, such as the bootstrap servers and serializers.
 */
@Configuration
public class ReservationProducerConfig {
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String kafkaProducerServers;

    /**
     * Creates a configuration map for the Kafka producer.
     *
     * @return A Map of producer configuration properties.
     */
    public Map<String, Object> tutorProducerConfig() {
        Map<String, Object> tpConfig = new HashMap<>();
        tpConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerServers);
        tpConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        tpConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return tpConfig;
    }

    /**
     * Creates a producer factory for producing messages to Kafka.
     *
     * @param <T> The type of values to be sent to the Kafka topic.
     * @return A ProducerFactory for producing messages to Kafka.
     */
    @Bean
    public <T> ProducerFactory<String, T> producerFactory() {
        return new DefaultKafkaProducerFactory<>(tutorProducerConfig());
    }

    /**
     * Creates a KafkaTemplate for sending messages to Kafka.
     *
     * @param <T> The type of values to be sent to the Kafka topic.
     * @return A KafkaTemplate for producing and sending messages to Kafka.
     */
    @Primary
    @Bean
    public <T> KafkaTemplate<String, T> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

