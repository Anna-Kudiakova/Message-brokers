package com.coursework.consumer.services;

import com.coursework.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class ConsumerService {

    @Value("${topic.message.name}")
    private String topicName;

    private final ReactiveRedisOperations<String, Message> redisTemplate;

    public ConsumerService(ReactiveRedisOperations<String, Message> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void subscribe() {
        redisTemplate
                .listenTo(ChannelTopic.of(topicName))
                .map(ReactiveSubscription.Message::getMessage)
                .subscribe(message -> log.info(String.format("Reporter : %s\nEmail: %s\nReport: %s", message.getName(),
                        message.getEmail(), message.getReport())));
    }
}
