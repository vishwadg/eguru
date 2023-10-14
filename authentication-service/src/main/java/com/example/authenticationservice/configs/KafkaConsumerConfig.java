package com.example.authenticationservice.configs;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
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
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;


    //Method to configure kafka consumer properties
    public Map<String, Object> consumerJsonConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);// Kafka broker servers
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);// Consumer group ID
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);//Key deserializer
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);// Value deserializer (JSON)
        return config;
    }

    //Bean definition for Kafka Listener container factory
    @Bean
    public <T> ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerJsonFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ?> factory = new ConcurrentKafkaListenerContainerFactory<>();

        // Create a Kafka consumer factory with the configured properties
        DefaultKafkaConsumerFactory<String, Object> defaultKafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(consumerJsonConfig());

        //Configure the value deserializer for JSON messages
        final JsonDeserializer<Object> valueDeserializer = new JsonDeserializer<>();
        valueDeserializer.addTrustedPackages("com.example.commonsmodule.DTOs");// Trusted packages for deserialization

        defaultKafkaConsumerFactory.setValueDeserializer(valueDeserializer);

        // Set the key deserializer as StringDeserializer
        defaultKafkaConsumerFactory.setKeyDeserializer(new StringDeserializer());

        // Configure the factory with the consumer factory, message converter, and batch listener
        factory.setConsumerFactory(defaultKafkaConsumerFactory);
        factory.setMessageConverter(new StringJsonMessageConverter()); // Message converter fro JSON
        factory.setBatchListener(true); // Enable batch message consumption
        return factory; //Return the configuration kafka listener container factory
    }

}
