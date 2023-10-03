package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.constants.WebConstant;
import com.udacity.jwdnd.course1.cloudstorage.entity.NoteEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * NoteMapper.
 *
 * @author NguyenT4.
 * @since 2023/09/24.
 */
@Mapper
public interface NoteMapper {
    @Select(WebConstant.GetDataFromTable.SELECT_NOTE_BY_USER_ID)
    List<NoteEntity> getAllNoteByUserId(Integer userId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) " +
            "VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    void insert(NoteEntity note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteNote(Integer noteId);

    @Update("UPDATE NOTES SET notetitle = #{title}, notedescription = #{description} WHERE noteid = #{noteId}")
    void updateNote(Integer noteId, String title, String description);
}
