package com.shoppingwebapp.Service;

import com.shoppingwebapp.Dao.NoteRepository;
import com.shoppingwebapp.Model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    private NoteRepository noteRepository;
    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Note findById(int id) {
        Optional<Note> result = noteRepository.findById(id);
        Note note = null;
        if(result.isPresent()) {
            note = result.get();
        }else{
            throw new RuntimeException("not found");
        }
        return note;
    }
    public int updateContentById(Integer id) {
        return noteRepository.updateContentById(id);
    }



    @Transactional
    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void deleteByID(int id) {
        noteRepository.deleteById(id);
    }


}

