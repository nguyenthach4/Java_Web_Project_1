package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User.
 *
 * @author NguyenT4.
 * @since 2023/09/23.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private Integer userId;
    private String username;
    private String salt;
    private String password;
    private String firstName;
    private String lastName;
}
