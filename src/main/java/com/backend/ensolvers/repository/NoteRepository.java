package com.backend.ensolvers.repository;

import com.backend.ensolvers.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    // Retrieves a list of notes based on their archive status.
    List<Note> findByArchived(boolean archived);

    // Retrieves a list of notes based on the ID of a specific category
    @Query("SELECT n FROM Note n JOIN n.categories c WHERE c.id = :categoryId")
    List<Note> findByCategory_Id(@Param("categoryId") Long categoryId);
}
