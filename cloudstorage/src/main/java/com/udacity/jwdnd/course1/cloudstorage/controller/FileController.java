package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.constants.WebConstant;
import com.udacity.jwdnd.course1.cloudstorage.entity.FileEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * FileController.
 *
 * @author NguyenT4.
 * @since 2023/09/26.
 */
@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = WebConstant.ControllerUrl.URL_UPLOAD_FILE, method = RequestMethod.POST)
    public String uploadFile(Authentication authentication, @RequestParam(name = WebConstant.FileTable.FILE_UPLOAD) MultipartFile multipartFile,
                             RedirectAttributes redirectAttributes) {
        return fileService.uploadFile(authentication.getName(), multipartFile, redirectAttributes);
    }


    @RequestMapping(value = WebConstant.ControllerUrl.URL_GET_FILE_BY_FILE_ID, method = RequestMethod.GET)
    public ResponseEntity<byte[]> getFileByFileId(@PathVariable(name = WebConstant.FileTable.FILE_ID) String fileId) {
        FileEntity file = fileService.getFileByFileId(fileId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(file.getContentType()));
        return new ResponseEntity<>(fileService.getFileByFileId(fileId).getFileData(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = WebConstant.ControllerUrl.URL_DELETE_FILE_BY_FILE_ID, method = RequestMethod.GET)
    private String deleteFileByFileId(@PathVariable(name = WebConstant.FileTable.FILE_ID) String fileId,
                                      RedirectAttributes redirectAttributes) {
        return fileService.deleteFile(Integer.parseInt(fileId), redirectAttributes);
    }
}
