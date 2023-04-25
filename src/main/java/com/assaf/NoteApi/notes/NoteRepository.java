package com.assaf.NoteApi.notes;

import com.assaf.NoteApi.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

    Optional<List<Note>> findAllByUser(User user);

}
