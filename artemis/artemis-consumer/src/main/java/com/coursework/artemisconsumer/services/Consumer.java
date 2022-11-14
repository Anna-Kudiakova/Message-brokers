package com.coursework.artemisconsumer.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class Consumer {

    @JmsListener( destination = "${spring.activemq.topic-name}", subscription = "batko")
    public void messageListener(String message){
        log.info("Message received, {}",message);
    }

}
