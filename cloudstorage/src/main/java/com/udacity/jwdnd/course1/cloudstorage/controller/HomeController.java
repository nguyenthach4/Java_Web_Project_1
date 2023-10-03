package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.constants.WebConstant;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * HomeController.
 *
 * @author NguyenT4.
 * @since 2023/09/24.
 */
@Controller
public class HomeController {

    @Autowired
    private  FileService fileService;

    @Autowired
    private  UserService userService;

    @Autowired
    private  NoteService noteService;

    @Autowired
    private  CredentialService credentialService;

    @Autowired
    private EncryptionService encryptionService;

    @RequestMapping(value = WebConstant.ControllerUrl.URL_HOME, method = RequestMethod.GET)
    public String index(Authentication authentication, Model model) {
        Integer userId = userService.getUserId(authentication);
        if (Objects.isNull(userId)) {
            return WebConstant.ControllerUrl.URL_LOGIN;
        }
        model.addAttribute("credentials", this.credentialService.getAllCredentialByUserId(userId));
        model.addAttribute("filesEmpty", this.fileService.getAllFileByUserId(userId).isEmpty());
        model.addAttribute("files", this.fileService.getAllFileByUserId(userId));
        model.addAttribute("notes", this.noteService.getAllNoteByUserId(userId));
        model.addAttribute("encryptionService", encryptionService);
        return WebConstant.ControllerUrl.URL_HOME;
    }
}
