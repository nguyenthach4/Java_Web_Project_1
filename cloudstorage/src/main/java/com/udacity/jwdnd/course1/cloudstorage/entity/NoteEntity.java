package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * NoteEntity.
 *
 * @author NguyenT4.
 * @since 2023/09/24.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteEntity {
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;
}
