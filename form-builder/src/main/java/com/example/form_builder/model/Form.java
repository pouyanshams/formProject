package com.example.form_builder.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean published;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<Field> fields;

    @OneToOne(cascade = CascadeType.ALL)
    private SubmitEndpoint submitEndpoint;

    // Default constructor
    public Form() {}

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isPublished() {
        return published;
    }

    public List<Field> getFields() {
        return fields;
    }

    public SubmitEndpoint getSubmitEndpoint() {
        return submitEndpoint;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void setSubmitEndpoint(SubmitEndpoint submitEndpoint) {
        this.submitEndpoint = submitEndpoint;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Form{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", published=" + published +
                ", fields=" + fields +
                ", submitEndpoint=" + submitEndpoint +
                '}';
    }
}