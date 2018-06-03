package com.github.liubt.tspdemo.service;

public interface KafkaSendService {
    
    void send(String topic, String content);
}
