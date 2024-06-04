package com.shoppingwebapp.Service;

import com.shoppingwebapp.Model.Note;

import java.util.List;

public interface NoteService {
    List<Note> findAll();

    Note findById(int id);

    Note save(Note note);

    void deleteByID(int id);

    int updateContentById(Integer id);

    List<Note> findNoteByUserId(Integer userId);
}
