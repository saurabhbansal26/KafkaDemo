package com.demo.kafka.controller;

import com.demo.kafka.model.Account;
import com.demo.kafka.servic.consumer.AccountConsumer;
import com.demo.kafka.servic.producer.AccountProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    AccountProducer accountProducer;

    @Autowired
    AccountConsumer accountConsumer;

    @GetMapping
    public List<Account> getAccountData(){
        return accountProducer.getData();
    }

    @GetMapping("/kafka")
    public List<Account> getKafkaAccountData(@RequestParam int partitionNo){
        return accountConsumer.getDataFromKafka(partitionNo);
    }

    @PostMapping("/upload-data")
    public void uploadData(@RequestParam int partitionNo){
        accountProducer.sendDataToKafka(partitionNo);
    }

}
