package com.ac.alibaba.cloud.tookit.sample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description 测试部署后请求是否成功,通过 Cloud Toolkit ，高效的将本地应用程序代码修改，部署到云上
 * @Author zhuangding
 * @Date 2019/06/30 15:59
 **/
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello Alibaba Cloud Toolkit !";
    }
}
