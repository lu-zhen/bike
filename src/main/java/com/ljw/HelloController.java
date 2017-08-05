package com.ljw;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liujiawang on 2017/8/3.
 */
@RestController
public class HelloController {
    @RequestMapping("hello")
    public String say(){
        return "Hello World";
    }
}
