package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.constants.WebConstant;
import com.udacity.jwdnd.course1.cloudstorage.entity.CredentialEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * CredentialMapper.
 *
 * @author NguyenT4.
 * @since 2023/09/23.
 */
@Mapper
public interface CredentialMapper {
    @Select(WebConstant.GetDataFromTable.SELECT_CREDENTIAL_BY_USER_ID)
    List<CredentialEntity> getCredentialByUserId(Integer userId);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) " +
            "VALUES(#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    void insert(CredentialEntity credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void deleteCredential(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url = #{url}, key = #{key}, password = #{password}, username = #{newUserName} " +
            "WHERE credentialid = #{credentialId}")
    void updateCredential(Integer credentialId, String newUserName, String url, String key, String password);
}
