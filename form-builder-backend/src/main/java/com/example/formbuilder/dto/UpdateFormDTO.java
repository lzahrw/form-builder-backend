// src/main/java/com/example/formbuilder/dto/UpdateFormDTO.java

package com.example.formbuilder.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateFormDTO {
    private String name;
    private String submitMethod;
    private String submitUrl;
    private Boolean isPublished;
}
