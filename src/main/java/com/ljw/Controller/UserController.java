package com.ljw.Controller;

import com.ljw.Entity.UserEntity;
import com.ljw.Service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liujiawang on 2017/8/3.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;                //获取用户接口

    /**
     * 根据用户的账号密码实现登陆查询
     * @param request
     * @param response
     */
    @RequestMapping("login")

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");         //获取用户名
        String password = request.getParameter("password");         //获取密码
        JSONObject json = new JSONObject();                             //获取json对象
        UserEntity userEntity = userService.findByUsername(username);   //查询数据库是否有该用户
        if(userEntity!=null){
            if(userEntity.getPassword().equals(password)){
                json.put("msg",true);
            }else{
                json.put("msg",false);
            }
        }else{
            json.put("msg",false);
        }
        String js = json.toString();
        response.getWriter().write(js.toString());                          //通过response对象发送json信息
    }

    /**
     * 获取用户账号和密码，实现用户的注册功能
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("register")
    public void register(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");                         //设置编码，防止json中文乱码
        String username = request.getParameter("username");         //获取用户名
        String password = request.getParameter("password");         //获取密码
        JSONObject json = new JSONObject();
        UserEntity userEntity = userService.findByUsername(username);  //查询用户是否存在以及相对应的处理
        if(userEntity!=null){
            json.put("msg",false);
        }else {
            try {
                userEntity = new UserEntity();                  //存储用户
                userEntity.setUsername(username);
                userEntity.setPassword(password);
                userService.save(userEntity);
                json.put("msg",true);
            } catch (Exception e) {
                e.printStackTrace();
                json.put("msg",false);
            }
        }
        //json.put("msg",s);
        String js = json.toString();
        response.getWriter().write(js.toString());
    }
}
