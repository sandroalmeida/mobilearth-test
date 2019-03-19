package com.mobilearth;

import java.util.Map;

/**
 * Created by sandro on 19/03/19
 */
public class Consumer implements Runnable {

    boolean exit = false;
    int quantity;
    private Map<Integer, User> map;

    public Consumer(int quantity, Map<Integer, User> map) {
        this.quantity = quantity;
        this.map = map;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    @Override
    public void run() {

        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " consumer init");

        while (!exit) {
            try {
                if (map.size() > 0) {

                    // Try to remove an aleatory key
                    int id = (int) Math.round(Math.random() * quantity);
                    User user = map.remove(id);
                    if(user != null)
                        System.out.println("Remove User: " + user);
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(threadName + "consumer exiting");
    }
}
