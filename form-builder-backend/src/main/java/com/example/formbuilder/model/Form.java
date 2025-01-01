// src/main/java/com/example/formbuilder/model/Form.java

package com.example.formbuilder.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "forms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean isPublished = false;

    @Builder.Default // Ensures fields is initialized when using @Builder
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Field> fields = new HashSet<>();

    private String submitMethod;

    private String submitUrl;

    // Helper methods to manage bidirectional relationship
    public void addField(Field field) {
        fields.add(field);
        field.setForm(this);
    }

    public void removeField(Field field) {
        fields.remove(field);
        field.setForm(null);
    }
}
