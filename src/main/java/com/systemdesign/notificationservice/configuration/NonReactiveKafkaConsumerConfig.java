package com.systemdesign.notificationservice.configuration;

import com.systemdesign.notificationservice.DTO.Event;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class NonReactiveKafkaConsumerConfig {


    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, Event> consumerFactory(){
        Map<String,Object> consumerConfig = new HashMap<>();
        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        logConsumerConfig(consumerConfig);

        return new DefaultKafkaConsumerFactory<>(consumerConfig, new StringDeserializer(), new JsonDeserializer<>(Event.class));
    }

    private void logConsumerConfig(Map<String, Object> consumerConfig) {
        StringBuilder sb = new StringBuilder("ConsumerConfig values: ");
        for (Map.Entry<String, Object> entry : consumerConfig.entrySet()) {
            sb.append("\n\t").append(entry.getKey()).append(" = ").append(entry.getValue());
        }
        log.info(sb.toString());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,Event> concurrentConsumerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Event> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }
}
