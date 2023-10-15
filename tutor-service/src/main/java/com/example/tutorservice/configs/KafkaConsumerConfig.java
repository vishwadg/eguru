package com.example.tutorservice.configs;

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

/**
 * The KafkaConsumerConfig class configures Kafka consumer properties and settings for consuming JSON messages.
 * It specifies the bootstrap servers, group ID, and deserializer classes for message keys and values.
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    /**
     * Configure Kafka consumer properties for JSON messages.
     *
     * @return A map of consumer properties.
     */
    public Map<String, Object> consumerJsonConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return props;
    }

    /**
     * Create a Kafka listener container factory for JSON messages.
     *
     * @return The Kafka listener container factory configured for JSON deserialization.
     */
    @Bean
    public <T> ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerJsonFactory() {

        ConcurrentKafkaListenerContainerFactory<String, ?> factory = new ConcurrentKafkaListenerContainerFactory<>();
        DefaultKafkaConsumerFactory<String, Object> defaultKafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(consumerJsonConfig());

        final JsonDeserializer<Object> valueDeserializer = new JsonDeserializer<>();
        valueDeserializer.addTrustedPackages("com.example.commonsmodule.DTOs");
        defaultKafkaConsumerFactory.setValueDeserializer(valueDeserializer);
        defaultKafkaConsumerFactory.setKeyDeserializer(new StringDeserializer());

        factory.setConsumerFactory(defaultKafkaConsumerFactory);
        factory.setMessageConverter(new StringJsonMessageConverter());
        factory.setBatchListener(true);
        return factory;
    }

}
