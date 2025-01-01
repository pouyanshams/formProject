// src/main/java/com/example/form_builder/dto/SubmitEndpointDTO.java
package com.example.form_builder.dto;

public class SubmitEndpointDTO {
    private Long id;
    private String url;
    private String method;

    // Default constructor
    public SubmitEndpointDTO() {}

    // Getters
    public Long getId() { return id; }
    public String getUrl() { return url; }
    public String getMethod() { return method; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setUrl(String url) { this.url = url; }
    public void setMethod(String method) { this.method = method; }
}