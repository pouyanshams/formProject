package com.example.form_builder.model;

import jakarta.persistence.*;

@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String label;

    @Enumerated(EnumType.STRING)
    private FieldType type;

    private String defaultValue;

    @ManyToOne
    private Form form;

    // Default constructor
    public Field() {}

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getLabel() { return label; }
    public FieldType getType() { return type; }
    public String getDefaultValue() { return defaultValue; }
    public Form getForm() { return form; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setLabel(String label) { this.label = label; }
    public void setType(FieldType type) { this.type = type; }
    public void setDefaultValue(String defaultValue) { this.defaultValue = defaultValue; }
    public void setForm(Form form) { this.form = form; }
}