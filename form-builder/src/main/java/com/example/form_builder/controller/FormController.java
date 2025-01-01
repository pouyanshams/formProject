package com.example.form_builder.controller;

import com.example.form_builder.dto.FormDTO;
import com.example.form_builder.dto.FieldDTO;
import com.example.form_builder.service.FormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormController {
    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping
    public List<FormDTO> getAllForms() {
        System.out.println("Getting all forms");
        return formService.getAllForms();
    }

    @PostMapping
    public FormDTO createForm(@RequestBody FormDTO formDTO) {
        System.out.println("Received form creation request: " + formDTO);
        return formService.createForm(formDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormDTO> getForm(@PathVariable Long id) {
        System.out.println("Getting form with id: " + id);
        return formService.getForm(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormDTO> updateForm(@PathVariable Long id, @RequestBody FormDTO formDTO) {
        System.out.println("Updating form with id: " + id);
        return ResponseEntity.ok(formService.updateForm(id, formDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable Long id) {
        System.out.println("Deleting form with id: " + id);
        formService.deleteForm(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/fields")
    public ResponseEntity<List<FieldDTO>> getFormFields(@PathVariable Long id) {
        System.out.println("Getting fields for form with id: " + id);
        return ResponseEntity.ok(formService.getFormFields(id));
    }

    @PutMapping("/{id}/fields")
    public ResponseEntity<List<FieldDTO>> updateFormFields(@PathVariable Long id, @RequestBody List<FieldDTO> fields) {
        System.out.println("Updating fields for form with id: " + id);
        return ResponseEntity.ok(formService.updateFormFields(id, fields));
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<FormDTO> togglePublishStatus(@PathVariable Long id) {
        System.out.println("Toggling publish status for form with id: " + id);
        return ResponseEntity.ok(formService.togglePublishStatus(id));
    }

    @GetMapping("/published")
    public List<FormDTO> getPublishedForms() {
        System.out.println("Getting all published forms");
        return formService.getPublishedForms();
    }
}