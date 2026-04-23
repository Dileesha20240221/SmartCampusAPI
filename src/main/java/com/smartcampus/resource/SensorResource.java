package com.smartcampus.resource;

import com.smartcampus.model.*;
import com.smartcampus.service.DataStore;
import com.smartcampus.exception.LinkedResourceNotFoundException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.*;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    @GET
    public Collection<Sensor> getSensors(@QueryParam("type") String type) {

        if (type == null) return DataStore.sensors.values();

        List<Sensor> result = new ArrayList<>();

        for (Sensor s : DataStore.sensors.values()) {
            if (s.getType().equalsIgnoreCase(type)) {
                result.add(s);
            }
        }
        return result;
    }

    @POST
    public Response addSensor(Sensor sensor) {

        if (!DataStore.rooms.containsKey(sensor.getRoomId())) {
            throw new LinkedResourceNotFoundException("Room not found!");
        }

        DataStore.sensors.put(sensor.getId(), sensor);

        DataStore.rooms.get(sensor.getRoomId())
                .getSensorIds().add(sensor.getId());

        return Response.status(Response.Status.CREATED).entity(sensor).build();
    }

    // ✅ ADD THIS PART (VERY IMPORTANT)
    @Path("/{id}/readings")
    public SensorReadingResource getSensorReadings(@PathParam("id") String id) {
        return new SensorReadingResource(id);
    }
}