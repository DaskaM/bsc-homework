package com.bsc.notes.model.converter;

import com.bsc.notes.entity.NoteEntity;
import com.bsc.notes.model.NoteView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class handles converting NoteEntity {@link NoteEntity} to NoteView {@link NoteView} objects
 */
@Component
public class NoteConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public NoteConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This methods converts note entity object to an object that's exposed through the API
     * @param note source NoteEntity {@link NoteEntity}
     * @return converted entity to NoteView {@link NoteView}
    */
    public NoteView convert(NoteEntity note) {
        return modelMapper.map(note, NoteView.class);
    }
}