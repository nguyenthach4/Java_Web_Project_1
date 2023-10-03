package com.udacity.jwdnd.course1.cloudstorage.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * StringHelper.
 *
 * @author NguyenT4.
 * @since 2023/10/02.
 */
@Component
public class StringHelper {

    public String generateKey() {
        try {
            SecureRandom random = new SecureRandom();
            byte[] key = new byte[16];
            random.nextBytes(key);
            return Base64.getEncoder().encodeToString(key);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
