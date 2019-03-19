package com.mobilearth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
* This project is a test in a Map Data Structure
* To test properly these structure a Producer Consumer Pattern was created
* The producer is responsible to fill the map using randoms key based on capacity
* Ten consumers are created to try guess valid keys present in the map
* When a key is found by the consumer it is removed
* When all key are found the producer notify all consumers to stop the process
*
* With this pattern is possible to check the thread safe property in the maps
* Two maps can be tested
* ConcurrentHashMap is a map from java library design to be thread safe
* CustomMap was created to this project to have the same behavior
* */

public class Main {

    enum MapType{
        JAVA_MAP,
        CUSTOM_MAP
    }

    public static void main(String[] args) {

        MapType mapType = MapType.CUSTOM_MAP;

        // Initialization of variables used
        int capacity = 100;
        Map<Integer, User> map = null;
        List<Consumer> consumers = new ArrayList<>();


        // Setting method accordingly argument
        if(mapType == MapType.JAVA_MAP) {
            map = new ConcurrentHashMap<>();
        }
        if(mapType == MapType.CUSTOM_MAP) {
            map = new CustomMap<>(capacity);
        }

        // Create consumers
        for(int i = 1; i <= 10; i++){
            Consumer consumer = new Consumer(capacity, map);
            consumers.add(consumer);
        }

        // Create and Start producer
        Producer producer = new Producer(capacity, consumers, map);
        new Thread(producer).start();

        // Start consumers
        for(Consumer consumer : consumers){
            new Thread(consumer).start();
        }

    }
}
