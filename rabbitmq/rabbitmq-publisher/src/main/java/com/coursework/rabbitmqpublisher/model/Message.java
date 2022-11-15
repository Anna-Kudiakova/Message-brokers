package com.coursework.rabbitmqpublisher.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message implements Serializable {

    private String name;
    private String email;
    private String report;

}