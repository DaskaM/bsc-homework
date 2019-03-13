package com.bsc.notes.service;

import com.bsc.notes.exception.ApiErrorException;
import com.bsc.notes.model.NoteForm;
import com.bsc.notes.model.NoteView;

import java.util.List;

/**
 * This service handles logic behind Notes
 * */
public interface NoteService {

    /**
     * Retrieves all notes from the database and converts it to a model that's exposed through the API
     *
     * @return the collection of notes {@link List<NoteView>}
     * @throws ApiErrorException if there is an issue
     */
    List<NoteView> getAllNotes();

    /**
     * Retrieves a single note {@link NoteView} from the database by the given id and converts it to a model
     * that's exposed through the API
     *
     * @param id the Id of the note we want to retrieve
     * @throws ApiErrorException if there is an issue
     */
    NoteView getNoteById(long id);

    /**
     * Creates a new note from the noteForm {@link NoteForm} object
     * @throws ApiErrorException if there is an issue
     */
    void createNote(NoteForm noteForm);

    /**
     * Updates an existing note from the noteForm {@link NoteForm} object
     * @throws ApiErrorException if there is an issue
     */
    void updateNote(NoteForm noteForm);

    /**
     * Deletes single note with given id
     *
     * @param id the Id of the note we want to delete
     * @throws ApiErrorException if there is an issue
     */
    void deleteNoteByNoteId(long id);
}
