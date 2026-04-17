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
    
    
}
