package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.constants.WebConstant;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.utils.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * CredentialController.
 *
 * @author NguyenT4.
 * @since 2023/10/02.
 */
@Controller
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private UserService userService;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private StringHelper stringHelper;

    @RequestMapping(value = WebConstant.ControllerUrl.URL_ADD_OR_UPDATE_CREDENTIAL, method = RequestMethod.POST)
    public String addNewOrupdateCredential(Authentication authentication, @ModelAttribute(name = WebConstant.TableName.CREDENTIAL_MODEL) CredentialModel credentialModel,
                                           RedirectAttributes redirectAttributes) {
        Integer userId = userService.getUserId(authentication);
        String encoderBase64 = stringHelper.generateKey();
        String passwordEncryptValue = encryptionService.encryptValue(credentialModel.getPassword(), encoderBase64);
        return credentialService.addNewOrupdateCredential(credentialModel.getCredentialId(), credentialModel.getUsername(), credentialModel.getUrl(),
                encoderBase64, passwordEncryptValue, userId, redirectAttributes);
    }

    @RequestMapping(value = WebConstant.ControllerUrl.URL_DELETE_CREDENTIAL, method = RequestMethod.GET)
    private String deleteCredentialByCredentialId(@PathVariable(name = WebConstant.CredentialTable.CREDENTIAL_ID) String credentialid,
                                                  RedirectAttributes redirectAttributes) {
        return credentialService.deletecredential(Integer.parseInt(credentialid), redirectAttributes);
    }
}
