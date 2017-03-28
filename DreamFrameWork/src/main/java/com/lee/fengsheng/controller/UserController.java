package com.lee.fengsheng.controller;

import com.lee.fengsheng.Service.UserService;
import com.lee.fengsheng.authorization.manager.TokenManager;
import com.lee.fengsheng.model.DreamResult;
import com.lee.fengsheng.model.TokenModel;
import com.lee.fengsheng.model.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by XinSheng on 2016/12/12.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

     @Autowired
    private TokenManager tokenManager;

    @ApiOperation(value = "获取用户列表", notes = "获取所有用户信息")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public DreamResult index() {
        List<User> userList = userService.findUserAll();
        System.out.print(userList);
        return DreamResult.instance(true, "请求成功", userList);
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public DreamResult insertUser(@RequestBody User user) {
        User u = userService.login(user);
        if(u==null){
            userService.insertUser(user);
            return DreamResult.instance(true, "请求成功", null);
        }else{
            return DreamResult.instance(false, "账号已经注册了", null);
        }
    }


    @ApiOperation(value = "用户登录", notes = "通过用户名和密码进行登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public DreamResult login(String userName, String password) {
        User u = new User();
        u.setName(userName);
        u.setPassword(password);
        User user = userService.login(u);
        if (user == null) {
            return DreamResult.instance(false, "用户名或密码错误", null);
        } else {
            Map<String,Object> r = new HashedMap();
           TokenModel model = tokenManager.createToken(user.getId());
            r.put("token",model.getToken());
            r.put("user",user);
            return DreamResult.instance(true, "登录成功", r);
        }
    }


    /**
     * 以下是测试
     */
    /**
     // 创建线程安全的Map
     static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

     @ApiOperation(value="获取用户列表", notes="")
     @RequestMapping(value="/", method= RequestMethod.GET)
     public List<User> getUserList() {
     // 处理"/users/"的GET请求，用来获取用户列表
     // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
     List<User> r = new ArrayList<User>(users.values());
     return r;
     }

     @ApiOperation(value="创建用户", notes="根据User对象创建用户")
     @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
     @RequestMapping(value="/", method=RequestMethod.POST)
     public String postUser(@ModelAttribute User user) {
     // 处理"/users/"的POST请求，用来创建User
     // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
     users.put(user.getU_id(), user);
     return "success";
     }

     @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
     @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
     @RequestMapping(value="/{id}", method=RequestMethod.GET)
     public User getUser(@PathVariable Long id) {
     // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
     // url中的id可通过@PathVariable绑定到函数的参数中
     return users.get(id);
     }

     @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
     @ApiImplicitParams({
     @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
     @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
     })
     @RequestMapping(value="/{id}", method=RequestMethod.PUT)
     public String putUser(@PathVariable Long id, @ModelAttribute User user) {
     // 处理"/users/{id}"的PUT请求，用来更新User信息
     User u = users.get(id);
     u.setUserName(user.getUserName());
     u.setAge(user.getAge());
     users.put(id, u);
     return "success";
     }

     @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
     @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
     @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
     public String deleteUser(@PathVariable Long id) {
     // 处理"/users/{id}"的DELETE请求，用来删除User
     users.remove(id);
     return "success";
     }
     */
}
