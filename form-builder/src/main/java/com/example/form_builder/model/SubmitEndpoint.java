// src/main/java/com/example/form_builder/model/SubmitEndpoint.java
package com.example.form_builder.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
public class SubmitEndpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String method;

    // Default constructor
    public SubmitEndpoint() {}

    // Getters
    public Long getId() { return id; }
    public String getUrl() { return url; }
    public String getMethod() { return method; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setUrl(String url) { this.url = url; }
    public void setMethod(String method) { this.method = method; }
}