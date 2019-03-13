package com.bsc.notes.repository;

import com.bsc.notes.entity.NoteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<NoteEntity, Long> {

    /**
     * Retrieves all notes from the database
     * @return list of notes {@link List<NoteEntity>}
     */
    List<NoteEntity> findAll();
}
