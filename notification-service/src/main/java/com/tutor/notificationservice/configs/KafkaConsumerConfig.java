package com.tutor.notificationservice.configs;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@EnableKafka // Annotates this class to enable Kafka functionality.
@Configuration // Annotates this class as a Spring configuration class.
public class KafkaConsumerConfig {

    // Injects values from application.properties or application.yml.
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String kafkaConsumerBootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String kafkaConsumerGroupId;

    // Defines configuration properties for consuming JSON messages.
    public Map<String, Object> consumerJsonConfig() {
        Map<String, Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConsumerBootstrapServers);
        map.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerGroupId);
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return map;
    }

    // Defines a bean for ConcurrentKafkaListenerContainerFactory.
    @Bean
    public <T> ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerJsonFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ?> factory = new ConcurrentKafkaListenerContainerFactory<>();
        DefaultKafkaConsumerFactory<String, Object> defaultKafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(consumerJsonConfig());

        final JsonDeserializer<Object> valueDeserializer = new JsonDeserializer<>();
        valueDeserializer.addTrustedPackages("com.example.commonmodule.DTOs");
        defaultKafkaConsumerFactory.setValueDeserializer(valueDeserializer);
        defaultKafkaConsumerFactory.setKeyDeserializer(new StringDeserializer());

        factory.setConsumerFactory(defaultKafkaConsumerFactory);
        factory.setMessageConverter(new StringJsonMessageConverter());
        factory.setBatchListener(true);

        return factory;
    }
}
