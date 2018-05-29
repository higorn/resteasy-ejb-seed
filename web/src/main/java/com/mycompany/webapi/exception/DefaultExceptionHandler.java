/*
 * File:   DefaultExceptionHandler.java
 *
 * Created on 28/05/18, 22:17
 */
package com.mycompany.webapi.exception;

import com.mycompany.webapi.model.ApiResponse;

import javax.persistence.NoResultException;
import javax.security.auth.login.AccountException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author higor
 */
public class DefaultExceptionHandler implements ExceptionMapper<Exception> {
  private static final Logger logger = Logger.getLogger(DefaultExceptionHandler.class.getName());

  public Response toResponse(Exception exception) {
    Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
    String message = ":(";

    if (exception instanceof AccountException || exception.getCause() instanceof AccountException) {
      status = Response.Status.UNAUTHORIZED;
      message = exception.getMessage();
    } else if (exception instanceof ValidationException || exception.getCause() instanceof ValidationException) {
      status = Response.Status.BAD_REQUEST;
      message = exception.getMessage();
    } else if (exception instanceof NoResultException || exception.getCause() instanceof NoResultException) {
      status = Response.Status.NOT_FOUND;
    }
    logger.log(Level.SEVERE, exception.getMessage(), exception);
    return Response.status(status).
        entity(new ApiResponse<>(status.getStatusCode(), status.toString(), message))
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
