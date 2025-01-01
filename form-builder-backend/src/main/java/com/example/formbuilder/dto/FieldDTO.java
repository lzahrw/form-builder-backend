// src/main/java/com/example/formbuilder/dto/FieldDTO.java

package com.example.formbuilder.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldDTO {
    private Long id;
    private String fieldIdentifier;
    private String name;
    private String label;
    private String type;
    private String defaultValue;
}
