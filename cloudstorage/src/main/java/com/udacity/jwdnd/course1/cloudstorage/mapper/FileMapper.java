package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.constants.WebConstant;
import com.udacity.jwdnd.course1.cloudstorage.entity.FileEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * FileMapper.
 *
 * @author NguyenT4.
 * @since 2023/09/24.
 */
@Mapper
public interface FileMapper {
    @Select(WebConstant.GetDataFromTable.SELECT_FILE_BY_FILE_ID)
    FileEntity getFileByFileId(int fileId);

    @Select(WebConstant.GetDataFromTable.SELECT_FILE_BY_FILE_NAME)
    FileEntity getFileByFileName(String fileName);

    @Select(WebConstant.GetDataFromTable.SELECT_FILE_BY_USER_ID)
    List<FileEntity> getFileByUserId(Integer userId);

    @Insert(WebConstant.InsertDataToTable.INSERT_FILE)
    @Options(useGeneratedKeys = true, keyProperty = WebConstant.FileTable.FILE_ID)
    int insert(FileEntity file);

    @Delete(WebConstant.DeleteDataFromTable.DELETE_FILE)
    void deleteFileByFileId(int fileId);
}
