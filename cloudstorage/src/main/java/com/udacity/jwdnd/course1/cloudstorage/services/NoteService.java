package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.constants.WebConstant;
import com.udacity.jwdnd.course1.cloudstorage.entity.NoteEntity;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

/**
 * NoteService.
 *
 * @author NguyenT4.
 * @since 2023/10/01.
 */
@Service
public class NoteService {

    public final Logger logger = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    private NoteMapper noteMapper;

    public String addNewOrupdateNote(Integer noteId, String title, String description, Integer userId,
                                     RedirectAttributes redirectAttributes) {
        try {
            if (Objects.isNull(noteId)) {
                NoteEntity note = new NoteEntity(null, title, description, userId);
                redirectAttributes.addFlashAttribute(WebConstant.TabPattern.TAB_DEFAULT, MessageFormat.format(WebConstant.TabPattern.TAB_SELECTION,
                        WebConstant.TableName.NOTES));
                redirectAttributes.addFlashAttribute(WebConstant.MessageResponse.MESSAGE_SUCCESS,
                        MessageFormat.format(WebConstant.MessageResponse.INSERT_DATA_TABLE_SUCCESS, WebConstant.TableName.NOTES));
                noteMapper.insert(note);
                return WebConstant.ControllerUrl.URL_REDIRECT_HOME;
            }
            noteMapper.updateNote(noteId, title, description);
            redirectAttributes.addFlashAttribute(WebConstant.TabPattern.TAB_DEFAULT, MessageFormat.format(WebConstant
                    .TabPattern.TAB_SELECTION, WebConstant.TableName.NOTES));
            redirectAttributes.addFlashAttribute(WebConstant.MessageResponse.MESSAGE_SUCCESS,
                    MessageFormat.format(WebConstant.MessageResponse.UPDATE_DATA_TABLE_SUCCESS, WebConstant.TableName.NOTES));
            return WebConstant.ControllerUrl.URL_REDIRECT_HOME;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            redirectAttributes.addFlashAttribute(WebConstant.TabPattern.TAB_DEFAULT, MessageFormat.format(WebConstant.TabPattern.TAB_SELECTION,
                    WebConstant.TableName.NOTES));
            if (Objects.nonNull(noteId)) {
                redirectAttributes.addFlashAttribute(WebConstant.MessageResponse.MESSAGE_SUCCESS,
                        MessageFormat.format(WebConstant.MessageResponse.INSERT_DATA_TABLE_ERROR, WebConstant.TableName.NOTES));
            }
            redirectAttributes.addFlashAttribute(WebConstant.MessageResponse.MESSAGE_SUCCESS,
                    MessageFormat.format(WebConstant.MessageResponse.UPDATE_DATA_TABLE_ERROR, WebConstant.TableName.NOTES));
            return WebConstant.ControllerUrl.URL_REDIRECT_HOME;
        }
    }

    public List<NoteEntity> getAllNoteByUserId(Integer userId) {
        return noteMapper.getAllNoteByUserId(userId);
    }

    public String deleteNote(Integer noteId, RedirectAttributes redirectAttributes) {
        try {
            redirectAttributes.addFlashAttribute(WebConstant.TabPattern.TAB_DEFAULT, MessageFormat.format(WebConstant.TabPattern.TAB_SELECTION,
                    WebConstant.TableName.NOTES));
            redirectAttributes.addFlashAttribute(WebConstant.MessageResponse.MESSAGE_SUCCESS,
                    MessageFormat.format(WebConstant.MessageResponse.DELETE_DATA_TABLE_SUCCESS, WebConstant.TableName.NOTES));
            noteMapper.deleteNote(noteId);
            return WebConstant.ControllerUrl.URL_REDIRECT_HOME;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            redirectAttributes.addFlashAttribute(WebConstant.TabPattern.TAB_DEFAULT, MessageFormat.format(WebConstant.TabPattern.TAB_SELECTION,
                    WebConstant.TableName.NOTES));
            redirectAttributes.addFlashAttribute(WebConstant.MessageResponse.MESSAGE_SUCCESS,
                    MessageFormat.format(WebConstant.MessageResponse.DELETE_DATA_TABLE_ERROR, WebConstant.TableName.NOTES));
            return WebConstant.ControllerUrl.URL_REDIRECT_HOME;
        }
    }
}
