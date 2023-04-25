package com.assaf.NoteApi.users;

import com.assaf.NoteApi.notes.Note;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Table(name = "users")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(nullable = false, length = 25)
    private String username;
    @Column(nullable = false, length = 50, unique = true)
    @NaturalId
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;
    @Column(nullable = false)
    private String role = "ROLE_USER";
    @Column(nullable = false)
    private boolean enabled = false;

}
