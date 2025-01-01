// src/main/java/com/example/formbuilder/controller/FormController.java

package com.example.formbuilder.controller;

import com.example.formbuilder.dto.*;
import com.example.formbuilder.service.FormService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/forms")
public class FormController {

    @Autowired
    private FormService formService;

    // Get a list of all forms
    @GetMapping("/")
    public ResponseEntity<List<FormDTO>> getAllForms() {
        List<FormDTO> forms = formService.getAllForms();
        return new ResponseEntity<>(forms, HttpStatus.OK);
    }

    // Create a new form
    @PostMapping("/")
    public ResponseEntity<FormDTO> createForm(@Valid @RequestBody CreateFormDTO createFormDTO) {
        FormDTO formDTO = formService.createForm(createFormDTO);
        return new ResponseEntity<>(formDTO, HttpStatus.CREATED);
    }

    // Get a form with a specific ID
    @GetMapping("/{id}")
    public ResponseEntity<FormDTO> getFormById(@PathVariable Long id) {
        FormDTO formDTO = formService.getFormById(id);
        return new ResponseEntity<>(formDTO, HttpStatus.OK);
    }

    // Update information for a specific form
    @PutMapping("/{id}")
    public ResponseEntity<FormDTO> updateForm(@PathVariable Long id, @Valid @RequestBody UpdateFormDTO updateFormDTO) {
        FormDTO formDTO = formService.updateForm(id, updateFormDTO);
        return new ResponseEntity<>(formDTO, HttpStatus.OK);
    }

    // Delete a form
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable Long id) {
        formService.deleteForm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // View fields for a form
    @GetMapping("/{id}/fields")
    public ResponseEntity<Set<FieldDTO>> getFieldsByFormId(@PathVariable Long id) {
        Set<FieldDTO> fields = formService.getFieldsByFormId(id);
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    // Update form fields
    @PutMapping("/{id}/fields")
    public ResponseEntity<FormDTO> updateFields(@PathVariable Long id, @Valid @RequestBody Set<CreateFieldDTO> fields) {
        FormDTO formDTO = formService.updateFields(id, fields);
        return new ResponseEntity<>(formDTO, HttpStatus.OK);
    }

    // Change the publishing status of a form
    @PostMapping("/{id}/publish")
    public ResponseEntity<FormDTO> togglePublishStatus(@PathVariable Long id) {
        FormDTO formDTO = formService.togglePublishStatus(id);
        return new ResponseEntity<>(formDTO, HttpStatus.OK);
    }

    // Get a list of published forms
    @GetMapping("/published")
    public ResponseEntity<List<FormDTO>> getPublishedForms() {
        List<FormDTO> forms = formService.getPublishedForms();
        return new ResponseEntity<>(forms, HttpStatus.OK);
    }
}
