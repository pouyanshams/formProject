package com.example.form_builder.repository;

import com.example.form_builder.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findByFormId(Long formId);
}