package com.assaf.NoteApi.notes;

import com.assaf.NoteApi.users.User;
import com.assaf.NoteApi.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public List<NoteDto> getNotesByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()){
            throw new UsernameNotFoundException("id:" + userId);
        }
        User user = userOptional.get();
        Optional<List<Note>> userNotes = noteRepository.findAllByUser(user);
        if (!userNotes.isPresent()) throw new IllegalStateException("No notes");
        List<Note> notes = userNotes.get();
        List<NoteDto> noteDtoList = new ArrayList<>();
        for (Note note : notes) {
            noteDtoList.add(NoteDto.toDto(note));
        }
        return noteDtoList;
    }

    public NoteDto addNote(NoteModel noteModel) {
        Long userId = noteModel.getUserId();
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()){
            throw new UsernameNotFoundException("id:" + userId);
        }
        User user = userOptional.get();
        Note note = new Note();
        note.setTitle(noteModel.getTitle());
        note.setContent(noteModel.getContent());
        note.setUser(user);
        note.setLastUpdate(new Date());

        note = noteRepository.save(note);
//        NoteModel resultNote = new NoteModel();
//        resultNote.setTitle(note.getTitle());
//        resultNote.setContent(note.getContent());
        return NoteDto.toDto(note);
    }

    public Note editNote(Long noteId, NoteModel noteModel) {
        Optional<Note> noteData = noteRepository.findById(noteId);
        if (noteData.isPresent()) {
            Note note = noteData.get();
            note.setTitle(noteModel.getTitle());
            note.setContent(noteModel.getContent());
            note.setLastUpdate(new Date());
            return noteRepository.save(note);
        }
        return null;
    }

    public boolean deleteNote(Long noteId) {
        Optional<Note> noteData = noteRepository.findById(noteId);
        if (noteData.isPresent()) {
            noteRepository.deleteById(noteId);
            return true;
        }
        return false;
    }
}
