package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.constants.WebConstant;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * NoteController.
 *
 * @author NguyenT4.
 * @since 2023/10/01.
 */
@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = WebConstant.ControllerUrl.URL_ADD_OR_UPDATE_NOTE, method = RequestMethod.POST)
    public String addNewOrupdateNote(Authentication authentication, @ModelAttribute(name = WebConstant.TableName.NOTE_MODEL) NoteModel noteModel,
                                     RedirectAttributes redirectAttributes) {
        Integer userId = userService.getUserId(authentication);
        return noteService.addNewOrupdateNote(noteModel.getNoteId(), noteModel.getTitle(), noteModel.getDescription(),
                userId, redirectAttributes);
    }

    @RequestMapping(value = WebConstant.ControllerUrl.URL_DELETE_NOTE, method = RequestMethod.GET)
    private String deleteNoteByNoteId(@PathVariable(name = WebConstant.NoteTable.NOTE_ID) String fileId,
                                      RedirectAttributes redirectAttributes) {
        return noteService.deleteNote(Integer.parseInt(fileId), redirectAttributes);
    }
}
