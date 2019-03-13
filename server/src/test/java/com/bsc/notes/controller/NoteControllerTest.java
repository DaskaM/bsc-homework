package com.bsc.notes.controller;

import com.bsc.notes.NotesDataGenerator;
import com.bsc.notes.exception.ApiError;
import com.bsc.notes.exception.ApiErrorException;
import com.bsc.notes.model.NoteForm;
import com.bsc.notes.model.NoteView;
import com.bsc.notes.service.NoteService;
import com.google.gson.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = NoteController.class, secure = false)
public class NoteControllerTest {

    private static final String EMPTY_STRING = "";

    @Autowired
    private MockMvc mockMvc;

    private GsonBuilder gsonBuilder;

    @MockBean
    private NoteService noteService;

    @Before
    public void setUp() throws Exception {
        gsonBuilder = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
    }

    @Test
    public void test_getAllNotes() throws Exception {
        final List<NoteView> expectedNoteViews =
                Arrays.asList(NotesDataGenerator.getNoteView1(), NotesDataGenerator.getNoteView2());

        when(noteService.getAllNotes()).thenReturn(expectedNoteViews);

        final MvcResult result = mockMvc.perform(get("/notes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedNoteViews.size())))
                .andReturn();

        final String jsonExpectedNoteViews = gsonBuilder.create().toJson(expectedNoteViews);

        assertThat(jsonExpectedNoteViews, equalTo(result.getResponse().getContentAsString()));
    }

    @Test
    public void test_getNoteById() throws Exception {
        final NoteView expectedNoteView = NotesDataGenerator.getNoteView1();
        when(noteService.getNoteById(expectedNoteView.getId())).thenReturn(expectedNoteView);

        final MvcResult result = mockMvc.perform(get("/notes/" + expectedNoteView.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        final String jsonExpectedNoteViews = gsonBuilder.create().toJson(expectedNoteView);

        assertThat(jsonExpectedNoteViews, equalTo(result.getResponse().getContentAsString()));
    }

    @Test
    public void test_getNoteByIdNotFound() throws Exception {
        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Not found");
        final String expectedResult = gsonBuilder.create().toJson(apiError);

        when(noteService.getNoteById(anyLong())).thenThrow(new ApiErrorException(apiError));

        final MvcResult result = mockMvc.perform(get("/notes/" + anyLong())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        assertThat(expectedResult, equalTo(result.getResponse().getContentAsString()));
    }

    @Test
    public void test_createNote() throws Exception {
        final NoteForm noteForm = NotesDataGenerator.getNoteForm();
        final String noteFormData = gsonBuilder.create().toJson(noteForm);

        final MvcResult result = mockMvc.perform(post("/notes")
                .content(noteFormData)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertThat(result.getResponse().getContentAsString(), is(EMPTY_STRING));
    }

    @Test
    public void test_createNoteNoData() throws Exception {
        mockMvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_createNoteError() throws Exception {
        final NoteForm noteForm = NotesDataGenerator.getNoteForm();
        final String noteFormData = gsonBuilder.create().toJson(noteForm);

        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                "Ooops, something went wrong");
        final String expectedResult = gsonBuilder.create().toJson(apiError);

        doThrow(new ApiErrorException(apiError)).when(noteService).createNote(any());

        final MvcResult result = mockMvc.perform(post("/notes")
                .content(noteFormData)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andReturn();

        assertThat(expectedResult, equalTo(result.getResponse().getContentAsString()));
    }

    @Test
    public void test_updateNote() throws Exception {
        final NoteForm noteForm = NotesDataGenerator.getNoteForm();
        final String noteFormData = gsonBuilder.create().toJson(noteForm);

        final MvcResult result = mockMvc.perform(put("/notes")
                .content(noteFormData)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertThat(result.getResponse().getContentAsString(), is(EMPTY_STRING));
    }

    @Test
    public void test_updateNoteNoData() throws Exception {
        mockMvc.perform(put("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void test_updateNoteError() throws Exception {
        final NoteForm noteForm = NotesDataGenerator.getNoteForm();
        final String noteFormData = gsonBuilder.create().toJson(noteForm);

        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                "Ooops, something went wrong");
        final String expectedResult = gsonBuilder.create().toJson(apiError);

        doThrow(new ApiErrorException(apiError)).when(noteService).updateNote(any());

        final MvcResult result = mockMvc.perform(put("/notes")
                .content(noteFormData)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andReturn();

        assertThat(expectedResult, equalTo(result.getResponse().getContentAsString()));
    }

    @Test
    public void test_deleteNote() throws Exception {
        final NoteForm noteForm = NotesDataGenerator.getNoteFormWithId();
        final String noteFormData = gsonBuilder.create().toJson(noteForm);

        final MvcResult result = mockMvc.perform(delete("/notes/" + noteForm.getId())
                .content(noteFormData)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        assertThat(result.getResponse().getContentAsString(), is(EMPTY_STRING));
    }

    @Test
    public void test_deleteNoteError() throws Exception {
        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Not found");
        final String expectedResult = gsonBuilder.create().toJson(apiError);

        doThrow(new ApiErrorException(apiError)).when(noteService).deleteNoteByNoteId(anyLong());

        final MvcResult result = mockMvc.perform(delete("/notes/" + anyLong())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andReturn();

        assertThat(expectedResult, equalTo(result.getResponse().getContentAsString()));
    }


    private class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime> {

        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(formatter));
        }
    }
}
