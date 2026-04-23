package com.smartcampus.resource;

import com.smartcampus.model.Room;
import com.smartcampus.service.DataStore;
import com.smartcampus.exception.RoomNotEmptyException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.*;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    @GET
    public Collection<Room> getAllRooms() {
        return DataStore.rooms.values();
    }

    @POST
    public Response addRoom(Room room) {
        DataStore.rooms.put(room.getId(), room);
        return Response.status(Response.Status.CREATED).entity(room).build();
    }

    @GET
    @Path("/{id}")
    public Room getRoom(@PathParam("id") String id) {
        return DataStore.rooms.get(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRoom(@PathParam("id") String id) {

        Room room = DataStore.rooms.get(id);

        if (room.getSensorIds().size() > 0) {
            throw new RoomNotEmptyException("Room has sensors!");
        }

        DataStore.rooms.remove(id);
        return Response.ok().build();
    }
}