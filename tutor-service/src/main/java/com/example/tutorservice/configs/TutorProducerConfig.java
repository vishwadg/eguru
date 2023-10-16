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
 * The type Tutor producer config.
 */
@Configuration
public class TutorProducerConfig {
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String kafkaProducerServers;


    /**
     * Tutor producer config map.
     *
     * @return the map
     */
    public Map<String, Object> tutorProducerConfig() {
        Map<String, Object> tpConfig = new HashMap<>();
        tpConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerServers);
        tpConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        tpConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return tpConfig;
    }

    /**
     * Producer factory producer factory.
     *
     * @param <T> the type parameter
     * @return the producer factory
     */
    @Bean
    public <T> ProducerFactory<String, T> producerFactory() {
        return new DefaultKafkaProducerFactory<>(tutorProducerConfig());
    }

    /**
     * Kafka template kafka template.
     *
     * @param <T> the type parameter
     * @return the kafka template
     */
    @Primary
    @Bean
    public <T> KafkaTemplate<String, T> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
