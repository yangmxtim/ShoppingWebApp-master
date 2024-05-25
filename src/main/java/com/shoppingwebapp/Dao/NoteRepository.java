package com.shoppingwebapp.Dao;

import com.shoppingwebapp.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Integer> {

}
