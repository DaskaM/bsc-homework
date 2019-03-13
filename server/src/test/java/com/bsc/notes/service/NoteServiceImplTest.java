package com.bsc.notes.service;

import com.bsc.notes.NotesDataGenerator;
import com.bsc.notes.entity.NoteEntity;
import com.bsc.notes.exception.ApiErrorException;
import com.bsc.notes.model.NoteForm;
import com.bsc.notes.model.NoteView;
import com.bsc.notes.model.converter.NoteConverter;
import com.bsc.notes.repository.NoteRepository;
import com.bsc.notes.service.impl.NoteServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class NoteServiceImplTest {

    private NoteService noteService;

    @MockBean
    private NoteRepository noteRepository;

    @Before
    public void setUp() throws Exception {
        final NoteConverter noteConverter = new NoteConverter(new ModelMapper());
        noteService = new NoteServiceImpl(noteRepository, noteConverter);
    }

    @Test
    public void test_getAllNotes() {
        final List<NoteEntity> expectedNoteEntityList = Arrays.asList(NotesDataGenerator.getNoteEntity1());

        when(noteRepository.findAll()).thenReturn(expectedNoteEntityList);

        final List<NoteView> resultNoteViews = noteService.getAllNotes();

        verify(noteRepository, times(1)).findAll();
        assertThat(expectedNoteEntityList, sameBeanAs(resultNoteViews));
    }

    @Test(expected = ApiErrorException.class)
    public void test_getAllNotesException() {
        when(noteRepository.findAll()).thenThrow(new TestDataAccessException("something went wrong"));
        noteService.getAllNotes();
    }

    @Test
    public void test_getNoteById() {
        final NoteView noteView = NotesDataGenerator.getNoteView1();

        when(noteRepository.findById(noteView.getId())).thenReturn(Optional.of(NotesDataGenerator.getNoteEntity1()));

        final NoteView expectedNoteEntity = noteService.getNoteById(noteView.getId());

        verify(noteRepository, times(1)).findById(noteView.getId());

        final ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(noteRepository).findById(captor.capture());

        assertThat(captor.getValue(), equalTo(noteView.getId()));

        assertThat(noteView, sameBeanAs(expectedNoteEntity));
    }

    @Test(expected = ApiErrorException.class)
    public void test_getNoteByIdException() {
        when(noteRepository.findById(anyLong())).thenThrow(new TestDataAccessException("something went wrong"));
        noteService.getNoteById(1L);
    }

    @Test
    public void test_createNote() {
        final NoteForm noteForm = NotesDataGenerator.getNoteForm();

        noteService.createNote(noteForm);

        verify(noteRepository, times(1)).save(any());

        final ArgumentCaptor<NoteEntity> captor = ArgumentCaptor.forClass(NoteEntity.class);
        verify(noteRepository).save(captor.capture());

        final NoteEntity entityToBeSaved = captor.getValue();
        assertThat(entityToBeSaved.isFinished(), equalTo(noteForm.isFinished()));
        assertThat(entityToBeSaved.getTitle(), equalTo(noteForm.getTitle()));
        assertNotNull(entityToBeSaved.getCreated());
        assertNull(entityToBeSaved.getLastUpdated());
    }

    @Test(expected = ApiErrorException.class)
    public void test_createNoteException() {
        when(noteRepository.save(any())).thenThrow(new TestDataAccessException("something went wrong"));
        noteService.createNote(NotesDataGenerator.getNoteForm());
    }

    @Test
    public void test_updateNote() {
        final NoteForm noteForm = NotesDataGenerator.getNoteForm();
        noteForm.setId(9L);

        final NoteEntity noteEntity = NotesDataGenerator.getNoteEntity1();

        when(noteRepository.findById(noteForm.getId())).thenReturn(Optional.of(noteEntity));

        noteService.updateNote(noteForm);

        verify(noteRepository, times(1)).save(noteEntity);

        final ArgumentCaptor<NoteEntity> captor = ArgumentCaptor.forClass(NoteEntity.class);
        verify(noteRepository).save(captor.capture());

        final NoteEntity entityToBeSaved = captor.getValue();
        assertThat(entityToBeSaved.isFinished(), equalTo(noteForm.isFinished()));
        assertThat(entityToBeSaved.getTitle(), equalTo(noteForm.getTitle()));
        assertNotNull(entityToBeSaved.getCreated());
        assertNotNull(entityToBeSaved.getLastUpdated());
    }

    @Test(expected = ApiErrorException.class)
    public void test_updateNoteException() {
        when(noteRepository.save(any())).thenThrow(new TestDataAccessException("something went wrong"));
        noteService.updateNote(NotesDataGenerator.getNoteForm());
    }

    @Test
    public void test_deleteNote() {
        final NoteForm noteForm = NotesDataGenerator.getNoteForm();

        noteService.deleteNoteByNoteId(noteForm.getId());
        verify(noteRepository, times(1)).deleteById(noteForm.getId());

        verify(noteRepository).deleteById(noteForm.getId());
    }

    @Test(expected = ApiErrorException.class)
    public void test_deleteNoteException() {
        doThrow(new TestDataAccessException("something went wrong")).when(noteRepository).deleteById(anyLong());
        noteService.updateNote(NotesDataGenerator.getNoteForm());
    }

    private class TestDataAccessException extends DataAccessException {

        TestDataAccessException(String msg) {
            super(msg);
        }
    }
}
