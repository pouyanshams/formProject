package com.example.form_builder.dto;

import com.example.form_builder.model.FieldType;

public class FieldDTO {
    private Long id;
    private String name;
    private String label;
    private FieldType type;
    private String defaultValue;

    // Default constructor
    public FieldDTO() {}

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getLabel() { return label; }
    public FieldType getType() { return type; }
    public String getDefaultValue() { return defaultValue; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setLabel(String label) { this.label = label; }
    public void setType(FieldType type) { this.type = type; }
    public void setDefaultValue(String defaultValue) { this.defaultValue = defaultValue; }
}