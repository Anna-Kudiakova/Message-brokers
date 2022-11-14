package com.coursework.artemisproducer.services;

import com.coursework.artemisproducer.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

//commented code is for point to point communication

@Service
public class Producer {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper mapper;

//    @Value("${spring.artemis.embedded.queues}")
//    private String queue;

    @Value("${spring.activemq.topic-name}")
    private String topic;

    public Producer(JmsTemplate jmsTemplate, ObjectMapper mapper) {
        this.jmsTemplate = jmsTemplate;
        this.mapper = mapper;
    }

//    public void sendToQueue(Message message) throws JsonProcessingException {
//        jmsTemplate.convertAndSend(queue, mapper.writeValueAsString(message));
//    }

    public void sendToTopic(Message message) throws JsonProcessingException {
        jmsTemplate.convertAndSend(topic, mapper.writeValueAsString(message));
    }
}
