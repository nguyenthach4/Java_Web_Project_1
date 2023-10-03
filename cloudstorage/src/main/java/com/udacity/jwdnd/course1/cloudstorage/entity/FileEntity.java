package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * FileEntity.
 *
 * @author NguyenT4.
 * @since 2023/09/23.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {

    private int fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Integer userId;
    private byte[] fileData;
}
