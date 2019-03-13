package com.bsc.notes.model.converter;

import com.bsc.notes.NotesDataGenerator;
import com.bsc.notes.entity.NoteEntity;
import com.bsc.notes.model.NoteView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class NoteConverterTest {

    private NoteConverter noteConverter;

    @Before
    public void setUp() throws Exception {
        noteConverter = new NoteConverter(new ModelMapper());
    }

    @Test
    public void test_EntityToView() {
        final NoteEntity noteEntity = NotesDataGenerator.getNoteEntity1();
        final NoteView expectedNoteView = NotesDataGenerator.getNoteView1();

        final NoteView resultNoteView = noteConverter.convert(noteEntity);

        assertThat(resultNoteView, sameBeanAs(expectedNoteView));
    }

}
