package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.constants.WebConstant;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

/**
 * UserService.
 *
 * @author NguyenT4.
 * @since 2023/09/23.
 */
@Service
public class UserService {

    public final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private HashService hashService;

    @Autowired
    private UserMapper userMapper;

    public int createUser(UserEntity user) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] salt = new byte[16];
            secureRandom.nextBytes(salt);
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            String hashedPassword = this.hashService.getHashedValue(user.getPassword(), encodedSalt);
            return userMapper.insert(new UserEntity(null, user.getUsername(), encodedSalt, hashedPassword,
                    user.getFirstName(), user.getLastName()));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw ex;
        }
    }

    public Integer getUserId(Authentication authentication) {
        try {
            String userName = authentication.getName();
            UserEntity user = getUserByUserName(userName);
            if (Objects.isNull(user)) {
                return null;
            }
            return user.getUserId();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw ex;
        }

    }

    public UserEntity getUserByUserName(String username) {
        try {
            return userMapper.getUserByUserName(username);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw ex;
        }

    }

    public String signup(UserEntity user, Model model, RedirectAttributes redirectAttributes) {
        try {
            if (Objects.nonNull(this.getUserByUserName(user.getUsername()))) {
                model.addAttribute(WebConstant.MessageResponse.MESSAGE_ERROR, WebConstant.MessageResponse.USERNAME_ALREADY_EXISTS);
                return WebConstant.ControllerUrl.URL_SIGNUP;
            }

            if (this.createUser(user) < 0) {
                model.addAttribute(WebConstant.MessageResponse.MESSAGE_ERROR, WebConstant.MessageResponse.SIGNING_ERROR);
                return WebConstant.ControllerUrl.URL_SIGNUP;
            }

            redirectAttributes.addFlashAttribute(WebConstant.MessageResponse.MESSAGE_SUCCESS, WebConstant.MessageResponse.SIGNING_SUCCESS);
            return "redirect:/login";
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw ex;
        }
    }

    public String logout(Authentication authentication, HttpServletRequest request,
                         HttpServletResponse response) {
        try {
            if (Objects.isNull(authentication)) {
                return WebConstant.MessageResponse.LOGOUT_RESPONSE;
            }
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            return WebConstant.MessageResponse.LOGOUT_RESPONSE;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw ex;
        }
    }
}
