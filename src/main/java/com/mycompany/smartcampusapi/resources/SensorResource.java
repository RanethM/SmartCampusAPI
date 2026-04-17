/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampusapi.resources;

import com.mycompany.smartcampusapi.database.DataStore;
import com.mycompany.smartcampusapi.model.Sensor;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
        
        return DataStore.sensors.values().stream() 
                .filter(sensor -> sensor.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList()); 
    }
    
}
