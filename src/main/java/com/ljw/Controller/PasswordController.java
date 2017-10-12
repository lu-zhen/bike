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
 * Created by liujiawang on 2017/10/12.
 */
@RestController
public class PasswordController {

    private static int CHANGE_SUCCEED = 1;
    private static int CHANGE_FAILED = 0;
    @Autowired
    private UserService userService;

    @RequestMapping("forgetpassword")
    public void forget(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        JSONObject json = new JSONObject();
        try {
            UserEntity userEntity = userService.findByUsername(username);
            userEntity.setPassword(password);
            userService.save(userEntity);
            json.put("msg",CHANGE_SUCCEED);
        }catch (Exception e){
            json.put("msg",CHANGE_FAILED);
        }
        String js = json.toString();
        response.getWriter().write(js.toString());
    }

    @RequestMapping("changepassword")
    public void change(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        JSONObject json = new JSONObject();
        UserEntity userEntity = userService.findByUsername(username);
        if(password.equals(userEntity.getPassword())){
            userEntity.setPassword(newPassword);
            userService.save(userEntity);
            json.put("msg",CHANGE_SUCCEED);
        }else{
            json.put("msg",CHANGE_FAILED);
        }
        String js = json.toString();
        response.getWriter().write(js.toString());
    }
}
