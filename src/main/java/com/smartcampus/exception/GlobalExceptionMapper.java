package com.smartcampus.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable ex) {
        return Response.status(500)
                .entity("{\"error\":\"Internal Server Error\"}")
                .build();
    }
}