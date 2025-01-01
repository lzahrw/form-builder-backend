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


    @GetMapping("/")
    public ResponseEntity<List<FormDTO>> getAllForms() {
        List<FormDTO> forms = formService.getAllForms();
        return new ResponseEntity<>(forms, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<FormDTO> createForm(@Valid @RequestBody CreateFormDTO createFormDTO) {
        FormDTO formDTO = formService.createForm(createFormDTO);
        return new ResponseEntity<>(formDTO, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<FormDTO> getFormById(@PathVariable Long id) {
        FormDTO formDTO = formService.getFormById(id);
        return new ResponseEntity<>(formDTO, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<FormDTO> updateForm(@PathVariable Long id, @Valid @RequestBody UpdateFormDTO updateFormDTO) {
        FormDTO formDTO = formService.updateForm(id, updateFormDTO);
        return new ResponseEntity<>(formDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable Long id) {
        formService.deleteForm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{id}/fields")
    public ResponseEntity<Set<FieldDTO>> getFieldsByFormId(@PathVariable Long id) {
        Set<FieldDTO> fields = formService.getFieldsByFormId(id);
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }


    @PutMapping("/{id}/fields")
    public ResponseEntity<FormDTO> updateFields(@PathVariable Long id, @Valid @RequestBody Set<CreateFieldDTO> fields) {
        FormDTO formDTO = formService.updateFields(id, fields);
        return new ResponseEntity<>(formDTO, HttpStatus.OK);
    }


    @PostMapping("/{id}/publish")
    public ResponseEntity<FormDTO> togglePublishStatus(@PathVariable Long id) {
        FormDTO formDTO = formService.togglePublishStatus(id);
        return new ResponseEntity<>(formDTO, HttpStatus.OK);
    }


    @GetMapping("/published")
    public ResponseEntity<List<FormDTO>> getPublishedForms() {
        List<FormDTO> forms = formService.getPublishedForms();
        return new ResponseEntity<>(forms, HttpStatus.OK);
    }
}
