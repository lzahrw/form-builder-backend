package com.example.formbuilder.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateFormDTO {
    private String name;
    private String submitMethod;
    private String submitUrl;
    private Set<CreateFieldDTO> fields;
}
