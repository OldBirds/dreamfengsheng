package com.lee.fengsheng.Service;

import com.lee.fengsheng.model.TokenModel;
import com.lee.fengsheng.model.User;

import java.util.List;

/**
 * Created by XinSheng on 2016/12/14.
 */
public interface UserService {

    void insertToken(TokenModel tokenModel);

    TokenModel getTokenByUid(TokenModel model);

    List<User> findUserAll();

    void insertUser(User user);

    User login(User user);

    User findByName(String name);
}
