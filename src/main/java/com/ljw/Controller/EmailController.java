package com.ljw.Controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liujiawang on 2017/10/12.
 */
@RestController
public class EmailController {
    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping("email")
    public void email(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int t = (int)(Math.random()*1000000)+1;
        String email = request.getParameter("email");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(email);
        message.setSubject("邮箱验证");
        message.setText("邮箱验证码是"+t+"。请勿泄露给他人使用。");
        javaMailSender.send(message);
        System.out.println(t);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",t);
        String js = jsonObject.toString();
        response.getWriter().write(js.toString());
    }
}
