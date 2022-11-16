package com.coursework.kafkaproducer.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaTopicConfig {

    @Value(value = "${topic.message.name}")
    private String messageTopicName;

    @Value(value = "${topic.filtered.name}")
    private String filteredTopicName;

    @Bean
    public NewTopic messageTopic() {
        return new NewTopic(messageTopicName, 4, (short) 1);
    }

    @Bean
    public NewTopic filteredTopic() {
        return new NewTopic(filteredTopicName, 1, (short) 1);
    }

}
