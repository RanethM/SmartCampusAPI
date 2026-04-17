/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampusapi.resources;

import com.mycompany.smartcampusapi.database.DataStore;
import com.mycompany.smartcampusapi.model.Room;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
/**
 *
 * @author ranet
 *
 */
@Path("/rooms")
public class RoomResource {
    
    
    // GET all the rooms in the campus
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Room> getAllRooms(){ 
        return DataStore.rooms.values();
    }
    
    //Post a new room for the campus 
    @POST 
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response addRoom(Room room){ 
         DataStore.rooms.put(room.getId(),room); 
         
         return Response.status(Response.Status.CREATED)
                 .entity(room)
                 .build(); 
    }
    
    // GET a single room according to the room ID 
    @GET
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    
    public Room getRoom(@PathParam("roomId") String id ){ 
        return DataStore.rooms.get(id); 
    }
    
    // DELTE room only if the sensor isnt attached to the room 
    
    @DELETE
    @Path("/{roomId}")
    public Response deleteRoom(@PathParam("roomId") String id){ 
        Room room = DataStore.rooms.get(id); 
        
        //if the room is null then  send a not found status
        if (room== null){ 
            return Response.status(Response.Status.NOT_FOUND).build(); 
            
        }
        
        if (!room.getSensorIds().isEmpty()) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Room has sensors, cannot delete")
                    .build();
        }
        
        DataStore.rooms.remove(id); 
        return Response.status(Response.Status.NO_CONTENT).build(); 

    }
    
    
}
