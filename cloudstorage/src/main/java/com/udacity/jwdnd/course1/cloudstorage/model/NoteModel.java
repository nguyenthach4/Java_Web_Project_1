package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * NoteModel.
 *
 * @author NguyenT4.
 * @since 2023/09/24.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteModel {
    private Integer noteId;
    private String title;
    private String description;
}
