package com.demo.kafka.servic.producer;

import com.demo.kafka.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AccountProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public List<Account> readDataFromJson(){
        List<Account> accountList;
        try {
            File file = new File(this.getClass().getClassLoader().getResource("AccountDetails.json").getFile());
            accountList = new ObjectMapper().readValue(file, ArrayList.class);
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
        return accountList;
    }

    public void sendDataToKafka(int partitionNo){
        kafkaTemplate.send("testTopic", partitionNo, "accounts", readDataFromJson());
    }

    public List<Account> getData(){
        return readDataFromJson();
    }
}
