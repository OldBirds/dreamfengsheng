package com.lee.fengsheng.Service.userService;

import com.lee.fengsheng.Service.UserService;
import com.lee.fengsheng.mappers.UserMapper;
import com.lee.fengsheng.model.TokenModel;
import com.lee.fengsheng.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by XinSheng on 2016/12/14.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertToken(TokenModel tokenModel) {
        userMapper.insertToken(tokenModel);
    }

    @Override
    public TokenModel getTokenByUid(TokenModel model) {
        return userMapper.getTokenByUid(model.getU_id());
    }

    @Override
    public List<User> findUserAll() {
        return userMapper.findUserAll();
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User login(User user) {
        return  userMapper.login(user);
    }

    @Override
    public User findByName(String name) {
        return  userMapper.findByName(name);
    }
}
