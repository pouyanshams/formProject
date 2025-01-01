package com.example.form_builder.repository;

import com.example.form_builder.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRepository extends JpaRepository<Form, Long> {
    List<Form> findByPublished(boolean published);
}