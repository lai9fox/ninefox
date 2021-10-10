package top.ninefox.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import top.ninefox.service.MailService;

import java.util.Map;


@Configuration
@EnableCaching
public class RedisListenerConfig {

    @Autowired
    private MailService mailService;

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory factory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
//        使用监听器监听频道
//        发送邮件
        container.addMessageListener((message, bytes) -> {

            String s = new String(message.getBody()).replace("\\", "");

            JSONObject json = JSON.parseObject(s.substring(1, s.length() - 1));

            mailService.sendHtmlMail((String) json.get("email"), "验证码", (String) json.get("code"));

        }, new ChannelTopic("email-send"));
        return container;
    }


}
