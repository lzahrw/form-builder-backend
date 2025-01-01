package com.example.formbuilder.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormDTO {
    private Long id;
    private String name;
    private Boolean isPublished;
    private Set<FieldDTO> fields;
    private String submitMethod;
    private String submitUrl;
}
