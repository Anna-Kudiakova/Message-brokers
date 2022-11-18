package com.coursework.producer.services;

import com.coursework.dto.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProducerService {

    @Value("${topic.message.name}")
    private String topicName;

    private final ReactiveRedisOperations<String, Message> redisTemplate;

    public ProducerService(ReactiveRedisOperations<String, Message> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Mono<String> publish(Message message) {
        return redisTemplate.convertAndSend(topicName, message)
                .map(r -> "Message was successfully sent");
    }
}
