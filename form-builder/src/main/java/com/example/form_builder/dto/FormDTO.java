package com.example.form_builder.dto;

import java.util.List;

public class FormDTO {
    private Long id;
    private String name;
    private boolean published;
    private List<FieldDTO> fields;
    private SubmitEndpointDTO submitEndpoint;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isPublished() { return published; }
    public void setPublished(boolean published) { this.published = published; }

    public List<FieldDTO> getFields() { return fields; }
    public void setFields(List<FieldDTO> fields) { this.fields = fields; }

    public SubmitEndpointDTO getSubmitEndpoint() { return submitEndpoint; }
    public void setSubmitEndpoint(SubmitEndpointDTO submitEndpoint) { this.submitEndpoint = submitEndpoint; }
}