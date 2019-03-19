# mobilearth-test

## Description
This program show a simple way to create a CustomMap Class.
Two conditions were required:
  - Implements Map Interface to use the Custom Map as a normal Java Map
  - Operations Put and Remove need to be Thread safe
 
## Classes
To test the Custom Map was developed two classes using the Design Pattern Producer/Consumer
The class producer is responsible to populate the map with objects
One aditional Bean class was created to be used as objects in this map
The class consumer is responsible to acess try guess possibles entries in the map and remove it.
Many classes consumer can be created to make the guessing process faster and test properly the map.

## Aditional
Using the option JAVA_CLASS
  If set the option JAVA_CLASS is possible to use a normal ConcurrentHashMap from java to see the program performance.
Using the option CUSTOM_CLASS
  If set the option CUSTOM_CLASS the custom class will be used.

## Program Test
This image is about one test running the program with 100 objects and 10 consumers threads.
Was inserted some Thread.sleep statements to show the program run slower.

![mobilearth_test](https://user-images.githubusercontent.com/6804563/54632115-9f23e880-4a3a-11e9-9229-1049440c43bc.gif)

First the program notify the consumers threads initialization.
The producer starting to wait and check the progress using the map size.
The consumers notify the users objects found and remove.
When the producer check the size of map is zero, the job is done.
All consumers are finished.

## Considerations about Custom Map
- The option to implement Map ensure the possibility to test as a normal Map
- Some methods required in the interface Map need to be implemented, only the necessary were implemented.
- To keep the implementation simple the Map was created using an Array and a Linkedlist.

![hashmap](https://user-images.githubusercontent.com/6804563/54634168-dc8a7500-4a3e-11e9-9473-bf61541bd1ce.jpg)

- To keep the performance good is possible to balance the size of array creating a specific method to do this.
- The resizing of the array will ensure a better distribuction of the objects but is necessary to re-hashing.

## About
Feel free to suggest any modification or fork this project.
