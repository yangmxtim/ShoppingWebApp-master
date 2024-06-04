package com.shoppingwebapp.Dao;

import com.shoppingwebapp.Model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    // test qr
    @Modifying
    @Transactional
    @Query("UPDATE Note n SET n.content = 'check' WHERE n.id = :id")
    int updateContentById(@Param("id") Integer id);

    List<Note> findNoteByUserId(Integer userId);
}
