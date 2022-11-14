package com.coursework.artemisproducer.controllers;

import com.coursework.artemisproducer.model.Message;
import com.coursework.artemisproducer.services.Producer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//commented code is for point to point communication

@RestController
@RequestMapping("/message")
public class PublisherController {

    private final Producer producer;

    public PublisherController(Producer producer) {
        this.producer = producer;
    }

//    @PostMapping("/queue")
//    public ResponseEntity<String> publishToQueue(@RequestBody Message message) {
//        try {
//            producer.sendToQueue(message);
//            return new ResponseEntity<>("Sent", HttpStatus.OK);
//        } catch (Exception exception) {
//            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping
    public ResponseEntity<String> publishToTopic(@RequestBody Message message) {
        try {
            producer.sendToTopic(message);
            return new ResponseEntity<>("Sent", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
