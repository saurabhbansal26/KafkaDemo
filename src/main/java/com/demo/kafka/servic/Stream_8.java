package com.demo.kafka.servic;

import com.demo.kafka.model.Account;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream_8 {

    public static void main(String[] args) {
        Map<Integer, Account> map = new HashMap<>();
        map.put(12, new Account(12, "Account2", "Test1"));
        map.put(11, new Account(11, "Account1", "Test2"));

        //Sort Map based on the key using below 2 approaches
        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e1, LinkedHashMap::new)).entrySet()
                .forEach(entry -> System.out.println(entry.getKey()+" "+ entry.getValue()));

        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e1, LinkedHashMap::new)).entrySet()
                .forEach(entry -> System.out.println(entry.getKey()+" "+ entry.getValue()));

        //Sort Map based on the Employee Name
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(Account::getAccountName))).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e1, LinkedHashMap::new));

        //find frequency of each character in a String
        String s = "saurabh is best";
        Map<Character, Long> frequencyMap = s.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(c -> c , Collectors.counting()));

        //Find Sum for Each Type (CREDIT/DEBIT)
        transactionList.stream().collect(Collectors.groupingBy(Tranaction::getType)),
                Collectors.summingDouble(Transaction::getAmount);

    }
}
