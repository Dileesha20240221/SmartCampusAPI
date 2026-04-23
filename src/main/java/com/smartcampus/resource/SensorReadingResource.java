package com.smartcampus.resource;

import com.smartcampus.model.Sensor;
import com.smartcampus.model.SensorReading;
import com.smartcampus.service.DataStore;
import com.smartcampus.exception.SensorUnavailableException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.ArrayList;
import java.util.List;

public class SensorReadingResource {

    private String sensorId;

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    // 🔹 GET readings (FIXED for 404)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReadings() {

        Sensor sensor = DataStore.sensors.get(sensorId);

        // ✅ 404 if sensor not found
        if (sensor == null) {
            return Response.status(404)
                    .entity("{\"error\":\"Sensor not found\"}")
                    .build();
        }

        // ensure list exists
        if (sensor.getReadings() == null) {
            sensor.setReadings(new ArrayList<>());
        }

        return Response.ok(sensor.getReadings()).build();
    }

    // 🔹 ADD reading
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReading(SensorReading reading) {

        Sensor sensor = DataStore.sensors.get(sensorId);

        // ✅ 404 if sensor not found
        if (sensor == null) {
            return Response.status(404)
                    .entity("{\"error\":\"Sensor not found\"}")
                    .build();
        }

        // ✅ 403 if maintenance
        if ("MAINTENANCE".equals(sensor.getStatus())) {
            throw new SensorUnavailableException("Sensor not available");
        }

        // ensure list exists
        if (sensor.getReadings() == null) {
            sensor.setReadings(new ArrayList<>());
        }

        // add reading
        sensor.getReadings().add(reading);
        sensor.setCurrentValue(reading.getValue());

        return Response.status(Response.Status.CREATED)
                .entity(reading)
                .build();
    }
}