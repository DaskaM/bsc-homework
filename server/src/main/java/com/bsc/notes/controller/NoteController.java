package com.bsc.notes.controller;

import com.bsc.notes.model.NoteForm;
import com.bsc.notes.model.NoteView;
import com.bsc.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller {@link NoteController} handles HTTP calls for the /notes endpoint
 */
@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * Accepts requests from /notes of type GET
     * @return collection of {@link NoteView} wrapped in a response {@link ResponseEntity} object
     */
    @GetMapping
    public ResponseEntity<List<NoteView>> getNotes() {
        final List<NoteView> notesList = noteService.getAllNotes();

        return new ResponseEntity<>(notesList, HttpStatus.OK);
    }

    /**
     * Accepts requests from /notes/{id} of type GET
     * @param id the id of the note we want to retrieve the details for
     * @return single note {@link NoteView} wrapped in a response {@link ResponseEntity} object
     */
    @GetMapping("/{id}")
    public ResponseEntity<NoteView> getNoteById(@PathVariable("id") long id) {
        final NoteView noteView = noteService.getNoteById(id);

        return new ResponseEntity<>(noteView, HttpStatus.OK);
    }

    /**
     * Accepts requests from /notes of type POST
     * @param noteForm {@link NoteForm} the data necessary for the note creation.
     * @return response {@link ResponseEntity} object
     */
    @PostMapping
    public ResponseEntity createNote(@RequestBody NoteForm noteForm) {
        noteService.createNote(noteForm);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * Accepts requests from /notes of type PUT
     * @param noteForm {@link NoteForm} the data necessary for the note update.
     * @return response {@link ResponseEntity} object
     */
    @PutMapping
    public ResponseEntity updateNote(@RequestBody NoteForm noteForm) {
        noteService.updateNote(noteForm);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * Accepts requests from /notes of type DELETE
     * @param id the id of the note we want to delete.
     * @return response {@link ResponseEntity} object
     */
    @DeleteMapping("{id}")
    public ResponseEntity deleteNote(@PathVariable("id") long id) {
        noteService.deleteNoteByNoteId(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
