package com.thinkitdevit.tinyproduct.ui.rest.exception;

import com.thinkitdevit.tinyproduct.common.exception.DataNotFoundException;
import com.thinkitdevit.tinyproduct.ui.dto.ApiErrorResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ApiExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof DataNotFoundException) {
            return handleDataNotFoundException((DataNotFoundException) exception);
        } else {
            return handleGeneralException(exception);
        }
    }

    private Response handleDataNotFoundException(DataNotFoundException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(404, e.getMessage());
        return Response.status(Response.Status.NOT_FOUND)
                .entity(apiErrorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private Response handleGeneralException(Exception e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(500, e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(apiErrorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
