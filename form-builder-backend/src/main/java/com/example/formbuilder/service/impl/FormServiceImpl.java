package com.example.formbuilder.service.impl;

import com.example.formbuilder.dto.*;
import com.example.formbuilder.exception.ResourceNotFoundException;
import com.example.formbuilder.model.Field;
import com.example.formbuilder.model.Form;
import com.example.formbuilder.repository.FormRepository;
import com.example.formbuilder.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormRepository formRepository;

    @Override
    public List<FormDTO> getAllForms() {
        List<Form> forms = formRepository.findAll();
        return forms.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public FormDTO createForm(CreateFormDTO createFormDTO) {
        Form form = Form.builder()
                .name(createFormDTO.getName())
                .submitMethod(createFormDTO.getSubmitMethod())
                .submitUrl(createFormDTO.getSubmitUrl())
                .isPublished(false)
                .build();

        if (createFormDTO.getFields() != null) {
            for (CreateFieldDTO fieldDTO : createFormDTO.getFields()) {
                Field field = Field.builder()
                        .fieldIdentifier(fieldDTO.getFieldIdentifier())
                        .name(fieldDTO.getName())
                        .label(fieldDTO.getLabel())
                        .type(fieldDTO.getType())
                        .defaultValue(fieldDTO.getDefaultValue())
                        .form(form)
                        .build();
                form.addField(field);
            }
        }

        Form savedForm = formRepository.save(form);
        return convertToDTO(savedForm);
    }

    @Override
    public FormDTO getFormById(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + id));
        return convertToDTO(form);
    }

    @Override
    public FormDTO updateForm(Long id, UpdateFormDTO updateFormDTO) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + id));

        if (updateFormDTO.getName() != null) {
            form.setName(updateFormDTO.getName());
        }
        if (updateFormDTO.getSubmitMethod() != null) {
            form.setSubmitMethod(updateFormDTO.getSubmitMethod());
        }
        if (updateFormDTO.getSubmitUrl() != null) {
            form.setSubmitUrl(updateFormDTO.getSubmitUrl());
        }
        if (updateFormDTO.getIsPublished() != null) {
            form.setIsPublished(updateFormDTO.getIsPublished());
        }

        Form updatedForm = formRepository.save(form);
        return convertToDTO(updatedForm);
    }

    @Override
    public void deleteForm(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + id));
        formRepository.delete(form);
    }

    @Override
    public Set<FieldDTO> getFieldsByFormId(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + id));
        return form.getFields().stream()
                .map(this::convertFieldToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public FormDTO updateFields(Long id, Set<CreateFieldDTO> fieldsDTO) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + id));

        // Clear existing fields
        form.getFields().clear();

        // Add new fields
        for (CreateFieldDTO fieldDTO : fieldsDTO) {
            Field field = Field.builder()
                    .fieldIdentifier(fieldDTO.getFieldIdentifier())
                    .name(fieldDTO.getName())
                    .label(fieldDTO.getLabel())
                    .type(fieldDTO.getType())
                    .defaultValue(fieldDTO.getDefaultValue())
                    .form(form)
                    .build();
            form.addField(field);
        }

        Form updatedForm = formRepository.save(form);
        return convertToDTO(updatedForm);
    }

    @Override
    public FormDTO togglePublishStatus(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + id));
        form.setIsPublished(!form.getIsPublished());
        Form updatedForm = formRepository.save(form);
        return convertToDTO(updatedForm);
    }

    @Override
    public List<FormDTO> getPublishedForms() {
        List<Form> forms = formRepository.findByIsPublishedTrue();
        return forms.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    private FormDTO convertToDTO(Form form) {
        return FormDTO.builder()
                .id(form.getId())
                .name(form.getName())
                .isPublished(form.getIsPublished())
                .submitMethod(form.getSubmitMethod())
                .submitUrl(form.getSubmitUrl())
                .fields(form.getFields().stream()
                        .map(this::convertFieldToDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    private FieldDTO convertFieldToDTO(Field field) {
        return FieldDTO.builder()
                .id(field.getId())
                .fieldIdentifier(field.getFieldIdentifier())
                .name(field.getName())
                .label(field.getLabel())
                .type(field.getType())
                .defaultValue(field.getDefaultValue())
                .build();
    }
}
