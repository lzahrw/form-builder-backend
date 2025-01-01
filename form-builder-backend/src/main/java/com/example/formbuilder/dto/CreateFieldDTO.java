

package com.example.formbuilder.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateFieldDTO {
    private String fieldIdentifier;
    private String name;
    private String label;
    private String type;
    private String defaultValue;
}
