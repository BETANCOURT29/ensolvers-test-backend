package com.backend.ensolvers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name="note")
public class  Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    private String description;

    @NotNull (message = "Note file status cannot be null")
    private Boolean archived;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "note_category",
            joinColumns = @JoinColumn(
                    name = "note_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id")
    )
    @JsonIgnore
    private List<Category> categories;

    public Boolean isArchived() {
        return archived;
    }

    public Note() {
        this.categories = new ArrayList<>();
    }

}
