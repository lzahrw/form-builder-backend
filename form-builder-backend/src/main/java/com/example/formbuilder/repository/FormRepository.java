// src/main/java/com/example/formbuilder/repository/FormRepository.java

package com.example.formbuilder.repository;

import com.example.formbuilder.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    List<Form> findByIsPublishedTrue();
}
