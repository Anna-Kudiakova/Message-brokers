package com.coursework.rabbitmqsubscriber.services;

import com.coursework.rabbitmqsubscriber.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Subscriber {


    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(Message message) {
        log.info(String.format("Reporter : %s\nEmail: %s\nReport: %s", message.getName(),
                message.getEmail(), message.getReport()));

    }

}
