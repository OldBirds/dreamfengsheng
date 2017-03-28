package com.lee.fengsheng.mappers;


import com.lee.fengsheng.model.TokenModel;
import com.lee.fengsheng.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by XinSheng on 2016/12/14.
 */
@Mapper
public interface UserMapper {
    @Insert("")
    void insertToken(TokenModel tokenModel);

    TokenModel getTokenByUid(Long uid);

    List<User> findUserAll();

    void insertUser(User user);

    User login(User user);
    @Select("select * from tbl_user")
    User findByName(String name);
}