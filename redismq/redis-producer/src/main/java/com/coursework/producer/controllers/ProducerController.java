package com.coursework.producer.controllers;

import com.coursework.dto.Message;
import com.coursework.producer.services.ProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/message")
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public Mono<ResponseEntity<String>> publish(@RequestBody Message message) {
        try {
            return producerService.publish(message)
                    .map(body -> ResponseEntity.ok("Message was successfully sent"))
                    .subscribeOn(Schedulers.newSingle("main"));
        } catch (Exception e) {
            return Mono.just(new ResponseEntity<>(String.format("%s - %s", e, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR));
        }

    }
}
