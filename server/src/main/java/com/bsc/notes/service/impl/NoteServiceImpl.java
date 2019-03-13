package com.bsc.notes.service.impl;

import com.bsc.notes.entity.NoteEntity;
import com.bsc.notes.exception.ApiError;
import com.bsc.notes.exception.ApiErrorException;
import com.bsc.notes.model.NoteForm;
import com.bsc.notes.model.NoteView;
import com.bsc.notes.model.converter.NoteConverter;
import com.bsc.notes.repository.NoteRepository;
import com.bsc.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteConverter noteConverter;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, NoteConverter noteConverter) {
        this.noteRepository = noteRepository;
        this.noteConverter = noteConverter;
    }

    @Override
    public List<NoteView>  getAllNotes() {
        List<NoteView> noteList;

        try {
            noteList = noteRepository.findAll()
                    .stream()
                    .map(noteConverter::convert)
                    .collect(Collectors.toList());

        } catch (DataAccessException exception) {
            throw new ApiErrorException(new ApiError(HttpStatus.NOT_FOUND,
                    "Couldn't find notes"));
        }

        return noteList;
    }

    @Override
    public NoteView getNoteById(long id) {
        final NoteEntity noteEntity = getNoteEntityById(id);

        return noteConverter.convert(noteEntity);
    }

    @Override
    public void createNote(NoteForm noteForm) {
        final NoteEntity noteEntity = new NoteEntity();

        noteEntity.setTitle(noteForm.getTitle());
        noteEntity.setFinished(noteForm.isFinished());
        noteEntity.setCreated(LocalDateTime.now());

        createOrUpdate(noteEntity);
    }

    @Override
    public void updateNote(NoteForm noteForm) {
        final NoteEntity noteEntity = getNoteEntityById(noteForm.getId());

        noteEntity.setFinished(noteForm.isFinished());
        noteEntity.setTitle(noteForm.getTitle());
        noteEntity.setLastUpdated(LocalDateTime.now());

        createOrUpdate(noteEntity);
    }

    @Override
    public void deleteNoteByNoteId(long id) {
        try {
            noteRepository.deleteById(id);
        } catch (DataAccessException exception) {
            throw new ApiErrorException(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred during the note deletion"));
        }
    }

    private void createOrUpdate(NoteEntity noteEntity) {
        try {
            noteRepository.save(noteEntity);
        } catch (DataAccessException exception) {
            throw new ApiErrorException(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred during the save operation"));
        }
    }

    private NoteEntity getNoteEntityById(long id) {
        NoteEntity noteEntity;
        try {
            noteEntity = noteRepository.findById(id).orElseThrow(() ->
                    new ApiErrorException(new ApiError(HttpStatus.NOT_FOUND,
                            "No note matching the Id was found")));
        } catch (DataAccessException exception) {
            throw new ApiErrorException(new ApiError(HttpStatus.NOT_FOUND,
                    "No note matching the Id was found"));
        }

        return noteEntity;
    }
}
