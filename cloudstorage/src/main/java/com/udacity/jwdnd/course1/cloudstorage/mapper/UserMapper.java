package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.constants.WebConstant;
import com.udacity.jwdnd.course1.cloudstorage.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * UserMapper.
 *
 * @author NguyenT4.
 * @since 2023/09/23.
 */
@Mapper
public interface UserMapper {
    @Select(WebConstant.GetDataFromTable.SELECT_USER_BY_USER_NAME)
    UserEntity getUserByUserName(String username);

    @Select(WebConstant.GetDataFromTable.SELECT_USER_BY_USER_ID)
    UserEntity getUserByUserId(Integer userId);

    @Insert(WebConstant.InsertDataToTable.INSERT_USER)
    @Options(useGeneratedKeys = true, keyProperty = WebConstant.UserTable.USER_ID)
    int insert(UserEntity user);


}
