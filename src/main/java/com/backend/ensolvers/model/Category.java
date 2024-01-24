package com.backend.ensolvers.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
    private List<Note> notes;

    public Category() {
        this.notes = new ArrayList<>();
    }

}
