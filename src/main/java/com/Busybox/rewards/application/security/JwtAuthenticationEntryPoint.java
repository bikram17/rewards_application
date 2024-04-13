package com.Busybox.rewards.application.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.Busybox.rewards.application.controller.ResponseHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;





@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Create a custom response object with the error message
        String errorMessage = "Access Denied !! " + authException.getMessage();
        
        // Use ResponseHandler.generateResponse to create the response
        ResponseEntity<Object> responseEntity = ResponseHandler.generateResponse(errorMessage, HttpStatus.UNAUTHORIZED, "Unauthorized");

        // Set the HTTP response status and content type
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        // Write the JSON response to the HttpServletResponse
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseEntity.getBody()));
        response.getWriter().flush();
    }
}
