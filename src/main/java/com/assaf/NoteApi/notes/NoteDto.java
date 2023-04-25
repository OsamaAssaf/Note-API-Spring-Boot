package com.assaf.NoteApi.notes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {
    private Long id;
    private String title;
    private String content;
    private Date lastUpdate;
    private Long userId;

    public static NoteDto toDto(Note entity){
        return NoteDto
                .builder()
                .id(entity.getNoteId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .lastUpdate(entity.getLastUpdate())
                .userId(entity.getUser().getUserId())
                .build();
    }


}
