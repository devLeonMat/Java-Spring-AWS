package com.rleon.sqs.demo.sqs.with.spring.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sqs")
public class SQSController {

    private static final Logger LOG = LoggerFactory.getLogger(SQSController.class);

    private QueueMessagingTemplate queueMessagingTemplate;

    private SQSController(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }


    @Value("${cloud.aws.end-point.uri}")
    private String sqsEndPoint;

    @GetMapping("/sendMessage")
    public void sendMessage() {
        LOG.info("Iniciando Envio");
        queueMessagingTemplate.send(sqsEndPoint, MessageBuilder.withPayload("hellooo this in my sqs first test").build());
    }


}
