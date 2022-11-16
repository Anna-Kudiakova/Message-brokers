package com.coursework.kafkaproducer.services;

import com.coursework.kafkaproducer.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaTemplate<String, Message> messageKafkaTemplate;

    @Value(value = "${topic.message.name}")
    private String topicName;

    @Value(value = "${topic.filtered.name}")
    private String filteredTopicName;

    public Producer(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, Message> messageKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.messageKafkaTemplate = messageKafkaTemplate;
    }

    public void sendMessage(Message message) {
        messageKafkaTemplate.send(topicName, message);
    }

    public void sendFilteredString(String str) {
        var future = kafkaTemplate.send(filteredTopicName, str);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info(String.format("%s - %s",ex, ex.getMessage()));
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info(String.format("Message [%s] was just sent",str));
            }
        });
    }

}
