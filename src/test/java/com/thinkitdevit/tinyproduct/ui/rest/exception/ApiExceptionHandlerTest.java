package com.thinkitdevit.tinyproduct.ui.rest.exception;

import com.thinkitdevit.tinyproduct.common.exception.DataNotFoundException;
import com.thinkitdevit.tinyproduct.ui.dto.ApiErrorResponse;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiExceptionHandlerTest {

    private ApiExceptionHandler exceptionHandler;

    @BeforeEach
    public void setUp() {
        exceptionHandler = new ApiExceptionHandler();
    }

    @Test
    public void whenDataNotFoundException_thenResponseShouldBeNotFound() {
        // GIVEN
        DataNotFoundException exception = new DataNotFoundException("Data not found");

        // WHEN
        Response response = exceptionHandler.toResponse(exception);

        // THEN
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus(), "Status code should be 404");
        assertTrue(response.getEntity() instanceof ApiErrorResponse, "Entity should be instance of ApiErrorResponse");
        ApiErrorResponse apiErrorResponse = (ApiErrorResponse) response.getEntity();
        assertEquals(404, apiErrorResponse.getCode(), "Error status code should be 404");
        assertEquals("Data not found", apiErrorResponse.getMessage(), "Error message should match");
    }

    @Test
    public void whenGeneralException_thenResponseShouldBeInternalServerError() {
        // GIVEN
        Exception exception = new Exception("General error");

        // WHEN
        Response response = exceptionHandler.toResponse(exception);

        // THEN
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus(), "Status code should be 500");
        assertTrue(response.getEntity() instanceof ApiErrorResponse, "Entity should be instance of ApiErrorResponse");
        ApiErrorResponse apiErrorResponse = (ApiErrorResponse) response.getEntity();
        assertEquals(500, apiErrorResponse.getCode(), "Error status code should be 500");
        assertEquals("General error", apiErrorResponse.getMessage(), "Error message should match");
    }
}
