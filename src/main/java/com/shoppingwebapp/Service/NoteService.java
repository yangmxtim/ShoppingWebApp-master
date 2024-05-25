package com.shoppingwebapp.Service;

import com.shoppingwebapp.Entity.Note;

import java.util.List;

public interface NoteService {
    List<Note> findAll();

    Note findById(int id);

    Note save(Note note);

    void deleteByID(int id);
}
