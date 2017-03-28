package com.lee.fengsheng.controller;

import com.lee.fengsheng.Service.UserService;
import com.lee.fengsheng.jwt.Audience;
import com.lee.fengsheng.model.AccessToken;
import com.lee.fengsheng.model.DreamResult;
import com.lee.fengsheng.model.LoginJWT;
import com.lee.fengsheng.model.User;
import com.lee.fengsheng.utils.JwtUtil;
import com.lee.fengsheng.utils.MyUtils;
import com.lee.fengsheng.utils.ResultStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by feng on 2017/2/15.
 */
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private Audience audienceEntity;

    @RequestMapping(value="/oauth/token",method = RequestMethod.POST,consumes = "application/json")
    public DreamResult getAccessToken(@RequestBody LoginJWT loginPara)
    {
        DreamResult resultMsg;
        try
        {
            System.out.println(loginPara.getClientId());
            System.out.println(audienceEntity.getClientId());
            if(loginPara.getClientId() == null
                    || (loginPara.getClientId().compareTo(audienceEntity.getClientId()) != 0))
            {
                resultMsg = new DreamResult(ResultStatusCode.INVALID_CLIENTID.getErrcode(),
                        ResultStatusCode.INVALID_CLIENTID.getErrmsg(), null);
                return resultMsg;
            }

            //验证码校验在后面章节添加


            //验证用户名密码
            User user = userService.findByName(loginPara.getUserName());
            System.out.println(user.getSalt());
            if (user == null)
            {
                resultMsg = new DreamResult(ResultStatusCode.INVALID_PASSWORD.getErrcode(),
                        ResultStatusCode.INVALID_PASSWORD.getErrmsg(), null);
                return resultMsg;
            }
            else
            {
                String md5Password = MyUtils.getMD5(loginPara.getPassword()+user.getSalt());
                System.out.println(md5Password);
                if (md5Password.compareTo(user.getPassword()) != 0)
                {
                    resultMsg = new DreamResult(ResultStatusCode.INVALID_PASSWORD.getErrcode(),
                            ResultStatusCode.INVALID_PASSWORD.getErrmsg(), null);
                    return resultMsg;
                }
            }

            //拼装accessToken
            String accessToken = JwtUtil.createJWT(loginPara.getUserName(), String.valueOf(user.getName()),
                    user.getRole(), audienceEntity.getClientId(), audienceEntity.getName(),
                    audienceEntity.getExpiresSecond() * 1000, audienceEntity.getBase64Secret());

            //返回accessToken
            AccessToken accessTokenEntity = new AccessToken();
            accessTokenEntity.setAccess_token(accessToken);
            accessTokenEntity.setExpires_in(audienceEntity.getExpiresSecond());
            accessTokenEntity.setToken_type("bearer");
            resultMsg = new DreamResult(ResultStatusCode.OK.getErrcode(),
                    ResultStatusCode.OK.getErrmsg(), accessTokenEntity);
            return resultMsg;

        }
        catch(Exception ex)
        {
            resultMsg = new DreamResult(ResultStatusCode.SYSTEM_ERR.getErrcode(),
                    ResultStatusCode.SYSTEM_ERR.getErrmsg(), null);
            return resultMsg;
        }
    }
}
