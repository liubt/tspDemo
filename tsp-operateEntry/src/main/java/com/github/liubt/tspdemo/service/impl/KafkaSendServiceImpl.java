package com.github.liubt.tspdemo.service.impl;

import com.github.liubt.tspdemo.service.KafkaSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaSendServiceImpl implements KafkaSendService {
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(String topic, String content) {
        log.info("topic:" + topic + ",content:" + content);
        kafkaTemplate.send(topic, content);
    }
}
