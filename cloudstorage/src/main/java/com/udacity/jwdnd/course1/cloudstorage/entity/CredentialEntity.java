package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CredentialEntity.
 *
 * @author NguyenT4.
 * @since 2023.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredentialEntity {
    private Integer credentialid;
    private String url;
    private String username;
    private String key;
    private String password;
    private Integer userid;
}
