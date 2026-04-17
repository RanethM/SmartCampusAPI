/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampusapi.resources;

import com.mycompany.smartcampusapi.database.DataStore;
import com.mycompany.smartcampusapi.model.Room;
import com.mycompany.smartcampusapi.model.Sensor;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ranet
 */
@Path("/sensors")
public class SensorResource {
    
    // GET the sensor  
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sensor> getSensors(@QueryParam("type") String type){
        if(type==null){ 
            return DataStore.sensors.values().stream().collect(Collectors.toList());
                    
        }
        
        //Allows to filter sensors using the query params
        
        return DataStore.sensors.values().stream() 
                .filter(sensor -> sensor.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList()); 
    }
    
    
    //POST a new sensor
    @POST 
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response addSensor(Sensor sensor){
    //First we validate 
    Room room = DataStore.rooms.get(sensor.getRoomId()); 
    
    if (room== null){ 
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Room does not exist")
                .build(); 
    }
    
    // Save the sensor in the DataStore 
    
    DataStore.sensors.put(sensor.getId(),sensor); 
    
    //Link the sensors to the room 
    
    room.getSensorIds().add(sensor.getId()); 
    
    return Response.status(Response.Status.CREATED)
            .entity(sensor)
            .build(); 
    }
    
    
}
