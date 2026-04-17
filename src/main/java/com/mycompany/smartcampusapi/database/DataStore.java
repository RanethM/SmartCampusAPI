/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampusapi.database;

import com.mycompany.smartcampusapi.model.Room;
import com.mycompany.smartcampusapi.model.Sensor;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ranet
 */
public class DataStore {
    
    public static Map<String, Room> rooms = new HashMap<>();

    static {
        rooms.put("R1", new Room("R1", "Library", 50));
        rooms.put("R2", new Room("R2", "Lab", 30));
    }
    
    public static Map<String, Sensor> sensors = new HashMap<>();
    
    
}
