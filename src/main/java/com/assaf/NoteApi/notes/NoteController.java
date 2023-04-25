package com.assaf.NoteApi.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notes")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{userId}")
    public List<NoteDto> getNotesByUserId(@PathVariable("userId") Long userId){
        return noteService.getNotesByUserId(userId);
    }
    @PostMapping("/add-note")
    public NoteDto addNote(@RequestBody NoteModel noteModel){
        return noteService.addNote(noteModel);
    }

    @PutMapping("/edit-note/{noteId}")
    public Note editNote(@PathVariable("noteId") Long noteId,@RequestBody NoteModel noteModel) {
        return noteService.editNote(noteId,noteModel);
    }
    @DeleteMapping("/delete-note/{noteId}")
    public boolean editNote(@PathVariable("noteId") Long noteId) {
        return noteService.deleteNote(noteId);
    }
}
