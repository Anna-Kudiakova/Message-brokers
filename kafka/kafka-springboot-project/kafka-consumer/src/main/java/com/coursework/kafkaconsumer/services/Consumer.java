package com.coursework.kafkaconsumer.services;

import com.coursework.kafkaconsumer.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Consumer {


    @KafkaListener(topicPartitions = @TopicPartition(topic = "${topic.message.name}", partitions = { "0", "3" })
            , groupId = "messages", containerFactory = "messageKafkaListenerContainerFactory")
    public void listenMessage(@Payload Message message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info(String.format("\nReporter : %s\nEmail: %s\nReport: %s\nPartition: %s", message.getName(),
                message.getEmail(), message.getReport(), partition));
    }

    @KafkaListener(topics  = "${topic.filtered.name}", groupId = "filtered",
            containerFactory = "filterKafkaListenerContainerFactory")
    public void listenFiltered(String message) {
        log.info(String.format("\nFiltered string received: %s", message));
    }


}
