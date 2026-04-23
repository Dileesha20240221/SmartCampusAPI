package com.smartcampus.filter;

import jakarta.ws.rs.container.*;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("Request: " + requestContext.getMethod());
    }

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {

        System.out.println("Response Status: " + responseContext.getStatus());
    }
}