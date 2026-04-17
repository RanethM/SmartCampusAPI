/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampusapi.model;

/**
 *
 * @author ranet
 */
public class Room {
    private String id;
    private String name;
    private int capacity;
    private java.util.List<String> sensorIds = new java.util.ArrayList<>();

    public Room() {}

    public Room(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public java.util.List<String> getSensorIds() { return sensorIds; }
    public void setSensorIds(java.util.List<String> sensorIds) { this.sensorIds = sensorIds; }
}