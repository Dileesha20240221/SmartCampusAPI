package com.smartcampus.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.Map;

@Path("/")
public class DiscoveryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getInfo() {

        Map<String, String> info = new HashMap<>();
        info.put("version", "v1");
        info.put("rooms", "/api/v1/rooms");
        info.put("sensors", "/api/v1/sensors");

        return info;
    }
}