package com.assaf.NoteApi.notes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteModel {
    private String title;
    private String content;
    private Long userId;
}
