package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.constants.WebConstant;
import com.udacity.jwdnd.course1.cloudstorage.entity.CredentialEntity;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

/**
 * CredentialService.
 *
 * @author NguyenT4.
 * @since 2023/09/23.
 */
@Service
public class CredentialService {

    public final Logger logger = LoggerFactory.getLogger(CredentialService.class);

    @Autowired
    private CredentialMapper credentialMapper;

    public List<CredentialEntity> getAllCredentialByUserId(Integer userId) {
        return credentialMapper.getCredentialByUserId(userId);
    }

    public String addNewOrupdateCredential(Integer credentialId, String username, String url, String encoderBase64,
                                           String passwordEncryptValue, Integer userId, RedirectAttributes redirectAttributes) {
        try {
            if (Objects.isNull(credentialId)) {
                CredentialEntity credential = new CredentialEntity();
                credential.setUserid(userId);
                credential.setUsername(username);
                credential.setUrl(url);
                credential.setKey(encoderBase64);
                credential.setPassword(passwordEncryptValue);
                redirectAttributes.addFlashAttribute(WebConstant.TabPattern.TAB_DEFAULT,
                        MessageFormat.format(WebConstant.TabPattern.TAB_SELECTION, WebConstant.TableName.CREDENTIALS));
                redirectAttributes.addFlashAttribute(WebConstant.MessageResponse.MESSAGE_SUCCESS,
                        MessageFormat.format(WebConstant.MessageResponse.INSERT_DATA_TABLE_SUCCESS, WebConstant.TableName.CREDENTIALS));
                credentialMapper.insert(credential);
                return WebConstant.ControllerUrl.URL_REDIRECT_HOME;
            }
            credentialMapper.updateCredential(credentialId, username, url, encoderBase64, passwordEncryptValue);
            redirectAttributes.addFlashAttribute(WebConstant.TabPattern.TAB_DEFAULT,
                    MessageFormat.format(WebConstant.TabPattern.TAB_SELECTION, WebConstant.TableName.CREDENTIALS));
            redirectAttributes.addFlashAttribute(WebConstant.MessageResponse.MESSAGE_SUCCESS,
                    MessageFormat.format(WebConstant.MessageResponse.UPDATE_DATA_TABLE_SUCCESS, WebConstant.TableName.CREDENTIALS));
            return WebConstant.ControllerUrl.URL_REDIRECT_HOME;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            redirectAttributes.addFlashAttribute(WebConstant.TabPattern.TAB_DEFAULT,
                    MessageFormat.format(WebConstant.TabPattern.TAB_SELECTION, WebConstant.TableName.CREDENTIALS));
            if (Objects.nonNull(credentialId)) {
                redirectAttributes.addFlashAttribute(WebConstant.MessageResponse.MESSAGE_ERROR,
                        MessageFormat.format(WebConstant.MessageResponse.INSERT_DATA_TABLE_ERROR, WebConstant.TableName.CREDENTIALS));
            }
            redirectAttributes.addFlashAttribute(WebConstant.MessageResponse.MESSAGE_ERROR,
                    MessageFormat.format(WebConstant.MessageResponse.UPDATE_DATA_TABLE_ERROR, WebConstant.TableName.CREDENTIALS));
            redirectAttributes.addFlashAttribute("errorMessage", "Add Credential error !");
            return WebConstant.ControllerUrl.URL_REDIRECT_HOME;
        }
    }

    public String deletecredential(Integer credentialId, RedirectAttributes redirectAttributes) {
        try {
            redirectAttributes.addFlashAttribute(WebConstant.TabPattern.TAB_DEFAULT,
                    MessageFormat.format(WebConstant.TabPattern.TAB_SELECTION, WebConstant.TableName.CREDENTIALS));
            redirectAttributes.addFlashAttribute(WebConstant.MessageResponse.MESSAGE_SUCCESS,
                    MessageFormat.format(WebConstant.MessageResponse.DELETE_DATA_TABLE_SUCCESS, WebConstant.TableName.CREDENTIALS));
            credentialMapper.deleteCredential(credentialId);
            return WebConstant.ControllerUrl.URL_REDIRECT_HOME;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            redirectAttributes.addFlashAttribute(WebConstant.TabPattern.TAB_DEFAULT,
                    MessageFormat.format(WebConstant.TabPattern.TAB_SELECTION, WebConstant.TableName.CREDENTIALS));
            redirectAttributes.addFlashAttribute(WebConstant.MessageResponse.MESSAGE_ERROR,
                    MessageFormat.format(WebConstant.MessageResponse.DELETE_DATA_TABLE_ERROR, WebConstant.TableName.CREDENTIALS));
            return WebConstant.ControllerUrl.URL_REDIRECT_HOME;
        }

    }
}
