package com.lee.fengsheng.authorization.manager.impl;

import com.lee.fengsheng.Service.UserService;
import com.lee.fengsheng.authorization.manager.TokenManager;
import com.lee.fengsheng.model.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

/**
 * 通过Redis存储和验证token的实现类
 */
@Component
public class MySqlTokenManager implements TokenManager {

    private String TOKEN_EXPIRES_HOUR = "TOKEN_EXPIRES_HOUR";

    @Autowired
    private UserService userService;

    public TokenModel createToken(long userId) {
        Long nowTime = new Date().getTime();
        if(checkToken(new TokenModel(userId,"",nowTime))){
            return userService.getTokenByUid(new TokenModel(userId,"",nowTime));
        }else {
            //使用uuid作为源token
            String token = UUID.randomUUID().toString().replace("-", "");
            TokenModel model = new TokenModel(userId, token,nowTime);
            //存储到mysql
            //TODO:设置过期时间
            userService.insertToken(model);

            return model;
        }
    }

    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 3) {
            return null;
        }
        //使用userId和源token简单拼接成的token，
        // TODO:可以增加加密措施
        long userId = Long.parseLong(param[0]);
        String token = param[1];
        Long nowTime = Long.parseLong(param[2]);
        return new TokenModel(userId, token,nowTime);
    }

    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        TokenModel tokenModels = userService.getTokenByUid(model);

        if (tokenModels == null) {
            return false;
        }

        //如果验证成功，说明此用户进行了一次有效操作，
        // TODO:延长token的过期时间

        return true;
    }

    public void deleteToken(long userId) {


    }
}