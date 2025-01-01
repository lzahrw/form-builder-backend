// src/main/java/com/example/formbuilder/model/Field.java

package com.example.formbuilder.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fields")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fieldIdentifier; // Unique across the form

    private String name;

    private String label;

    private String type; // e.g., Text, Number, Boolean, Date

    private String defaultValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    private Form form;
}
