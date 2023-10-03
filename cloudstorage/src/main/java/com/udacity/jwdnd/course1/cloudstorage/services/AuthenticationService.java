package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.entity.UserEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

/**
 * AuthenticationService.
 *
 * @author NguyenT4.
 * @since 2023/09/23.
 */
@Service
public class AuthenticationService implements AuthenticationProvider {

   final private UserMapper userMapper;

   final private HashService hashService;

    public AuthenticationService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserEntity user = userMapper.getUserByUserName(userName);
        if (Objects.nonNull(user)) {
            String encodedSalt = user.getSalt();
            String hashedPassword = hashService.getHashedValue(password, encodedSalt);
            if (Objects.equals(user.getPassword(), hashedPassword)) {
                return new UsernamePasswordAuthenticationToken(userName, password, new ArrayList<>());
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
