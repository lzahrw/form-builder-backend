package com.example.formbuilder.service;

import com.example.formbuilder.dto.*;

import java.util.List;
import java.util.Set;

public interface FormService {
    List<FormDTO> getAllForms();
    FormDTO createForm(CreateFormDTO createFormDTO);
    FormDTO getFormById(Long id);
    FormDTO updateForm(Long id, UpdateFormDTO updateFormDTO);
    void deleteForm(Long id);
    Set<FieldDTO> getFieldsByFormId(Long id);
    FormDTO updateFields(Long id, Set<CreateFieldDTO> fields);
    FormDTO togglePublishStatus(Long id);
    List<FormDTO> getPublishedForms();
}
