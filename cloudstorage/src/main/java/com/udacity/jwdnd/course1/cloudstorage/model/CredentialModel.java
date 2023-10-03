package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CredentialModel.
 *
 * @author NguyenT4.
 * @since 2023/09/23.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredentialModel {
    private Integer credentialId;
    private String url;
    private String password;
    private String username;
}
