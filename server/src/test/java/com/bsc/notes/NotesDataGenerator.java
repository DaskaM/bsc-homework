package com.bsc.notes;

import com.bsc.notes.entity.NoteEntity;
import com.bsc.notes.model.NoteForm;
import com.bsc.notes.model.NoteView;

import java.time.LocalDateTime;

public class NotesDataGenerator {

    public static NoteForm getNoteForm() {
        final NoteForm noteForm = new NoteForm();
        noteForm.setFinished(true);
        noteForm.setTitle("Some Faked Title");

        return noteForm;
    }

    public static NoteForm getNoteFormWithId() {
        final NoteForm noteForm = new NoteForm();
        noteForm.setId(55L);
        noteForm.setFinished(true);
        noteForm.setTitle("Some Faked Title");

        return noteForm;
    }

    public static NoteEntity getNoteEntity1() {
        final NoteEntity noteEntity = new NoteEntity();
        noteEntity.setId(2L);
        noteEntity.setTitle("Some very faked title");
        noteEntity.setFinished(true);
        noteEntity.setCreated(LocalDateTime.of( 2018, 11, 20, 15, 20));
        noteEntity.setLastUpdated(LocalDateTime.of(2015, 4, 3, 10, 33));

        return noteEntity;
    }

    public static NoteView getNoteView1() {
        final NoteView noteView = new NoteView();
        noteView.setId(2L);
        noteView.setTitle("Some very faked title");
        noteView.setFinished(true);
        noteView.setCreated(LocalDateTime.of( 2018, 11, 20, 15, 20));
        noteView.setLastUpdated(LocalDateTime.of(2015, 4, 3, 10, 33));

        return noteView;
    }

    public static NoteView getNoteView2() {
        final NoteView noteView = new NoteView();
        noteView.setId(15L);
        noteView.setTitle("Some very faked title 2");
        noteView.setFinished(false);
        noteView.setCreated(LocalDateTime.of( 2018, 11, 20, 15, 20));
        noteView.setLastUpdated(LocalDateTime.of(2015, 4, 3, 10, 33));

        return noteView;
    }

}
