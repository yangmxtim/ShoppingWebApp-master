package com.shoppingwebapp.Controller;

import com.shoppingwebapp.Service.NoteService;
import com.shoppingwebapp.Entity.Note;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = {"Origin", "Content-Type", "Accept"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
//@CrossOrigin(origins = {"＊"}, allowedHeaders = {"Origin", "Content-Type", "Accept"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
//@CrossOrigin(allowCredentials = "true", origins = "http://localhost:5173", allowedHeaders = "http://localhost:5173") // set
//@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "http://localhost:5173")

@RestController
@RequestMapping("/api")

public class NoteRestController {
    private NoteService noteService;

    public NoteRestController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public List<Note> findAll(){
        return noteService.findAll();
    }

    @GetMapping("/notes/{noteId}")
    public Note getnote(@PathVariable int noteId){
        Note note = noteService.findById(noteId);
        if(note == null){
            throw new RuntimeException("note not found");
        }else{
            return note;
        }
    }

    @PostMapping("/notes")
    public Note addnote(@RequestBody Note note){
        // in case it enters other exist id so merge will update the data in db
        note.setId(0);
        Note dbNote = noteService.save(note);
        return dbNote;
    }

    @PutMapping("/notes/{noteId}")
    public Note updatenote(@RequestBody Note note){
        Note dbNote = noteService.save(note);
        return dbNote;
    }
    //test qr code
    @GetMapping("/check/{noteId}")
    public void updateNoteContent(@PathVariable Integer noteId, HttpServletResponse response) throws IOException {
        int updatedRows = noteService.updateContentById(noteId);
        if (updatedRows > 0) {
            response.sendRedirect("https://www.google.es/");
        } else {
            response.sendRedirect("https://www.google.es/");        }
    }
//    @GetMapping("/check/{noteId}")
//    public ResponseEntity<Void> updateNoteContent(@PathVariable Integer noteId) {
//        int updatedRows = noteService.updateContentById(noteId);
//        if (updatedRows > 0) {
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


    @DeleteMapping("/notes/{noteId}")
    public void deletenote(@PathVariable int noteId){
        // noteService.deleteByID(noteId);

        Note note = noteService.findById(noteId);
        if(note == null){
            throw new RuntimeException("note not found");
        }else{
            noteService.deleteByID(noteId);
        }
    }
}
