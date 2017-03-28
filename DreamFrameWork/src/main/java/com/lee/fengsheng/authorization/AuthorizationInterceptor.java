package com.lee.fengsheng.authorization;

import com.lee.fengsheng.authorization.annotation.Authorization;
import com.lee.fengsheng.authorization.manager.TokenManager;
import com.lee.fengsheng.mappers.UserMapper;
import com.lee.fengsheng.model.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by XinSheng on 2016/12/16.
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private String  AUTHORIZATION="authorization";
    private String  CURRENT_USER_ID="CURRENT_USER_ID";

    @Autowired
    private TokenManager manager;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        System.out.print("21212121212");
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //从header中得到token
        String authorization = request.getHeader(AUTHORIZATION);

        //验证token
        TokenModel model = manager.getToken(authorization);
        if (manager.checkToken(model)) {
            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
            request.setAttribute(CURRENT_USER_ID, model.getU_id());
            return true;
        }
        //如果验证token失败，并且方法注明了Authorization，返回401错误
        if (method.getAnnotation(Authorization.class) != null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }
}