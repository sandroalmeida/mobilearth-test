package com.mobilearth;

import java.util.*;

/**
 * Created by sandro on 19/03/19
 */

class HashNode<K, V> implements Map.Entry<K, V> {

    K key;
    V value;
    HashNode<K, V> previous;
    HashNode<K, V> next;

    public HashNode(K key, V value){
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        return this.value = value;
    }
}

public class CustomMap<K, V> implements Map<K, V>{

    private HashNode<K, V>[] mapArray;
    private int capacity;
    private int size;

    public CustomMap(int capacity){
        this.capacity = capacity;
        this.mapArray = new HashNode[capacity];
        this.size = 0;

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    // Hash Function is responsible to calc the index
    // based on key value and capacity
    private int hashFunction(K key){

        int hashCode = 0;
        if(key instanceof Number){
            hashCode = (int) key;
        }

        int index = hashCode % capacity;
        return index;
    }

    // Return the value of the node with correspondent key
    @Override
    public V get(Object key){

        int index = hashFunction((K)key);
        HashNode<K, V> current = mapArray[index];

        while(current != null){
            if(current.key.equals(key)){
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    @Override
    public boolean containsKey(Object key) {

        int index = hashFunction((K)key);
        HashNode<K, V> current = mapArray[index];

        while(current != null){
            if(current.key.equals(key)){
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public boolean containsValue(Object value) {

        for(HashNode<K,V> node : mapArray){
            while(node != null){
                if(node.value.equals(value)){
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    @Override
    public synchronized V put(K key, V value) {

        // Using Hash Function to identify the index
        // and setting the first node to start look
        // for the key inside the chain of hashNodes
        int index = hashFunction(key);
        HashNode<K, V> currentNode = mapArray[index];
        HashNode<K, V> previousNode = null;

        // Searching key inside the chain
        // If found value parameter is used to update node value
        // return will stop execution for other statements
        while(currentNode != null){
            if(currentNode.key.equals(key)){
                currentNode.value = value;
                return value;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        // Key was not found so the new Node will be created
        HashNode<K, V> newNode = new HashNode<>(key, value);

        // If have some node in the chain new node will connect to it
        if(previousNode != null){
            previousNode.next = newNode;
            newNode.previous = previousNode;
        }
        // If not have any node in the chain first chain element will be new node
        else{
            mapArray[index] = newNode;
        }

        // Updating property size
        size++;

        return value;
    }

    @Override
    public synchronized V remove(Object key) {

        // Using Hash Function to identify the index
        // and setting the first node to start look
        // for the key inside the chain of hashNodes
        int index = hashFunction((K)key);
        HashNode<K, V> hashNode = mapArray[index];
        HashNode<K, V> hashFound = null;
        HashNode<K, V> hashNext = null;
        HashNode<K, V> hashPrevious = null;

        // Searching key inside the chain
        // If found variable hashFound is set with the node
        while(hashNode != null){
            if(hashNode.key.equals(key)){
                hashFound = hashNode;
                break;
            }
            hashNode = hashNode.next;
        }

        // Once found is time to remove the node
        // Remove operation is made updating the references
        // inside the nodes previous and next depending of
        // position of the node
        if(hashFound != null){

            // If node is first node
            if(hashFound.previous == null){
                // node is unique is not necessary update the chain
                if(hashFound.next == null){
                    mapArray[index] = null;
                }
                // node is not unique updating neighbors references
                else{
                    hashNext = hashFound.next;
                    hashFound.next = null;
                    hashNext.previous = null;
                    mapArray[index] = hashNext;
                }
            }
            // If node is last node
            else if(hashFound.next == null){
                hashPrevious = hashFound.previous;
                hashFound.previous = null;
                hashPrevious.next = null;
            }
            // Node is in the middle
            else{
                hashFound.next.previous = hashFound.previous;
                hashFound.previous.next = hashFound.next;
            }
            // Updating property size
            size--;

            return hashFound.value;
        }

        return null;

    }

    @Override
    public void clear() {
        this.mapArray = new HashNode[capacity];
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        for(Entry<K, V> entry :  mapArray){
            set.add(entry);
        }
        return set;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        // TO DO
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
