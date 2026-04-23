package com.smartcampus.smartcampusapi;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.net.URI;

public class SmartCampusAPI {

    // Base URL
    public static final String BASE_URI = "http://localhost:8080/api/v1/";

    // Start server
    public static HttpServer startServer() {

        final ResourceConfig config = new ResourceConfig()
                // IMPORTANT: scan ALL your packages
                .packages("com.smartcampus");

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }

    public static void main(String[] args) {

        final HttpServer server = startServer();

        System.out.println("🚀 Server started at: " + BASE_URI);
        System.out.println("Press CTRL + C to stop...");

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}