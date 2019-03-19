package com.mobilearth;

import java.util.List;
import java.util.Map;

/**
 * Created by sandro on 19/03/19
 */
public class Producer implements Runnable {

    private int quantity;
    private final List<Consumer> consumers;
    private final Map<Integer, User> map;

    public Producer(int quantity, final List<Consumer> consumers, final Map<Integer, User> map) {
        this.quantity = quantity;
        this.consumers = consumers;
        this.map = map;
    }

    @Override
    public void run() {

        ////////////////////////////////////////////
        // PRODUCING THE OBJECTS TO BE CONSUMED
        ////////////////////////////////////////////
        int i = 0;
        while(i <= quantity){

            try{
                i++;
                int id = (int) Math.round(Math.random() * quantity);
                User user = new User(id, "User" + id);
                map.put(id, user);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        /////////////////////////////////
        // WAIT UNTIL THE QUEUE IS EMPTY
        /////////////////////////////////
        while (map.size() > 0) {
            try {
                System.out.println("Producer waiting to end, map size is " + map.size() + " now.");
                Thread.sleep(1000);
            } catch (final InterruptedException e) {
                break;
            }
        }

        ////////////////////////////////////////////
        // SEND TO ALL CONSUMERS THE EXIT CONDITION
        ////////////////////////////////////////////
        System.out.println("Send to all consumers the exit condition");
        for (final Consumer consumer : consumers) {
            consumer.setExit(true);
        }
    }
}
