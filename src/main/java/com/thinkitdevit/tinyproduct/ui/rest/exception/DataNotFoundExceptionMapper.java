package com.thinkitdevit.tinyproduct.ui.rest.exception;

import com.thinkitdevit.tinyproduct.common.exception.DataNotFoundException;
import com.thinkitdevit.tinyproduct.ui.dto.ApiErrorResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

//@Provider
public class DataNotFoundExceptionMapper
        //implements ExceptionMapper<DataNotFoundException> {
{

//    @Override
//    public Response toResponse(DataNotFoundException e) {
//
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(404, e.getMessage());
//
//        return Response.status(Response.Status.NOT_FOUND).entity(apiErrorResponse).type(MediaType.APPLICATION_JSON).build();
//    }

}
