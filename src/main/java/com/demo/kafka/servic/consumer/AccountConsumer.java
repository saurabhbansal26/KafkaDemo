package com.demo.kafka.servic.consumer;

import com.demo.kafka.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Slf4j
@Service
public class AccountConsumer {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    String consumerServer;

    @Value("${spring.kafka.consumer.group-id}")
    String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    String autoOffsetReset;

    @Value("${spring.kafka.consumer.key-deserializer}")
    String keyDeserializer;

    @Value("${spring.kafka.consumer.value-deserializer}")
    String valueDeserializer;

    public List<Account> getDataFromKafka(int partitionNo){
        List<Account> accountList = new ArrayList<>();
        Properties properties = new Properties();
        properties.put("bootstrap-servers", consumerServer);
        properties.put("group-id", groupId);
        properties.put("auto-offset-reset", autoOffsetReset);
        properties.put("key-deserializer", keyDeserializer);
        properties.put("value-deserializer", valueDeserializer);

        KafkaConsumer<String, Object> consumer = new KafkaConsumer<>(properties);
        TopicPartition topicPartition = new TopicPartition("testTopic", partitionNo);
        consumer.assign(List.of(topicPartition));
        ConsumerRecords<String, Object> records = consumer.poll(Duration.ofSeconds(5));

        if(!records.isEmpty()){
            for(ConsumerRecord<String, Object> record : records){
                accountList.addAll((List<Account>) record.value());
            }
        }
        consumer.close();
        return accountList;
    }
}
