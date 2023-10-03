package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.constants.WebConstant;
import com.udacity.jwdnd.course1.cloudstorage.entity.UserEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * UserController.
 *
 * @author NguyenT4.
 * @since 2023/09/25.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = WebConstant.ControllerUrl.URL_SIGNUP, method = RequestMethod.GET)
    public String index() {
        return WebConstant.ControllerUrl.URL_SIGNUP;
    }

    @RequestMapping(value = WebConstant.ControllerUrl.URL_SIGNUP, method = RequestMethod.POST)
    public String signup(@ModelAttribute UserEntity user, Model model, RedirectAttributes redirectAttributes) {

        return userService.signup(user, model, redirectAttributes);
    }

    @RequestMapping(value = WebConstant.ControllerUrl.URL_LOGIN, method = RequestMethod.GET)
    public String login() {

        return WebConstant.ControllerUrl.URL_LOGIN;
    }

    @RequestMapping(value = WebConstant.ControllerUrl.URL_LOGOUT, method = RequestMethod.POST)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.logout(authentication, request, response);
    }
}
