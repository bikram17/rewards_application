package com.Busybox.rewards.application.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "api_logs")
public class Rewards_Application_API_Logs {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "request_url", length = 255)
    private String requestUrl;

    @Column(name = "request_method", length = 10)
    private String requestMethod;

    @Column(name = "request_parameters", length = 1024)
    private String requestParameters;

    @Column(name = "request_headers", length = 1024)
    private String requestHeaders;

    @Column(name = "response_status")
    private Integer responseStatus;

    @Column(name = "response_headers", length = 1024)
    private String responseHeaders;

    @Column(name = "timestamp", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime timestamp;
    
    @Column(name = "response_message", length = 255)
    private String response_message;

    @Column(name = "Json_response_message", length = 255)
    private String jsonmessage;

    @Column(name = "PayLoad_response", length = 255)
    private String payloadmessage;

    @Column(name = "request_body", length = 4096) // Adjust the length as needed
    private String requestBody;

    @Column(name = "response_body", length = 4096) // Adjust the length as needed
    private String responseBody;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getRequestParameters() {
		return requestParameters;
	}

	public void setRequestParameters(String requestParameters) {
		this.requestParameters = requestParameters;
	}

	public String getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(String requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public Integer getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(Integer responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(String responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getResponse_message() {
		return response_message;
	}

	public void setResponse_message(String response_message) {
		this.response_message = response_message;
	}

	public String getJsonmessage() {
		return jsonmessage;
	}

	public void setJsonmessage(String jsonmessage) {
		this.jsonmessage = jsonmessage;
	}

	public String getPayloadmessage() {
		return payloadmessage;
	}

	public void setPayloadmessage(String payloadmessage) {
		this.payloadmessage = payloadmessage;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
    
    
    
}
