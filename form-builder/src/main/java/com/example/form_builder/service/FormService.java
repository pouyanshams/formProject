package com.example.form_builder.service;

import com.example.form_builder.dto.FieldDTO;
import com.example.form_builder.dto.FormDTO;
import com.example.form_builder.dto.SubmitEndpointDTO;
import com.example.form_builder.model.Field;
import com.example.form_builder.model.Form;
import com.example.form_builder.model.SubmitEndpoint;
import com.example.form_builder.repository.FieldRepository;
import com.example.form_builder.repository.FormRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormService {
    private final FormRepository formRepository;
    private final FieldRepository fieldRepository;

    public FormService(FormRepository formRepository, FieldRepository fieldRepository) {
        this.formRepository = formRepository;
        this.fieldRepository = fieldRepository;
    }

    public List<FormDTO> getAllForms() {
        return formRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FormDTO createForm(FormDTO formDTO) {
        Form form = convertToEntity(formDTO);
        form = formRepository.save(form);
        return convertToDTO(form);
    }

    public Optional<FormDTO> getForm(Long id) {
        return formRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Transactional
    public FormDTO updateForm(Long id, FormDTO formDTO) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Form not found"));

        form.setName(formDTO.getName());
        if (formDTO.getSubmitEndpoint() != null) {
            SubmitEndpoint submitEndpoint = new SubmitEndpoint();
            submitEndpoint.setUrl(formDTO.getSubmitEndpoint().getUrl());
            submitEndpoint.setMethod(formDTO.getSubmitEndpoint().getMethod());
            form.setSubmitEndpoint(submitEndpoint);
        }

        return convertToDTO(formRepository.save(form));
    }

    public void deleteForm(Long id) {
        formRepository.deleteById(id);
    }

    public List<FieldDTO> getFormFields(Long formId) {
        return fieldRepository.findByFormId(formId).stream()
                .map(this::convertToFieldDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FieldDTO> updateFormFields(Long formId, List<FieldDTO> fieldDTOs) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new EntityNotFoundException("Form not found"));

        fieldRepository.deleteAll(form.getFields());

        List<Field> fields = fieldDTOs.stream()
                .map(dto -> {
                    Field field = convertToFieldEntity(dto);
                    field.setForm(form);
                    return field;
                })
                .collect(Collectors.toList());

        form.setFields(fields);
        formRepository.save(form);

        return fields.stream()
                .map(this::convertToFieldDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public FormDTO togglePublishStatus(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Form not found"));

        form.setPublished(!form.isPublished());
        return convertToDTO(formRepository.save(form));
    }

    public List<FormDTO> getPublishedForms() {
        return formRepository.findByPublished(true).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private FormDTO convertToDTO(Form form) {
        FormDTO dto = new FormDTO();
        dto.setId(form.getId());
        dto.setName(form.getName());
        dto.setPublished(form.isPublished());
        dto.setFields(form.getFields().stream()
                .map(this::convertToFieldDTO)
                .collect(Collectors.toList()));

        if (form.getSubmitEndpoint() != null) {
            SubmitEndpointDTO submitEndpointDTO = new SubmitEndpointDTO();
            submitEndpointDTO.setId(form.getSubmitEndpoint().getId());
            submitEndpointDTO.setUrl(form.getSubmitEndpoint().getUrl());
            submitEndpointDTO.setMethod(form.getSubmitEndpoint().getMethod());
            dto.setSubmitEndpoint(submitEndpointDTO);
        }

        return dto;
    }

    private Form convertToEntity(FormDTO dto) {
        Form form = new Form();
        form.setName(dto.getName());
        form.setPublished(dto.isPublished());

        if (dto.getSubmitEndpoint() != null) {
            SubmitEndpoint submitEndpoint = new SubmitEndpoint();
            submitEndpoint.setUrl(dto.getSubmitEndpoint().getUrl());
            submitEndpoint.setMethod(dto.getSubmitEndpoint().getMethod());
            form.setSubmitEndpoint(submitEndpoint);
        }

        if (dto.getFields() != null) {
            form.setFields(dto.getFields().stream()
                    .map(fieldDTO -> {
                        Field field = convertToFieldEntity(fieldDTO);
                        field.setForm(form);
                        return field;
                    })
                    .collect(Collectors.toList()));
        }

        return form;
    }

    private FieldDTO convertToFieldDTO(Field field) {
        FieldDTO dto = new FieldDTO();
        dto.setId(field.getId());
        dto.setName(field.getName());
        dto.setLabel(field.getLabel());
        dto.setType(field.getType());
        dto.setDefaultValue(field.getDefaultValue());
        return dto;
    }

    private Field convertToFieldEntity(FieldDTO dto) {
        Field field = new Field();
        field.setName(dto.getName());
        field.setLabel(dto.getLabel());
        field.setType(dto.getType());
        field.setDefaultValue(dto.getDefaultValue());
        return field;
    }
}