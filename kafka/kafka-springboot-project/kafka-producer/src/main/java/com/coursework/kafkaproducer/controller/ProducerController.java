package com.coursework.kafkaproducer.controller;

import com.coursework.kafkaproducer.model.Message;
import com.coursework.kafkaproducer.services.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/publisher")
public class ProducerController {

    private final Producer producer;

    public ProducerController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping("/message")
    ResponseEntity<String> publishMessage(@RequestBody Message message) {
        try {
            producer.sendMessage(message);
            return new ResponseEntity<>("Your report was successfully sent", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(String.format("%s - %s", e, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/filtered")
    ResponseEntity<String> publishString(@RequestParam(value = "str") String str) {
        try {
            producer.sendFilteredString(str);
            return new ResponseEntity<>("Your report was successfully sent", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(String.format("%s - %s", e, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
