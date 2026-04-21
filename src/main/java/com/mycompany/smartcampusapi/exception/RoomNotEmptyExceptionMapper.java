/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampusapi.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.mycompany.smartcampusapi.model.ErrorMessage;

@Provider
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException> {

    @Override
    public Response toResponse(RoomNotEmptyException exception) {

        ErrorMessage error = new ErrorMessage(
                exception.getMessage(),
                409
        );

        return Response.status(Response.Status.CONFLICT)
                .entity(error)
                .type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                .build();
    }
}