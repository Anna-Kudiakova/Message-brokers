package com.coursework.rabbitmqpublisher.controllers;

import com.coursework.rabbitmqpublisher.config.RabbitMQConfig;
import com.coursework.rabbitmqpublisher.model.Message;
import com.coursework.rabbitmqpublisher.services.Publisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class PublisherController {

    private final Publisher publisher;

    public PublisherController(Publisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping
    ResponseEntity<String> publish(@RequestBody Message message) {
        try {
            publisher.publish(message);
            return new ResponseEntity<>("Your report was successfully sent", HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(String.format("%s - %s", e, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
