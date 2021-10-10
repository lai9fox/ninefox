package top.ninefox.blog.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import top.ninefox.blog.config.FeignConfig;


@FeignClient(value = "user-service", configuration = FeignConfig.class)
public interface UserService {


    @RequestMapping("/tokencheck")
    boolean tokenCheck();

    @RequestMapping("/decodetoken")
    String decodeToken();

}
