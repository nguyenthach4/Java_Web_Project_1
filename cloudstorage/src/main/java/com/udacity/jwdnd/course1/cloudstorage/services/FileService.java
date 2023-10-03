package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.constants.WebConstant;
import com.udacity.jwdnd.course1.cloudstorage.entity.FileEntity;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * FileService.
 *
 * @author NguyenT4.
 * @since 2023/09/24.
 */
@Service
public class FileService {

    public final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private UserMapper userMapper;

    public List<FileEntity> getAllFileByUserId(Integer userId) {

        return fileMapper.getFileByUserId(userId);
    }

    public FileEntity getFileByFileId(String fileId) {
        return fileMapper.getFileByFileId(Integer.parseInt(fileId));
    }

    public String uploadFile(String username, MultipartFile multipartFile, RedirectAttributes redirectAttributes) {
        try {

            if (Objects.isNull(userMapper.getUserByUserName(username))) {
                redirectAttributes.addFlashAttribute("errorMessage", "The file exceeds allowed size. Please try again");
                return "redirect:/home";
            }

            if (multipartFile.isEmpty()) {
                redirectAttributes.addFlashAttribute("errorMessage", "File upload id empty, Please selected a file !");
                return "redirect:/home";
            }

            if (multipartFile.getSize() > WebConstant.MAX_SIZE_MB) {
                redirectAttributes.addFlashAttribute("errorMessage", "File size is too big. Maximum size is 15 MB !");
                return "redirect:/home";
            }

            FileEntity fileExist = fileMapper.getFileByFileName(multipartFile.getOriginalFilename());

            if (Objects.nonNull(fileExist)) {
                redirectAttributes.addFlashAttribute("errorMessage", "File is exist !");
                return "redirect:/home";
            }

            fileExist = new FileEntity();
            fileExist.setFileName(multipartFile.getOriginalFilename());
            fileExist.setContentType(multipartFile.getContentType());
            fileExist.setFileData(multipartFile.getBytes());
            fileExist.setUserId(userMapper.getUserByUserName(username).getUserId());

            fileMapper.insert(fileExist);
            redirectAttributes.addFlashAttribute("successMessage", "File " + fileExist.getFileName() + " saved successful !");
            return "redirect:/home";
        } catch (IOException ioEx) {
            logger.error(ioEx.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "File upload error !");
            return "redirect:/home";
        }
    }

    public String deleteFile(Integer fileId, RedirectAttributes redirectAttributes) {
        try {
            fileMapper.deleteFileByFileId(fileId);
            redirectAttributes.addFlashAttribute("successMessage", "delete file success !");
            return "redirect:/home";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "delete file error !");
            logger.error(ex.getMessage());
            return "redirect:/home";
        }
    }
}
