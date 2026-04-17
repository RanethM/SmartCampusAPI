/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampusapi.resources;

import com.mycompany.smartcampusapi.database.DataStore;
import com.mycompany.smartcampusapi.model.Sensor;
import com.mycompany.smartcampusapi.model.SensorReading;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ranet
 */
public class SensorReadingResource {
    private String sensorId; 
    
    public SensorReadingResource(String sensorId){
        this.sensorId = sensorId;
        
    }
    
    //GET sensor readings 
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorReading> getReadings(){ 
        return DataStore.readings.getOrDefault(sensorId, new ArrayList<>());
    }
    
    //POST to add a new sensor reading 
    @POST 
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response addReading(SensorReading reading){ 
        Sensor sensor = DataStore.sensors.get(sensorId);
        
        if (sensor == null){ 
            return Response.status(Response.Status.NOT_FOUND).build(); 
            
        }
        
        // Add the reading 
        
        DataStore.readings.computeIfAbsent(sensorId, k-> new ArrayList())
                .add(reading); 
        
        sensor.setCurrentValue(reading.getValue()); 
        
        return Response.status(Response.Status.CREATED).entity(reading)
                .build(); 
    }
    
    
}
